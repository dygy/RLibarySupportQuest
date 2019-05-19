import java.util.List;

public class Main {
    public static void main(String[] args) {
        String text = "g(x)={(f(x)+f((x/2)))} \\n\n"
                + "f(x)={[(x>1)]?{(f((x-1))+f((x-2)))}:{x}} \\n\n"
                + "g(10) \\n\n"
                + "(2+2) \\n\n"
                + "(2+((3*4)/5)) \\n\n"
                + "[((10+20)>(20+10))]?{1}:{0} \\n";
        List<Token> tokens = new Lexer().split(text);
        for (Token token : tokens) {
            System.out.println(token.type + ": " + token.text);

        }
        Parser parser = new Parser(tokens);
        List<BaseStatement> program = parser.program();
    }

}
