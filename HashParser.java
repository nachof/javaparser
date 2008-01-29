public abstract class HashParser extends Parser
{
    protected void discard(Parser p) {
    }
    protected void discard(String s){
    }
    protected void capture(String name, Parser p) {
    }

    public Object parse(String s) {
        return null;
    }

    public abstract void runParser();
}
