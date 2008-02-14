import java.util.Map;
import java.util.List;

import parser.Parser;
import parser.HashParser;

public class Test
{
    public static void main(String argv[]) {
        Test t = new Test();
        t.runTest();
    }

    public void runTest() {
        Parser p = new HashParser() {
            public void runParser() {
                discard(character);
                discard(repeat("-"));
                capture("character", character);
            }
        };
        Map h = (Map) p.parse("A--B");
        System.out.println(h.get("character"));
        Parser parserHora = new  HashParser () {
            public void runParser() {
                capture("hora", integer);
                discard(":");
                capture("minutos", integer);
            }
        };
        String hora = "15:38";
        Map m = (Map) parserHora.parse(hora);
        System.out.println("Hora: " + m.get("hora") + "\nMinutos: " + m.get("minutos"));
        final Parser testRecursivo = new HashParser() {
            public void runParser() {
                discard("(");
                capture("v", either(this, character));
                discard(")");
            }
        };
        String parens = "((a))";
        Map mr = (Map) testRecursivo.parse(parens);
        System.out.println(mr.get("v"));
        System.out.println(((Map)mr.get("v")).get("v"));

        List l = (List) Parser.repeat(Parser.character).parse("1234567890");
        System.out.println(l);

        List l2 = (List) Parser.lines(Parser.integer).parse("23\n456\n789");
        System.out.println(l2);
    }
}
