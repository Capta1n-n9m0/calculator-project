package calculator;

import numerics.Expression;

import java.io.StreamTokenizer;
import java.io.StringReader;
import java.util.LinkedList;

public class Parser {
    /**
     * This should be a tool for converting string to tree of expressions.
     *
     */
    LinkedList <String> tokens;
    Expression root;

    public Parser() throws Exception {
        root = new Expression();
    }
    public LinkedList<String> tokenize(String equation) throws Exception {
        LinkedList<String> res = new LinkedList<>();
        StreamTokenizer tokenizer = new StreamTokenizer(new StringReader(equation));
        tokenizer.ordinaryChar('-');
        tokenizer.ordinaryChar('/');
        while (tokenizer.nextToken() != StreamTokenizer.TT_EOF) {
            switch (tokenizer.ttype) {
                case StreamTokenizer.TT_NUMBER -> res.add(String.valueOf(tokenizer.nval));
                case StreamTokenizer.TT_WORD -> res.add(tokenizer.sval);
                default -> res.add(String.valueOf((char) tokenizer.ttype));
            }
        }
        return res;
    }
}
