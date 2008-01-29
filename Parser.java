public abstract class Parser 
{
    public static Parser character = new Character();

    public abstract void runParser();

    public Object parse(String s) {
        return null;
    }
}

class Character extends Parser
{
    public void runParser(){
    }
}
