import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
class Lexer {

    private static TokenType toType(int i) {
        switch (i) {
            case 1: return TokenType.EQL;
            case 2: return TokenType.DIVIDE;
            case 3: return TokenType.EQUAL;
            case 4: return TokenType.LESSTHAN;
            case 5: return TokenType.GREATERTHAN;
            case 6: return TokenType.NUMBER;
            case 7: return TokenType.IDENT;
            case 8: return TokenType.MINUS;
            case 9: return TokenType.PLUS;
            case 10: return TokenType.PRECENT;
            case 11: return TokenType.QUANTIFY;
            case 12: return TokenType.OPENFORM;
            case 13: return TokenType.CLOSEFORM;
            case 14: return TokenType.OPENSTANDART;
            case 15: return TokenType.CLOSESTANDART;
            case 16: return TokenType.OPENSQUARE;
            case 17: return TokenType.CLOSESQUARE;
            case 18: return TokenType.QUESTIONMARK;
            case 19: return TokenType.DOBULEPOINT;
        }
        throw new IllegalStateException();
    }
    List<Token> split(String text) {

        final String regex = "(\n)|(/)|(=)|(<)|(>)|(\\d+)|([A-Za-z_])|(-)|(\\+)|(%)|(\\*)|(\\{)|(})|(\\()|(\\))|(\\[)|(\\])|(\\?)|(:)";
        List<Token> list = new ArrayList<>();
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            for (int i = 1; i <= matcher.groupCount(); i++) {
                if (matcher.group(i)!=null) {
                    list.add(new Token(matcher.group(i), toType(i)));
                }
            }
        }
        return list;
    }

}