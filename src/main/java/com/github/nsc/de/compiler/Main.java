package com.github.nsc.de.compiler;

import com.github.nsc.de.compiler.interpreter.Interpreter;
import com.github.nsc.de.compiler.lexer.Lexer;
import com.github.nsc.de.compiler.lexer.characterinputstream.CharacterInputStream;
import com.github.nsc.de.compiler.lexer.characterinputstream.StringCharacterInputStream;
import com.github.nsc.de.compiler.lexer.token.TokenInputStream;
import com.github.nsc.de.compiler.parser.Parser;
import com.github.nsc.de.compiler.parser.node.Node;

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
            } catch (Error e) {
                System.err.println(" >> Error occurred while running your code: ");
                e.printStackTrace();
            }

        }

    }

    private static Object run(String source, String code) {
        CharacterInputStream in = new StringCharacterInputStream(source, code);
        Lexer lexer = new Lexer(in);
        TokenInputStream tokens = lexer.makeTokens();
        Parser parser = new Parser(tokens);
        Node tree = parser.parse();
        System.out.println(tree);
        return interpreter.visit(tree).getValue();
    }
}
