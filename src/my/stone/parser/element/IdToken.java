package my.stone.parser.element;

import my.stone.Token;
import my.stone.parser.ast.ASTLeaf;

import java.util.HashSet;

public class IdToken extends AToken {
    HashSet<String> reserved;

    public IdToken(Class<? extends ASTLeaf> type, HashSet<String> r) {
        super(type);
        reserved = r != null ? r : new HashSet<String>();
    }

    protected boolean test(Token t) {
        return t.isIdentifier() && !reserved.contains(t.getText());
    }
}
