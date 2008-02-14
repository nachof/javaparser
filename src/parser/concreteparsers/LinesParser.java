package parser.concreteparsers;

import parser.Parser;
import parser.NoMatchException;
import parser.ParseResult;
import parser.HashParser;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class LinesParser extends Parser
{
    private Parser p;

    public LinesParser(Parser p) {
        this.p = p;
    }

    public ParseResult doParse(String s) {
        Parser actualParser = new HashParser() {
            public void runParser() {
                capture("line", p);
                discard(maybe("\n"));
            }
        };
        Parser repeating = Parser.repeat(actualParser);
        ParseResult pr = repeating.doParse(s);
        List firstList = (List) pr.getObject();
        List finalList = unPackList(firstList);
        return new ParseResult(finalList, pr.getRemainder());
    }

    private List unPackList(List firstList) {
        List finalList = new ArrayList();
        Iterator it = firstList.iterator();
        while (it.hasNext()) {
            Map m = (Map) it.next();
            finalList.add(m.get("line"));
        }
        return finalList;
    }
}
