package my.stone.parser.element;

import my.stone.Token;
import my.stone.parser.ast.ASTLeaf;

public class NumToken extends AToken {
    public NumToken(Class<? extends ASTLeaf> type) {
        super(type);
    }

    protected boolean test(Token t) {
        return t.isNumber();
    }
}
