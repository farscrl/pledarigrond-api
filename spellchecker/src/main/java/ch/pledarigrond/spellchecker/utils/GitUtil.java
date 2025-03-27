package ch.pledarigrond.spellchecker.utils;

import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.spellchecker.model.GitDataDto;
import org.eclipse.jgit.api.CreateBranchCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class GitUtil {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final List<Language> activeLanguages;

    public GitUtil(List<Language> activeLanguages) {
        this.activeLanguages = activeLanguages;
    }

    public void commit(GitDataDto gitData) {
        UsernamePasswordCredentialsProvider credentialsProvider = getCredentialsProvider(gitData);
        try {
            File repoDir = checkOutRepository(gitData, credentialsProvider);
            Git git = getRepository(repoDir);
            changeToBranch(git, credentialsProvider, gitData.getRemoteBranch());
            pull(git, credentialsProvider);
            boolean didChange = copyFiles(git, gitData.getHunspellLocation());
            if (didChange) {
                commitAndPush(git, credentialsProvider);
                logger.info("Changes committed and pushed");
            } else {
                logger.info("No changes to commit");
            }
        } catch (GitAPIException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private UsernamePasswordCredentialsProvider getCredentialsProvider(GitDataDto gitData) {
        return new UsernamePasswordCredentialsProvider(gitData.getGitToken(), "");
    }

    private File checkOutRepository(GitDataDto gitData, UsernamePasswordCredentialsProvider credentialsProvider) throws GitAPIException {
        File repoDir = new File(gitData.getRepoPath());
        if (!repoDir.exists()) {
            logger.info("Cloning repository");
            try {
                Git.cloneRepository()
                        .setURI(gitData.getRemoteUrl())
                        .setDirectory(repoDir)
                        .setCredentialsProvider(credentialsProvider)
                        .call();
            } catch (GitAPIException e) {
                logger.error("Error while cloning repository", e);
                throw new RuntimeException(e);
            }

        }
        return repoDir;
    }

    private Git getRepository(File repoDir) throws IOException {
        return Git.open(repoDir);
    }

    private void changeToBranch(Git git, UsernamePasswordCredentialsProvider credentialsProvider, String branchName) throws GitAPIException {
        git.fetch().setCredentialsProvider(credentialsProvider).call();

        boolean branchExists = git.branchList().call().stream().anyMatch(ref -> ref.getName().endsWith(branchName));
        if (!branchExists) {
            git.branchCreate()
                    .setName(branchName)
                    .setStartPoint("origin/" + branchName)
                    .setUpstreamMode(CreateBranchCommand.SetupUpstreamMode.TRACK)
                    .call();
        }
        git.checkout().setName(branchName).call();
    }

    private void pull(Git git, UsernamePasswordCredentialsProvider credentialsProvider) throws GitAPIException {
        git.pull().setCredentialsProvider(credentialsProvider).call();
    }

    private boolean copyFiles(Git git, String hunspellPath) throws IOException {
        AtomicBoolean didChange = new AtomicBoolean(false);
        Path basePath = Paths.get(hunspellPath);
        Files.createDirectories(basePath);

        activeLanguages.forEach(language -> {
            File aff = basePath.resolve(language.getName() + "/rm-" + language.getSubtag() + ".aff").toFile();
            File dicFile = basePath.resolve(language.getName() + "/rm-" + language.getSubtag() + ".dic").toFile();
            File versionFile = basePath.resolve(language.getName() + "/rm-" + language.getSubtag() + "_version.txt").toFile();
            File licenceFile = basePath.resolve(language.getName() + "/rm-" + language.getSubtag() + "_LICENSE.txt").toFile();
            File hyphenationFile = basePath.resolve(language.getName() + "/hyph_rm-" + language.getSubtag() + ".dic").toFile();
            File licenceHyphenationFile = basePath.resolve(language.getName() + "/hyph_rm-" + language.getSubtag() + "_LICENSE.txt").toFile();

            String affPath = "dictionaries/rm-" + language.getSubtag() + "/rm-" + language.getSubtag() + ".aff";
            String dicPath = "dictionaries/rm-" + language.getSubtag() + "/rm-" + language.getSubtag() + ".dic";
            String versionPath = "dictionaries/rm-" + language.getSubtag() + "/rm-" + language.getSubtag() + "_version.txt";
            String licencePath = "dictionaries/rm-" + language.getSubtag() + "/rm-" + language.getSubtag() + "_LICENSE.txt";
            String hyphenationPath = "dictionaries/rm-" + language.getSubtag() + "/hyph_rm-" + language.getSubtag() + ".dic";
            String licenceHyphenationPath = "dictionaries/rm-" + language.getSubtag() + "/hyph_rm-" + language.getSubtag() + "_LICENSE.txt";

            Path affDestination = basePath.resolve("git/" + affPath);
            Path dicDestination = basePath.resolve("git/" + dicPath);
            Path versionDestination = basePath.resolve("git/" + versionPath);
            Path licenceDestination = basePath.resolve("git/" + licencePath);
            Path hyphenationDestination = basePath.resolve("git/" + hyphenationPath);
            Path licenceHyphenationDestination = basePath.resolve("git/" + licenceHyphenationPath);

            try {
                Files.copy(aff.toPath(), affDestination, StandardCopyOption.REPLACE_EXISTING);
                Files.copy(dicFile.toPath(), dicDestination, StandardCopyOption.REPLACE_EXISTING);
                Files.copy(hyphenationFile.toPath(), hyphenationDestination, StandardCopyOption.REPLACE_EXISTING);
                Files.copy(licenceHyphenationFile.toPath(), licenceHyphenationDestination, StandardCopyOption.REPLACE_EXISTING);
                git.add().addFilepattern(affPath).call();
                git.add().addFilepattern(dicPath).call();
                git.add().addFilepattern(hyphenationPath).call();
                git.add().addFilepattern(licenceHyphenationPath).call();

                Status status = git.status().call();
                if (!status.getAdded().isEmpty() || !status.getChanged().isEmpty() || !status.getRemoved().isEmpty()) {
                    Files.copy(versionFile.toPath(), versionDestination, StandardCopyOption.REPLACE_EXISTING);
                    Files.copy(licenceFile.toPath(), licenceDestination, StandardCopyOption.REPLACE_EXISTING);
                    git.add().addFilepattern(versionPath).call();
                    git.add().addFilepattern(licencePath).call();

                    didChange.set(true);
                }
            } catch (IOException | GitAPIException e) {
                throw new RuntimeException(e);
            }
        });

        return didChange.get();
    }

    private void commitAndPush(Git git, UsernamePasswordCredentialsProvider credentialsProvider) throws GitAPIException {
        git.commit().setMessage("feat: actualisaziun dicziunaris hunspell").call();
        git.push().setCredentialsProvider(credentialsProvider).call();
    }
}
