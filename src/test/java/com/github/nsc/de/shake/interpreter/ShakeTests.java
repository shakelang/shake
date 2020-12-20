package com.github.nsc.de.shake.interpreter;

import com.github.nsc.de.shake.interpreter.values.InterpreterValue;
import com.github.nsc.de.shake.lexer.Lexer;
import com.github.nsc.de.shake.lexer.characterinput.characterinputstream.CharacterInputStream;
import com.github.nsc.de.shake.lexer.characterinput.characterinputstream.SourceCharacterInputStream;
import com.github.nsc.de.shake.lexer.token.TokenInputStream;
import com.github.nsc.de.shake.parser.Parser;
import com.github.nsc.de.shake.parser.node.Node;
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
        System.out.printf("Running test \"%s\"... in \"%s\"%n", test.getName(), test.getSourceFile());
        assertEquals(test.getResult(), run(test.getSourceFile(), test.getCode()).toString());
    }

    static Stream<ShakeTest> testStream() throws FileNotFoundException {
        List<ShakeTest> list = new ArrayList<>();
        Scanner reader = new Scanner(new File("src/test/resources/shake-tests/tests.txt"));
        while (reader.hasNextLine()) {
            list.add(new ShakeTest(reader.nextLine()));

        }
        return Stream.of(list.toArray(new ShakeTest[] {}));
    }

    public InterpreterValue run(String source, String code) {

        CharacterInputStream in = new SourceCharacterInputStream(source, code);
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
            while (reader.hasNextLine()) {
                ret.append(reader.nextLine()).append('\n');
            }
            if(ret.length() > 0) ret.setLength(ret.length() - 1);

            reader.close();
            return ret.toString();
        }

        @Override
        public String toString() {
            return getName();
        }
    }

}
