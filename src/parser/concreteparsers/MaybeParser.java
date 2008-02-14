package parser.concreteparsers;

import parser.Parser;
import parser.NoMatchException;
import parser.ParseResult;

public class MaybeParser extends Parser
{
    private Parser parser;

    public MaybeParser(Parser p) {
        parser = p;
    }

    public ParseResult doParse(String s) {
        try {
            ParseResult result = parser.doParse(s);
            return result;
        } catch (NoMatchException e) {
            return new ParseResult(null, s);
        }
    }
}
