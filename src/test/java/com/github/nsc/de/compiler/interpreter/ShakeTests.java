package com.github.nsc.de.compiler.interpreter;

import com.github.nsc.de.compiler.interpreter.values.InterpreterValue;
import com.github.nsc.de.compiler.lexer.Lexer;
import com.github.nsc.de.compiler.lexer.characterinputstream.CharacterInputStream;
import com.github.nsc.de.compiler.lexer.characterinputstream.StringCharacterInputStream;
import com.github.nsc.de.compiler.lexer.token.TokenInputStream;
import com.github.nsc.de.compiler.parser.Parser;
import com.github.nsc.de.compiler.parser.node.Node;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class ShakeTests {

    @ParameterizedTest
    @MethodSource("testStream")
    public void interpreterTests(ShakeTest test) {
        System.out.println("Running test \"%s\"...");
        assertEquals(test.getResult(), run(test.getSourceFile(), test.getCode()).toString());
    }

    static Stream<ShakeTest> testStream() throws FileNotFoundException {
        List<ShakeTest> list = new ArrayList<>();
        Scanner reader = new Scanner(new File("src/test/resources/shake-tests/tests.txt"));
        while (reader.hasNextLine()) {
            list.add(new ShakeTest(reader.nextLine()));

        }
        return Stream.of(new ShakeTest("simple-calc"));
    }

    public InterpreterValue run(String source, String code) {

        CharacterInputStream in = new StringCharacterInputStream(source, code);
        Lexer lexer = new Lexer(in);
        TokenInputStream tokens = lexer.makeTokens();

        Parser parser = new Parser(tokens);
        Node tree = parser.parse();

        Interpreter interpreter = new Interpreter();

        return interpreter.visit(tree);

    }

    public static class ShakeTest {

        private final String name;
        private final String code;
        private final String result;

        ShakeTest(String name) throws FileNotFoundException {
            this.name = name;
            this.code = readFile(new File(String.format("src/test/resources/shake-tests/tests/%s.shake", name)));
            this.result = readFile(new File(String.format("src/test/resources/shake-tests/results/%s.txt", name)));
        }

        public String getName() {
            return name;
        }

        public String getSourceFile() {
            return String.format("test/resources/shake-tests/tests/%s.shake", this.getName());
        }

        public String getCode() {
            return code;
        }

        public String getResult() {
            return result;
        }

        private static String readFile(File f) throws FileNotFoundException {
            Scanner reader = new Scanner(f);
            StringBuilder ret = new StringBuilder();
            while (reader.hasNext()) {
                ret.append(reader.next());
            }
            reader.close();
            return ret.toString();
        }

    }

}
