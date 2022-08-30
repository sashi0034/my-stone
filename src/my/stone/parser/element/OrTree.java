package my.stone.parser.element;

import my.stone.ParseException;
import my.stone.lexer.Lexer;
import my.stone.parser.Parser;
import my.stone.parser.ast.ASTree;

import java.util.List;

public class OrTree extends Element {
    protected Parser[] parsers;

    public OrTree(Parser[] p) {
        parsers = p;
    }

    public void parse(Lexer lexer, List<ASTree> res)
            throws ParseException {
        Parser p = choose(lexer);
        if (p == null)
            throw new ParseException(lexer.peek(0));
        else
            res.add(p.parse(lexer));
    }

    public boolean match(Lexer lexer) throws ParseException {
        return choose(lexer) != null;
    }

    protected Parser choose(Lexer lexer) throws ParseException {
        for (Parser p : parsers)
            if (p.match(lexer))
                return p;

        return null;
    }

    public void insert(Parser p) {
        Parser[] newParsers = new Parser[parsers.length + 1];
        newParsers[0] = p;
        System.arraycopy(parsers, 0, newParsers, 1, parsers.length);
        parsers = newParsers;
    }
}
