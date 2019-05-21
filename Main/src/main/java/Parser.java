import java.util.List;
class Parser {

    private static List<Token> tokens;
    private static int pos = 0;
    static boolean isOp (int pos){
        return tokens.get(pos).type == TokenType.PLUS
                || tokens.get(pos).type == TokenType.MINUS
                || tokens.get(pos).type == TokenType.QUANTIFY
                || tokens.get(pos).type == TokenType.DIVIDE
                || tokens.get(pos).type == TokenType.PRECENT;
    }
    static boolean isGoodOp (int pos){
        return  tokens.get(pos).type == TokenType.QUANTIFY
                || tokens.get(pos).type == TokenType.DIVIDE
                || tokens.get(pos).type == TokenType.PRECENT;
    }
    Parser(List<Token> tokens) {
        Parser.tokens = tokens;
    }

    // <character>  ::= "A" | "B" | "C" | "D" | "E" | "F" | "G" | "H" | "I" | "J" | "K" | "L" | "M" | "N" | "O" | "P" | "Q" | "R" | "S" | "T" | "U" | "V" | "W" | "X" | "Y" | "Z" | "a" | "b" | "c" | "d" | "e" | "f" | "g" | "h" | "i" | "j" | "k" | "l" | "m" | "n" | "o" | "p" | "q" | "r" | "s" | "t" | "u" | "v" | "w" | "x" | "y" | "z" | "_"
    //<digit>   ::= "0" | "1" | "2" | "3" | "4" | "5" | "6" | "7" | "8" | "9"
    //<number> ::= <digit> | <digit> <number>
    //<identifier> ::= <character> | <identifier> <character>
    //<operation> ::= "+" | "-" | "*" | "/" | "%" | ">" | "<" | "="
    //
    //<constant-expression> ::= "-" <number> | <number>
    //<binary-expression> ::= "(" <expression> <operation> <expression>  ")"
    //<argument-list> ::= <expression> | <expression> "," <argument-list>
    //<call-expression> ::= <identifier> "(" <argument-list> ")"
    //<if-expression> ::= "[" <expression> "]?(" <expression> "):("<expression>")"
    //
    //
    //<expression> ::= <identifier>
    //                  | <constant-expression>
    //                  | <binary-expression>
    //                  | <if-expression>
    //                  | <call-expression>
    //
    //<parameter-list> ::= <identifier> | <identifier> "," <parameter-list>
    //
    //<function-definition> ::= <identifier>"(" <parameter_list> ")" "={" <expression> "}"
    //
    //<function-definition-list> : ""
    //                             | <function-definition> <EOL>
    //                             | <function-definition> <EOL> <function-definition-list>
    //
    //<program> ::= <function-definition-list> <expression>
    //<EOL> - символ перевода строки --- \n

    private static void error(String message) {
        throw new IllegalArgumentException(message);
    }


   private static boolean right=false;
   private static boolean left=false;
    private static void checkInside(int pos) {

        int newPos = pos;
        while (!left){
            checkLeft(newPos);
            newPos--;
        }
        newPos = pos;
        while (!right){
            checkRight(newPos);
            newPos++;
        }
    }
    private static void checkLeft(int pos) {
        if (tokens.get(pos-1).type==TokenType.NUMBER||tokens.get(pos-1).type==TokenType.IDENT){
        left=true;
        }
        else if (tokens.get(pos-1).type==TokenType.CLOSESTANDART){
        }
        else{
            error("Missing Variable left to operator");
        }
    }
    private static void checkRight(int pos) {
        if (tokens.get(pos+1).type==TokenType.NUMBER||tokens.get(pos+1).type==TokenType.IDENT){
            right=true;
        }
        else if (tokens.get(pos+1).type==TokenType.OPENSTANDART){
        }
        else{
            error("Missing Variable right to operator");
        }
    }
    static int raiting;
    private static GroupsNode gettingGroup(int pos) {
        Object leftValue=null;
        Object rightValue=null;
        Token op =null;
        raiting+=2;
        if (tokens.get(pos+1).type== TokenType.CLOSESTANDART){
            error("Empty groups not allowed");
        }
        for (int i = pos+1; i < tokens.size(); i++) {
            Token token = tokens.get(i);
            if (token.type == TokenType.IDENT || token.type == TokenType.NUMBER) {
                if (leftValue == null) {
                    leftValue = token;
                }
                if (rightValue == null) {
                    rightValue = token;
                }
                else error("too many values for 1 group at " + tokens.get(pos).text);
            }
            if (token.type == TokenType.OPENSTANDART){
                System.out.println(token.text);
                if (leftValue == null) {
                    leftValue = gettingGroup(i);
                }
                if (rightValue == null) {
                    rightValue = gettingGroup(i);
                }
                else error("too many values for 1 group at " + tokens.get(pos).text);
            }
            if (isOp(i)){
             op=token;
            }
            if (token.type == TokenType.CLOSESTANDART) {
                break;
            }

        }
        return formatGroup(leftValue,rightValue,op,raiting);
    }
        //Функция для формирования групп
    private static GroupsNode formatGroup(Object leftValue, Object rightValue, Token op, int rating) {
        return new GroupsNode(leftValue,rightValue,op,rating);
    }
    //здесь будет подсчет скобок
    private static boolean checkSParentheses(){
        int openStandart=0;
        int openSquare=0;
        int openForm=0;
        //по хорошему это можно считать внутри program, но я решил,
        // что так будет сразу откидывать некомпилируемые программы.
        for (Token token : tokens) {
            if (openForm < 0 || openStandart < 0 || openSquare < 0) {
                error("You can't close group that wasn't started yet");
            }
            if (token.type == TokenType.OPENFORM) { openForm++; }
            else if (token.type == TokenType.OPENSQUARE) { openSquare++; }
            else if (token.type == TokenType.OPENSTANDART) { openStandart++; }
            else if (token.type == TokenType.CLOSESTANDART) { openStandart--; }
            else if (token.type == TokenType.CLOSESQUARE) { openSquare--; }
            else if (token.type == TokenType.CLOSEFORM) { openForm--; }
        }
    if (openSquare==openForm&&openStandart==openSquare&&openForm==0) {
        return true;
    }
    else if (openForm>0){
    error(" \" { \" is missing");
    }
    else if (openForm<0){
        error(" \" } \" is missing");
    }
    else if (openSquare>0){
        error(" \" [ \" is missing");
    }
    else if (openSquare<0){
        error(" \" ] \" is missing");
    }
    else if (openStandart>0){
        error(" \" ) \" is missing");
    }
    else if (openStandart<0){
        error(" \" ( \" is missing");
    }
        return false;
    }


    static void program() {
        checkSParentheses();
        while (pos < tokens.size()) {
         //   System.out.print(tokens.get(pos).text);
            if (isOp(pos)){
                checkInside(pos);
            }
            if (tokens.get(pos).type==TokenType.OPENSTANDART){
               Main.list.add(gettingGroup(pos));
               raiting-=2;
            }
            pos++;
        }
    }

}