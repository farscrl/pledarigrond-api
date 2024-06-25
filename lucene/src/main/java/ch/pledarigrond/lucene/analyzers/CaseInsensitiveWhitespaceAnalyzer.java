package ch.pledarigrond.lucene.analyzers;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.LowerCaseFilter;
import org.apache.lucene.analysis.core.WhitespaceTokenizer;
import org.apache.lucene.util.Version;

import java.io.Reader;

public class CaseInsensitiveWhitespaceAnalyzer extends Analyzer {

    @Override
    protected TokenStreamComponents createComponents(String arg0, Reader arg1) {
        Tokenizer tokenizer = new WhitespaceTokenizer(Version.LUCENE_CURRENT, arg1);
        TokenStream filter = new LowerCaseFilter(Version.LUCENE_CURRENT, tokenizer);
        return new TokenStreamComponents(tokenizer, filter);
    }
}
