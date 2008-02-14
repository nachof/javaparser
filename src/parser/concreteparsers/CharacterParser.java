package parser.concreteparsers;

import parser.Parser;
import parser.NoMatchException;
import parser.ParseResult;

public class CharacterParser extends Parser
{
    public ParseResult doParse(String s) {
        if (s != null && s.length() > 0) {
            return new ParseResult(s.substring(0, 1), s.substring(1));
        } else {
            throw new NoMatchException(s);
        }
    }
}

