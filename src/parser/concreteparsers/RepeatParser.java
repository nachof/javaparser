package parser.concreteparsers;

import java.util.List;
import java.util.ArrayList;

import parser.Parser;
import parser.NoMatchException;
import parser.ParseResult;

public class RepeatParser extends Parser
{
    private Parser parser;

    public RepeatParser(Parser p) {
        parser = p;
    }

    public ParseResult doParse(String s) {
        String finalString = s;
        List resultado = new ArrayList();
        try {
            while(true) {
                ParseResult res = parser.doParse(finalString);
                finalString = res.getRemainder();
                resultado.add(res.getObject());
            }
        } catch (NoMatchException e) {
            // Do Nothing -- end processing
        }
        return new ParseResult(resultado, finalString);
    }
}
