package ch.pledarigrond.lucene.analyzers;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.LowerCaseFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.standard.StandardTokenizer;

public class CaseInsensitiveStandardTokenizer extends Analyzer {

    @Override
    protected TokenStreamComponents createComponents(String fieldName) {
        Tokenizer tokenizer = new StandardTokenizer();
        TokenStream tokenStream = new LowerCaseFilter(tokenizer);
        return new TokenStreamComponents(tokenizer, tokenStream);
    }
}
