public class Test
{
    public static void main(String argv[]) {
        Test t = new Test();
        t.runTest();
    }

    public void runTest() {
        Parser p = new Parser() {
            public void parse() {
                swallow(Parser.character);
            }
        };
    }
}
