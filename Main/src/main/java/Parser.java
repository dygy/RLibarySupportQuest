

import java.util.ArrayList;
import java.util.List;

class Parser {

    private static List<Token> tokens;
    private static int pos = 0;

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

    /**
     * Если текущая лексема имеет тип type, то возвращает текущую лексему и переходит к следующей лексеме; иначе
     * возвращает null.
     */
    private static Token match(TokenType type) {

        if (pos >= tokens.size())
            return null;

        Token currentToken = tokens.get(pos);

        if (currentToken.type != type)
            return null;

        pos++;
        return currentToken;
    }
    private static Token checkSide() {
        Token toReturn;
        if ((toReturn = match(TokenType.NUMBER)) != null) {}
        else if ((toReturn= match(TokenType.IDENT)) != null){}
        else error("Missed variable");
        return toReturn;
    }



    List<BaseStatement> program() {
        List<BaseStatement> statements = new ArrayList<>();
        while (pos < tokens.size()) {

        }
        return statements;
    }
}