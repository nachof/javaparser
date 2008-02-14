package parser;

import java.util.List;
import java.util.ArrayList;

import parser.concreteparsers.*;

public abstract class Parser 
{
    public static Parser character = new CharacterParser();
    public static Parser integer = new IntegerParser();

    public static Parser maybe(Parser p) {
        return new MaybeParser(p);
    }
    public static Parser maybe(String s) {
        return maybe(new ConcreteStringParser(s));
    }

    public static Parser repeat(String s) {
        return repeat(new ConcreteStringParser(s));
    }
    public static Parser repeat(Parser p) {
        return new RepeatParser(p);
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
