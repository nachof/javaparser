import java.util.List;
import java.util.ArrayList;

public abstract class Parser 
{
    protected String originalString;
    protected String finalString;

    public static Parser character = new Character();
    public static Parser integer = new IntegerParser();

    public static Parser maybe(Parser p) {
        return new Maybe(p);
    }
    public static Parser maybe(String s) {
        return maybe(new NamedString(s));
    }

    public static Parser repeat(String s) {
        return repeat(new NamedString(s));
    }
    public static Parser repeat(Parser p) {
        return new Repeat(p);
    }

    public static Parser either(Parser p1, Parser p2) {
        return new EitherParser(p1, p2);
    }


    public abstract Object parse(String s);

    public String getRemainder() {
        return finalString;
    }
}

class Character extends Parser
{
    public Object parse(String s) {
        originalString = s;
        if (s != null && s.length() > 0) {
            finalString = s.substring(1);
            return s.substring(0, 1);
        } else {
            throw new NoMatchException(s);
        }
    }
}

class IntegerParser extends Parser
{
    public Object parse(String s) {
        int i = 0;
        while (s.length() > i && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
            i++;
        }
        if (i == 0)
            throw new NoMatchException(s);
        String number = s.substring(0, i);
        finalString = s.substring(i);
        Integer result = Integer.valueOf(number);
        return result;
    }
}

class NamedString extends Parser
{
    private String word;
    public NamedString(String w) {
        word = w;
    }

    public Object parse(String s) {
        originalString = s;
        int slength = word.length();
        if (s != null && s.length() >= slength && s.substring(0, slength).equals(word)) {
            finalString = s.substring(slength);
            return word;
        } else {
            throw new NoMatchException(s);
        }
    }
}

class Maybe extends Parser
{
    private Parser parser;

    public Maybe(Parser p) {
        parser = p;
    }

    public Object parse(String s) {
        originalString = s;
        try {
            Object result = parser.parse(s);
            finalString = parser.getRemainder();
            return result;
        } catch (NoMatchException e) {
            finalString = s;
            return null;
        }
    }
}

class Repeat extends Parser
{
    private Parser parser;

    public Repeat(Parser p) {
        parser = p;
    }

    public Object parse(String s) {
        originalString = s;
        finalString = s;
        List resultado = new ArrayList();
        try {
            while(true) {
                Object res = parser.parse(finalString);
                finalString = parser.getRemainder();
                resultado.add(res);
            }
        } catch (NoMatchException e) {
            // Do Nothing -- end processing
        }
        return resultado;
    }
}

class EitherParser extends Parser
{
    private Parser p1;
    private Parser p2;
    public EitherParser(Parser p1, Parser p2)
    {
        this.p1 = p1;
        this.p2 = p2;
    }

    public Object parse(String s)
    {
        Object result;
        try {
            result = p1.parse(s);
            finalString = p1.getRemainder();
            return result;
        } catch (NoMatchException e) {
            result = p2.parse(s);
            finalString = p2.getRemainder();
            return result;
        }
    }
}

