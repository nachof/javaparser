public abstract class Parser 
{
    private String originalString;
    private String finalString;

    public static Parser character = new Character();

    public abstract void runParser();

    public Object parse(String s) {
        return null;
    }

    public String getRemainder() {
        return finalString;
    }
}

class Character extends Parser
{
    public void runParser(){
    }
}
