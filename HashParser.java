import java.util.Map;
import java.util.HashMap;


public abstract class HashParser extends Parser
{
    private Map retorno;
    private String finalString;

    protected void discard(Parser p) {
        p.parse(finalString);
        finalString = p.doParse(finalString).getRemainder();
    }

    protected void discard(String s){
        Parser p = new NamedString(s);
        finalString = p.doParse(finalString).getRemainder();
    }

    protected void capture(String name, Parser p) {
        ParseResult result = p.doParse(finalString);
        retorno.put(name, result.getObject());
        finalString = result.getRemainder();
    }

    public ParseResult doParse(String s) {
        finalString = s;
        retorno = new HashMap();
        runParser();
        return new ParseResult(retorno, finalString);
    }

    public abstract void runParser();
}
