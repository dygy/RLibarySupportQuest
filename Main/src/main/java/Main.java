import java.util.List;

public class Main {
    public static List<GroupsNode> list;
    public static void main(String[] args) {
        String text =
                "(2+2+2)";
        List<Token> tokens = new Lexer().split(text);
        for (Token token : tokens) {
            System.out.println(token.type + ": " + token.text);

        }
        Parser parser = new Parser(tokens);
        parser.program();
    }

}
