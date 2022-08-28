package my.stone.lexer;

import my.stone.Token;

class NumToken extends Token {
    private final int value;

    protected NumToken(int line, int v) {
        super(line);
        value = v;
    }
    public boolean isNumber() { return true; }
    public String getText() { return Integer.toString(value); }
    public int getNumber() { return value; }
}

class IdToken extends Token {
    private final String text;
    protected IdToken(int line, String id) {
        super(line);
        text = id;
    }
    public boolean isIdentifier() { return true; }
    public String getText() { return text; }
}

class StrToken extends Token {
    private final String literal;
    StrToken(int line, String str) {
        super(line);
        literal = str;
    }
    public boolean isString() { return true; }
    public String getText() { return literal; }
}
