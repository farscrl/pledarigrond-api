package ch.pledarigrond.mongodb.util.backup;

import java.io.File;
import java.io.FileFilter;
import java.util.*;

public abstract class AbstractBackupHelper {

	protected List<File> listBackupFilesAsc(String backupDir) {
		File[] files = new File(backupDir).listFiles(filter());
		if (files == null) {
			return new ArrayList<File>();
		}
		List<File> backupFiles = Arrays.asList(files);

		Collections.sort(backupFiles, new Comparator<File>() {
			@Override
			public int compare(File o1, File o2) {
				Date d1 = new Date(o1.lastModified());
				Date d2 = new Date(o2.lastModified());
				return d2.compareTo(d1);
			}
		});
		return backupFiles;
	}

	protected FileFilter filter() {
		return new FileFilter() {
			@Override
			public boolean accept(File f) {
				return f.exists() && f.getName().endsWith(".zip")
						&& f.canRead() && !f.isHidden();
			}
		};
	}

}
