package com.github.nsc.de.compiler.parser;

import com.github.nsc.de.compiler.lexer.Lexer;
import com.github.nsc.de.compiler.lexer.characterinputstream.CharacterInputStream;
import com.github.nsc.de.compiler.lexer.characterinputstream.StringCharacterInputStream;
import com.github.nsc.de.compiler.lexer.token.TokenInputStream;
import com.github.nsc.de.compiler.parser.node.Node;
import com.github.nsc.de.compiler.parser.node.Tree;
import com.github.nsc.de.compiler.parser.parser.Parser;

import static org.junit.jupiter.api.Assertions.*;
import static com.github.nsc.de.compiler.TestUtil.*;

public class ParserTestUtil {

    public static Tree parse(String source, String input) {

        CharacterInputStream in = new StringCharacterInputStream(source, input);
        Lexer lexer = new Lexer(in);
        TokenInputStream tokens = lexer.makeTokens();
        Parser parser = new Parser(tokens);

        return parser.parse();

    }

    @SuppressWarnings("unchecked")
    public static  <T extends Node> T parseSingle(String source, String input, Class<T> type) {

        Tree tree = parse(source, input);
        Node[] nodes = tree.getChildren();

        // Result should be 1 node
        assertEquals(1, nodes.length);

        // result is expected class
        assertNotNull(nodes[0]);
        assertType(type, nodes[0]);

        return (T) nodes[0];

    }
}
