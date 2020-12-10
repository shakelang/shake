package com.github.nsc.de.shake;

import com.github.nsc.de.shake.generators.json.JsonGenerator;
import com.github.nsc.de.shake.lexer.Lexer;
import com.github.nsc.de.shake.lexer.characterinputstream.CharacterInputStream;
import com.github.nsc.de.shake.lexer.characterinputstream.StringCharacterInputStream;
import com.github.nsc.de.shake.lexer.token.TokenInputStream;
import com.github.nsc.de.shake.parser.node.Tree;
import com.github.nsc.de.shake.parser.Parser;
import org.json.JSONArray;

import java.util.Scanner;

public class JsonMain {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        while(true) {
            System.out.print("dump into json > ");
            String code = s.nextLine();
            try {
                System.out.println(run("<stdin>", code).toString(2));
            } catch (Error e) {
                System.err.println(" >> Error occurred while running your code: ");
                System.err.println(e.getMessage());
                e.printStackTrace();
            }

        }

    }

    private static JSONArray run(String source, String code) {
        CharacterInputStream in = new StringCharacterInputStream(source, code);
        Lexer lexer = new Lexer(in);
        TokenInputStream tokens = lexer.makeTokens();
        System.out.printf("[DEBUG] Lexer Tokens: %s%n", tokens.toString());
        Parser parser = new Parser(tokens);
        Tree tree = parser.parse();
        System.out.printf("[DEBUG] Parser Tree: %s%n", tree.toString());
        return new JsonGenerator().visitTree(tree);
    }
}
