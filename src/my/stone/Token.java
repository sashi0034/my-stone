
package my.stone;

public abstract class Token {
    private int lineNumber;

    protected Token(int line){
        lineNumber = line;
    }

    public int getLineNumber(){return lineNumber;}
    public boolean isIdentifier(){return false;}
    public boolean isNumber(){return false;}
    public boolean isString(){return false;}
    public int getNumber(){throw new StoneException("nut number token");}
    public String getText(){return "";}

    public static final Token EOF = new Token(-1) {};
    public static final String EOL = "\\n";

}
