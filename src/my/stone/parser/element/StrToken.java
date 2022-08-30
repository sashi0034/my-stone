package my.stone.parser.element;

import my.stone.Token;
import my.stone.parser.ast.ASTLeaf;

public class StrToken extends AToken {
    public StrToken(Class<? extends ASTLeaf> type) {
        super(type);
    }

    protected boolean test(Token t) {
        return t.isString();
    }
}
