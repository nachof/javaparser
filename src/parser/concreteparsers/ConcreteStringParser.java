package parser.concreteparsers;

import parser.Parser;
import parser.NoMatchException;
import parser.ParseResult;


public class ConcreteStringParser extends Parser
{
    private String word;
    public ConcreteStringParser(String w) {
        word = w;
    }

    public ParseResult doParse(String s) {
        int slength = word.length();
        if (s != null && s.length() >= slength && s.substring(0, slength).equals(word)) {
            return new ParseResult(word, s.substring(slength));
        } else {
            throw new NoMatchException(s);
        }
    }
}
