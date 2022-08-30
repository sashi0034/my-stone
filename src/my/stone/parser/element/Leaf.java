package my.stone.parser.element;

import my.stone.ParseException;
import my.stone.Token;
import my.stone.lexer.Lexer;
import my.stone.parser.ast.ASTLeaf;
import my.stone.parser.ast.ASTree;

import java.util.List;

public class Leaf extends Element {
    protected String[] tokens;

    public Leaf(String[] pat) {
        tokens = pat;
    }

    public void parse(Lexer lexer, List<ASTree> res)
            throws ParseException {
        Token t = lexer.read();
        if (t.isIdentifier())
            for (String token : tokens)
                if (token.equals(t.getText())) {
                    find(res, t);
                    return;
                }

        if (tokens.length > 0)
            throw new ParseException(tokens[0] + " expected.", t);
        else
            throw new ParseException(t);
    }

    protected void find(List<ASTree> res, Token t) {
        res.add(new ASTLeaf(t));
    }

    public boolean match(Lexer lexer) throws ParseException {
        Token t = lexer.peek(0);
        if (t.isIdentifier())
            for (String token : tokens)
                if (token.equals(t.getText()))
                    return true;

        return false;
    }
}
