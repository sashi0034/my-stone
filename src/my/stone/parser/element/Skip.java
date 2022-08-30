package my.stone.parser.element;

import my.stone.Token;
import my.stone.parser.ast.ASTree;

import java.util.List;

public class Skip extends Leaf {
    public Skip(String[] t) {
        super(t);
    }

    protected void find(List<ASTree> res, Token t) {
    }
}
