package parser.concreteparsers;

import parser.Parser;
import parser.NoMatchException;
import parser.ParseResult;

public class IntegerParser extends Parser
{
    public ParseResult doParse(String s) {
        int i = 0;
        while (s.length() > i && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
            i++;
        }
        if (i == 0)
            throw new NoMatchException(s);
        String number = s.substring(0, i);
        Integer result = Integer.valueOf(number);
        return new ParseResult(result, s.substring(i));
    }
}
