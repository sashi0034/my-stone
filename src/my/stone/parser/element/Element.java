package my.stone.parser.element;

import my.stone.ParseException;
import my.stone.Token;
import my.stone.lexer.Lexer;
import my.stone.parser.Parser;
import my.stone.parser.ast.ASTLeaf;
import my.stone.parser.ast.ASTList;
import my.stone.parser.ast.ASTree;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public abstract class Element {
    public abstract void parse(Lexer lexer, List<ASTree> res)
            throws ParseException;
    public abstract boolean match(Lexer lexer) throws ParseException;
}


