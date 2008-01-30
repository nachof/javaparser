import java.util.Map;

public class Test
{
    public static void main(String argv[]) {
        Test t = new Test();
        t.runTest();
    }

    public void runTest() {
        Parser p = new HashParser() {
            public void runParser() {
                discard(Parser.character);
                discard(Parser.repeat("-"));
                capture("character", Parser.character);
            }
        };
        Map h = (Map) p.parse("A--B");
        System.out.println(h.get("character"));
        /*
        Parser parserHora = new  HashParser () {
            public void runParser() {
                capture("hora", Parser.number);
                discard(":");
                capture("minutos", Parser.number);
            }
        }
        */
    }
}
