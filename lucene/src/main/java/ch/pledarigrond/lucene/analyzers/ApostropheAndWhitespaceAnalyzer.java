package ch.pledarigrond.lucene.analyzers;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.WhitespaceTokenizer;
import org.apache.lucene.analysis.pattern.PatternReplaceCharFilter;

import java.io.Reader;
import java.util.regex.Pattern;

public class ApostropheAndWhitespaceAnalyzer extends Analyzer {

    @Override
    protected TokenStreamComponents createComponents(String fieldName) {
        WhitespaceTokenizer tokenizer = new WhitespaceTokenizer();
        return new TokenStreamComponents(tokenizer);
    }

    @Override
    protected Reader initReader(String fieldName, Reader reader) {
        return initReaderStatic(fieldName, reader);
    }

    public static Reader initReaderStatic(String fieldName, Reader reader) {
        // Replace apostrophes with spaces using a PatternReplaceCharFilter
        Pattern apostrophePattern = Pattern.compile("['`´‘’]");
        return new PatternReplaceCharFilter(apostrophePattern, " ", reader);
    }
}
