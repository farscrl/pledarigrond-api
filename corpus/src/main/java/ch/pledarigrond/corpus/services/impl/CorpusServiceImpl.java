package ch.pledarigrond.corpus.services.impl;

import ch.pledarigrond.common.config.PgEnvironment;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.corpus.entities.CorpusText;
import ch.pledarigrond.corpus.repositories.CorpusTextRepository;
import ch.pledarigrond.corpus.services.CorpusService;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class CorpusServiceImpl implements CorpusService {

    private static final Logger logger = LoggerFactory.getLogger(CorpusServiceImpl.class);

    @Autowired
    private CorpusTextRepository corpusTextRepository;

    @Autowired
    private PgEnvironment pgEnvironment;

    @Override
    public boolean importQuotidianaCorpusFiles() {
        File f = new File(pgEnvironment.getCorpusBaseFolder() + "/quotidiana-corpus");

        String[] pathNames = f.list();

        for (String pathname : pathNames) {
            if (!importQuotidianaFile(new File(pgEnvironment.getCorpusBaseFolder() + "/quotidiana-corpus/" + pathname))) {
                return false;
            }
        }

        return true;
    }

    @Override
    public List<String> findInCorpus(Language language, String s) {
        List<CorpusText> results = corpusTextRepository.findByLangAndTextContaining("rm-" + language.getSubtag(), s, PageRequest.of(0, 20));
        List<String> texts = new ArrayList<>();
        for (CorpusText result : results) {
            texts.add(result.getText());
        }
        return texts;
    }

    private boolean importQuotidianaFile(File file) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            // process XML securely, avoid attacks like XML External Entities (XXE)
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            // parse XML file
            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();

            if (!doc.getDocumentElement().getNodeName().equals("CORPUS")) {
                return false;
            }

            NodeList list = doc.getElementsByTagName("DOC");

            for (int i = 0; i < list.getLength(); i++) {

                Node currentCorpusEntry = list.item(i);

                if (currentCorpusEntry.getNodeType() == Node.ELEMENT_NODE) {
                    Element docElement = (Element) currentCorpusEntry;
                    String id = docElement.getAttribute("id");
                    String lang = docElement.getAttribute("xml:lang");
                    String text = docElement.getElementsByTagName("P").item(0).getTextContent();
                    text = text.replace("\n", " ");

                    String[] sentences = cleanStrings(splitSentences(text));
                    for (String sentence : sentences) {
                        CorpusText importedDoc = new CorpusText(id, lang, sentence, "quotidiana");
                        corpusTextRepository.save(importedDoc);
                    }
                }
            }

        }  catch (ParserConfigurationException | SAXException | IOException e) {
            logger.error("Error while importing quotidiana file: " + e.getMessage());
            return false;
        }

        return true;
    }

    private String[] splitSentences(String sentence) {
        try (InputStream modelIn = getClass().getClassLoader().getResourceAsStream("opennlp-it-ud-vit-sentence-1.0-1.9.3.bin")) {
            assert modelIn != null;
            SentenceModel model = new SentenceModel(modelIn);
            SentenceDetectorME sentenceDetector = new SentenceDetectorME(model);

            return sentenceDetector.sentDetect(sentence);
        } catch (Exception e) {
            logger.error("Error while splitting sentences: " + e.getMessage());
        }
        return new String[0];
    }

    private String[] cleanStrings(String[] sentences) {
        for (int i = 0; i < sentences.length; i++) {
            sentences[i] = cleanString(sentences[i]);
        }
        return sentences;
    }

    private static String cleanString(String str) {
        // Define the regular expression pattern to match:
        // "■ " followed optionally by "(letters/letters)" or "(letters)"
        String pattern = "^■ (\\([a-zA-Z]{1,3}(\\/[a-zA-Z]{1,3})?\\) )?";
        // Replace the matched pattern with an empty string to remove it
        return str.replaceAll(pattern, "");
    }
}
