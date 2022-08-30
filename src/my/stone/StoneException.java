package my.stone;

import my.stone.parser.ast.ASTree;

public class StoneException extends RuntimeException{
    public StoneException(String m){super(m);}

    public StoneException(String m, ASTree t) {
        super(m + " " + t.location());
    }
}
