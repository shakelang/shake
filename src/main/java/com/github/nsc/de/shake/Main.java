package com.github.nsc.de.shake;

import com.github.nsc.de.shake.interpreter.Interpreter;
import com.github.nsc.de.shake.lexer.Lexer;
import com.github.nsc.de.shake.lexer.characterinputstream.CharacterInputStream;
import com.github.nsc.de.shake.lexer.characterinputstream.StringCharacterInputStream;
import com.github.nsc.de.shake.lexer.token.TokenInputStream;
import com.github.nsc.de.shake.parser.Parser;
import com.github.nsc.de.shake.parser.node.Node;

import java.util.Scanner;

public class Main {
    static Interpreter interpreter = new Interpreter();

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        while(true) {
            System.out.print("interpreter > ");
            String code = s.nextLine();
            try {
                System.out.println(" >> " + run("<stdin>", code));
            } catch (Exception | Error e) {
                System.err.println(" >> Error occurred while running your code: ");
                System.err.println(e.getMessage());
                e.printStackTrace();
            }

        }

    }

    private static Object run(String source, String code) {
        CharacterInputStream in = new StringCharacterInputStream(source, code);
        Lexer lexer = new Lexer(in);
        TokenInputStream tokens = lexer.makeTokens();
        System.out.printf("[DEBUG] Lexer Tokens: %s%n", tokens.toString());
        Parser parser = new Parser(tokens);
        Node tree = parser.parse();
        System.out.printf("[DEBUG] Parser Tree: %s%n", tree.toString());
        return interpreter.visit(tree);
    }
}
