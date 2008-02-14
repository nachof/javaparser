package parser;

public class ParseResult
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

