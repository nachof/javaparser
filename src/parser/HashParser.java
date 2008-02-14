package parser;

import java.util.Stack;
import java.util.EmptyStackException;
import java.util.Map;
import java.util.HashMap;

import parser.concreteparsers.ConcreteStringParser;

public abstract class HashParser extends Parser
{
    private Map retorno;
    private String finalString;

    private Stack stateStack = new Stack();

    protected void discard(Parser p) {
        p.parse(finalString);
        finalString = p.doParse(finalString).getRemainder();
    }

    protected void discard(String s){
        Parser p = new ConcreteStringParser(s);
        finalString = p.doParse(finalString).getRemainder();
    }

    protected void capture(String name, Parser p) {
        ParseResult result = p.doParse(finalString);
        retorno.put(name, result.getObject());
        finalString = result.getRemainder();
    }

    public ParseResult doParse(String s) {
        saveState();
        finalString = s;
        retorno = new HashMap();
        runParser();
        ParseResult resultado = new ParseResult(retorno, finalString);
        restoreState();
        return resultado;
    }

    private void saveState() {
        stateStack.push(new HashParserState(retorno, finalString));
    }

    private void restoreState() {
        try {
            HashParserState state = (HashParserState) stateStack.pop();
            retorno = state.getRetorno();
            finalString = state.getFinalString();
        } catch (EmptyStackException e) {
            // Do nothing -- no state to restore (shouldn't happen, in fact
        }
    }

    private class HashParserState {
        private Map retorno;
        private String finalString;

        public HashParserState(Map retorno, String finalString) {
            if (retorno == null)
                this.retorno = new HashMap();
            else
                this.retorno = new HashMap(retorno);
            if (finalString == null) 
                this.finalString = "";
            else
                this.finalString = new String(finalString);
        }

        public Map getRetorno() {
            return this.retorno;
        }

        public String getFinalString() {
            return this.finalString;
        }
    }

    public abstract void runParser();
}
