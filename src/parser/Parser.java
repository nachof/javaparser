package parser;

import java.util.List;
import java.util.ArrayList;

public abstract class Parser 
{
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


    public final Object parse(String s) {
        ParseResult res = doParse(s);
        return res.getObject();
    }

    public abstract ParseResult doParse(String s);
}

class Character extends Parser
{
    public ParseResult doParse(String s) {
        if (s != null && s.length() > 0) {
            return new ParseResult(s.substring(0, 1), s.substring(1));
        } else {
            throw new NoMatchException(s);
        }
    }
}

class IntegerParser extends Parser
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

class NamedString extends Parser
{
    private String word;
    public NamedString(String w) {
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

class Maybe extends Parser
{
    private Parser parser;

    public Maybe(Parser p) {
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

class Repeat extends Parser
{
    private Parser parser;

    public Repeat(Parser p) {
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

class EitherParser extends Parser
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

class ParseResult
{
    private Object retrievedObject;
    private String remainder;

    public ParseResult(Object retrievedObject, String remainder) {
        this.remainder = remainder;
        this.retrievedObject = retrievedObject;
    }

    public Object getObject() {
        return this.retrievedObject;
    }

    public String getRemainder() {
        return this.remainder;
    }
}
