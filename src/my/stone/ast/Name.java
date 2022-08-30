package my.stone.ast;

import my.stone.Token;

public class Name extends ASTLeaf {
    public Name(Token t) { super(t); }
    public String name() { return token().getText(); }
}

