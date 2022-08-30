package my.stone.ast;

import my.stone.Token;

import java.util.Iterator;
import java.util.ArrayList;

public class ASTLeaf extends ASTree {
    private static final ArrayList<ASTree> empty = new ArrayList<ASTree>();
    private final Token token;
    public ASTLeaf(Token t) { token = t; }
    public ASTree child(int i) { throw new IndexOutOfBoundsException(); }
    public int numChildren() { return 0; }
    public Iterator<ASTree> children() { return empty.iterator(); }
    public String toString() { return token.getText(); }
    public String location() { return "at line " + token.getLineNumber(); }
    public Token token() { return token; }
}
