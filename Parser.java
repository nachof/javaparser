public abstract class Parser 
{
    public static Parser character = new Character();

    public abstract void parse();

    void swallow(Parser p) {
    }
}

class Character extends Parser
{
    public void parse(){
    }
}
