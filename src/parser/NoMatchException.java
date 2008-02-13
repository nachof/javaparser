package parser;

public class NoMatchException extends RuntimeException
{
    public NoMatchException(String unparsableInput) {
        super("Impossible to parse: " + unparsableInput);
    }
}
