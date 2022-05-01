package calculator;

import numerics.Expression;

import java.io.StreamTokenizer;
import java.io.StringReader;
import java.util.LinkedList;

public class Parser {
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    /**
     * This should be a tool for converting string to tree of expressions.
     *
     */
    LinkedList <String> tokens;
    Expression root;

    public Parser() throws Exception {
       //root = new Expression();
    }
    public LinkedList<String> tokenize(String equation) throws Exception {
        LinkedList<String> temp = new LinkedList<>();
        StreamTokenizer tokenizer = new StreamTokenizer(new StringReader(equation));
        //tokenizer.ordinaryChar('-');
        tokenizer.ordinaryChar('/');
        //tokenizer.ordinaryChar('i');
        tokenizer.ordinaryChar('÷');
        tokenizer.ordinaryChar('x');
        tokenizer.ordinaryChar('*');
        while (tokenizer.nextToken() != StreamTokenizer.TT_EOF) {
            switch (tokenizer.ttype) {
                case StreamTokenizer.TT_NUMBER -> temp.add(String.valueOf(tokenizer.nval));
                case StreamTokenizer.TT_WORD -> temp.add(tokenizer.sval);
                default -> temp.add(String.valueOf((char) tokenizer.ttype));
            }
        }
        LinkedList<String> res = new LinkedList<>();
        System.out.println(temp);
        for(int i = 0; i < temp.size(); i++){
            if(isNumeric(temp.get(i))){
                try{
                    if ("i".equals(temp.get(i + 1))) {
                        res.add(temp.get(i) + temp.get(i + 1));
                        i++;
                    }
                    else res.add(temp.get(i));
                }catch (Exception e){
                    res.add(temp.get(i));
                }
            }
            else res.add(temp.get(i));
        }
        this.tokens = res;
        return res;
    }
}
