import java.util.List;

public class Main {
    public static void main(String[] args) {
        String text =
                "(2+2) \n";
        List<Token> tokens = new Lexer().split(text);
        for (Token token : tokens) {
            System.out.println(token.type + ": " + token.text);

        }
        Parser parser = new Parser(tokens);
        List<BaseStatement> program = parser.program();
    }

}
