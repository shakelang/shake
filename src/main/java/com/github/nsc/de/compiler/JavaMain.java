package com.github.nsc.de.compiler;

import com.github.nsc.de.compiler.generators.java.JavaGenerator;
import com.github.nsc.de.compiler.lexer.Lexer;
import com.github.nsc.de.compiler.lexer.characterinputstream.CharacterInputStream;
import com.github.nsc.de.compiler.lexer.characterinputstream.StringCharacterInputStream;
import com.github.nsc.de.compiler.lexer.token.TokenInputStream;
import com.github.nsc.de.compiler.parser.Parser;
import com.github.nsc.de.compiler.parser.node.Tree;

import java.util.Scanner;

public class JavaMain {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        while(true) {
            System.out.print("dump into java > ");
            String code = s.nextLine();
            try {
                System.out.println(run("<stdin>", code));
            } catch (Error e) {
                System.err.println(" >> Error occurred while running your code: ");
                System.err.println(e.getMessage());
                e.printStackTrace();
            }

        }

    }

    private static String run(String source, String code) {
        CharacterInputStream in = new StringCharacterInputStream(source, code);
        Lexer lexer = new Lexer(in);
        TokenInputStream tokens = lexer.makeTokens();
        System.out.printf("[DEBUG] Lexer Tokens: %s%n", tokens.toString());
        Parser parser = new Parser(tokens);
        Tree tree = parser.parse();
        System.out.printf("[DEBUG] Parser Tree: %s%n", tree.toString());
        return new JavaGenerator().visitTree(tree);
    }
}
