package my.stone.parser.element;

import my.stone.ParseException;
import my.stone.Token;
import my.stone.lexer.Lexer;
import my.stone.parser.Parser;
import my.stone.parser.ast.ASTLeaf;
import my.stone.parser.ast.ASTree;

import java.util.ArrayList;
import java.util.List;

public class Expr extends Element {
    protected Factory factory;
    protected Operators ops;
    protected Parser factor;

    public Expr(Class<? extends ASTree> clazz, Parser exp,
                Operators map) {
        factory = Factory.getForASTList(clazz);
        ops = map;
        factor = exp;
    }

    public void parse(Lexer lexer, List<ASTree> res) throws ParseException {
        ASTree right = factor.parse(lexer);
        Precedence prec;
        while ((prec = nextOperator(lexer)) != null)
            right = doShift(lexer, right, prec.value);

        res.add(right);
    }

    private ASTree doShift(Lexer lexer, ASTree left, int prec)
            throws ParseException {
        ArrayList<ASTree> list = new ArrayList<ASTree>();
        list.add(left);
        list.add(new ASTLeaf(lexer.read()));
        ASTree right = factor.parse(lexer);
        Precedence next;
        while ((next = nextOperator(lexer)) != null
                && rightIsExpr(prec, next))
            right = doShift(lexer, right, next.value);

        list.add(right);
        return factory.make(list);
    }

    private Precedence nextOperator(Lexer lexer) throws ParseException {
        Token t = lexer.peek(0);
        if (t.isIdentifier())
            return ops.get(t.getText());
        else
            return null;
    }

    private static boolean rightIsExpr(int prec, Precedence nextPrec) {
        if (nextPrec.leftAssoc)
            return prec < nextPrec.value;
        else
            return prec <= nextPrec.value;
    }

    public boolean match(Lexer lexer) throws ParseException {
        return factor.match(lexer);
    }
}
