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
                swallow(Parser.character);
                swallow("-");
                capture("character", Parser.character);
            }
        };
        Map h = (Map) p.parse("A-B");
    }
}
