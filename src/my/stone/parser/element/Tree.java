package my.stone.parser.element;

import my.stone.ParseException;
import my.stone.lexer.Lexer;
import my.stone.parser.Parser;
import my.stone.parser.ast.ASTree;

import java.util.List;

public class Tree extends Element {
    protected Parser parser;

    public Tree(Parser p) {
        parser = p;
    }

    public void parse(Lexer lexer, List<ASTree> res)
            throws ParseException {
        res.add(parser.parse(lexer));
    }

    public boolean match(Lexer lexer) throws ParseException {
        return parser.match(lexer);
    }
}
