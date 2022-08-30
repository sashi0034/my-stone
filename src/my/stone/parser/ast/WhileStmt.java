package my.stone.parser.ast;
import java.util.List;

public class WhileStmt extends ASTList {
    public WhileStmt(List<ASTree> c) { super(c); }
    public ASTree condition() { return child(0); }
    public ASTree body() { return child(1); }
    public String toString() {
        return "(while " + condition() + " " + body() + ")";
    }
}
