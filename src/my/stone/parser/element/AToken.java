package my.stone.parser.element;

import my.stone.ParseException;
import my.stone.Token;
import my.stone.lexer.Lexer;
import my.stone.parser.ast.ASTLeaf;
import my.stone.parser.ast.ASTree;

import java.util.List;

public abstract class AToken extends Element {
    protected Factory factory;

    protected AToken(Class<? extends ASTLeaf> type) {
        if (type == null)
            type = ASTLeaf.class;
        factory = Factory.get(type, Token.class);
    }

    public void parse(Lexer lexer, List<ASTree> res)
            throws ParseException {
        Token t = lexer.read();
        if (test(t)) {
            ASTree leaf = factory.make(t);
            res.add(leaf);
        } else
            throw new ParseException(t);
    }

    public boolean match(Lexer lexer) throws ParseException {
        return test(lexer.peek(0));
    }

    protected abstract boolean test(Token t);
}
