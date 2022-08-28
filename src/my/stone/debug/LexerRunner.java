package my.stone.debug;

import my.stone.ParseException;
import my.stone.Token;
import my.stone.lexer.Lexer;

public class LexerRunner {
    public static void main(String[] args) throws ParseException {
        Lexer lexer = new Lexer(new CodeDialog());
        for (Token token; (token = lexer.read()) != Token.EOF; )
            System.out.println("=> " + token.getText());
    }
}
