package parser.concreteparsers;

import parser.Parser;
import parser.NoMatchException;
import parser.ParseResult;

public class EitherParser extends Parser
{
    private Parser p1;
    private Parser p2;
    public EitherParser(Parser p1, Parser p2)
    {
        this.p1 = p1;
        this.p2 = p2;
    }

    public ParseResult doParse(String s)
    {
        ParseResult result;
        try {
            result = p1.doParse(s);
            return result;
        } catch (NoMatchException e) {
            result = p2.doParse(s);
            return result;
        }
    }
}
