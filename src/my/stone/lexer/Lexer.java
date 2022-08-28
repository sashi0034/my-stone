package my.stone.lexer;

import my.stone.ParseException;
import my.stone.Token;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static my.stone.lexer.RegexPad.getRegexPad;

public class Lexer {
    private static final String REGEX_PAD = getRegexPad();

    private final Pattern pattern = Pattern.compile(REGEX_PAD);
    private ArrayList<Token> queue = new ArrayList<Token>();
    private boolean hasMore;
    private LineNumberReader reader;


    public Lexer(Reader reader){
        hasMore = true;
        reader = new LineNumberReader(reader);
    }

    public Token read() throws ParseException {
        if (fillQueue(0))
            return queue.remove(0);
        else
            return Token.EOF;
    }

    private boolean fillQueue(int index) throws ParseException{
        while (index>queue.size()){
            if (hasMore)
                readLine();
            else
                return false;
        }
        return true;
    }

    protected void readLine() throws ParseException{
        String line = tryReadLine();
        if (line == null) return;
        int lineNo = reader.getLineNumber();
        Matcher matcher = pattern.matcher(line);
        matcher.useTransparentBounds(true).useAnchoringBounds(false);

        int currPos = 0;
        int endPos = line.length();
        while (currPos < endPos) {
            matcher.region(currPos, endPos);
            if (matcher.lookingAt()) {
                addToken(lineNo, matcher);
                currPos = matcher.end();
            }
            else
                throw new ParseException("bad token at line " + lineNo);
        }
        queue.add(new IdToken(lineNo, Token.EOL));
    }

    private String tryReadLine() throws ParseException {
        String line;
        try {
            line = reader.readLine();
        } catch (IOException e) {
            throw new ParseException(e);
        }
        if (line == null) {
            hasMore = false;
            return null;
        }
        return line;
    }

    protected void addToken(int lineNo, Matcher matcher) {
        String matchedNonSpace = matcher.group(RegexPadGroup.NON_SPACE.getGroup());

        // スペースのとき
        if (matchedNonSpace == null) return;

        // コメントのとき
        if (matcher.group(RegexPadGroup.COMMENT.getGroup()) != null) return;

        Token token;
        if (matcher.group(RegexPadGroup.NUMBER_LITERAL.getGroup()) != null)
            token = new NumToken(lineNo, Integer.parseInt(matchedNonSpace));
        else if (matcher.group(RegexPadGroup.STRING_LITERAL.getGroup()) != null)
            token = new StrToken(lineNo, toStringLiteral(matchedNonSpace));
        else
            token = new IdToken(lineNo, matchedNonSpace);
        queue.add(token);
    }

    protected String toStringLiteral(String baseStr) {
        StringBuilder stringBuilder = new StringBuilder();
        int len = baseStr.length() - 1;
        for (int i = 1; i < len; i++) {
            char checkingChar = baseStr.charAt(i);
            if (checkingChar == '\\' && i + 1 < len) {
                char escapedChar = baseStr.charAt(i + 1);
                if (escapedChar == '"' || escapedChar == '\\')
                    checkingChar = baseStr.charAt(++i);
                else if (escapedChar == 'n') {
                    ++i;
                    checkingChar = '\n';
                }
            }
            stringBuilder.append(checkingChar);
        }
        return stringBuilder.toString();
    }
}
