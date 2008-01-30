import java.util.Map;
import java.util.HashMap;


public abstract class HashParser extends Parser
{
    private Map retorno;

    protected void discard(Parser p) {
        p.parse(finalString);
        finalString = p.getRemainder();
    }

    protected void discard(String s){
        Parser p = new NamedString(s);
        p.parse(finalString);
        finalString = p.getRemainder();
    }

    protected void capture(String name, Parser p) {
        retorno.put(name, p.parse(finalString));
        finalString = p.getRemainder();
    }

    public Object parse(String s) {
        originalString = s;
        finalString = s;
        retorno = new HashMap();
        runParser();
        return retorno;
    }

    public abstract void runParser();
}
