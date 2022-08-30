package my.stone.parser.element;

import my.stone.ParseException;
import my.stone.lexer.Lexer;
import my.stone.parser.Parser;
import my.stone.parser.ast.ASTList;
import my.stone.parser.ast.ASTree;

import java.util.List;

public class Repeat extends Element {
    protected Parser parser;
    protected boolean onlyOnce;

    public Repeat(Parser p, boolean once) {
        parser = p;
        onlyOnce = once;
    }

    public void parse(Lexer lexer, List<ASTree> res)
            throws ParseException {
        while (parser.match(lexer)) {
            ASTree t = parser.parse(lexer);
            if (t.getClass() != ASTList.class || t.numChildren() > 0)
                res.add(t);
            if (onlyOnce)
                break;
        }
    }

    public boolean match(Lexer lexer) throws ParseException {
        return parser.match(lexer);
    }
}
