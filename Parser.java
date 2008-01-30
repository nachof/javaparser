public abstract class Parser 
{
    protected String originalString;
    protected String finalString;

    public static Parser character = new Character();

    public static Parser maybe(Parser p) {
        return new Maybe(p);
    }
    public static Parser maybe(String s) {
        return maybe(new NamedString(s));
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
            throw new NoMatchException();
        }
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
            throw new NoMatchException();
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
