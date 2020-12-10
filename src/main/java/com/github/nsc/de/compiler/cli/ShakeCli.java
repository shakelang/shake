package com.github.nsc.de.compiler.cli;

import com.github.nsc.de.compiler.generators.json.JsonGenerator;
import com.github.nsc.de.compiler.interpreter.Interpreter;
import com.github.nsc.de.compiler.lexer.Lexer;
import com.github.nsc.de.compiler.lexer.characterinputstream.CharacterInputStream;
import com.github.nsc.de.compiler.lexer.characterinputstream.StringCharacterInputStream;
import com.github.nsc.de.compiler.lexer.token.TokenInputStream;
import com.github.nsc.de.compiler.parser.Parser;
import com.github.nsc.de.compiler.parser.node.Tree;

import java.util.Scanner;

public class ShakeCli {

    public static final Interpreter interpreter = new Interpreter();
    public static final JsonGenerator json = new JsonGenerator();
    public static boolean DEBUG;
    public static final String VERSION = "0.1.0";

    public static void main(String[] args) {

        CliArgumentParser argumentParser = new CliArgumentParser();
        argumentParser
                .option("generator", "g", 1, new String[] { "interpreter" })
                .option("debug", "d");


        CliArgumentParser.CliArguments arguments = argumentParser.parse(args);

        // Detect debug mode
        ShakeCli.DEBUG = arguments.getOption("debug").isGiven();

        String generator = arguments.getOption("generator").getValues()[0];

        if(arguments.getArguments().size() == 0) {

            // Info message for Shake console
            System.out.printf("# Shake version %s %s%n" +
                    "# Enter Shake code below to execute!%n" +
                    "# Using %s to execute code%n",
                    ShakeCli.VERSION,
                    ShakeCli.DEBUG ? "in debug mode " : "",
                    generator);

            // Open input console
            Scanner s = new Scanner(System.in);
            while(true) {

                try {
                    System.out.printf("%n$ > ");
                    StringCharacterInputStream chars = new StringCharacterInputStream("<Console>", s.nextLine());
                    Tree t = parse(chars);
                    execute(t, generator);
                } catch(Exception | Error e) {
                    e.printStackTrace();
                }
            }
        }
        else if(arguments.getArguments().size() == 1) {
            // Execute file
            // TODO implement visit file
        }
        else throw new Error("There are only 0-1 arguments allowed");

    }

    private static Tree parse(CharacterInputStream in) {
        Lexer lexer = new Lexer(in);
        TokenInputStream tokens = lexer.makeTokens();
        Parser parser = new Parser(tokens);
        return parser.parse();
    }

    private static void execute(Tree t, String generator) {
        switch (generator) {

            case "interpreter":
                System.out.printf(">> %s%n", ShakeCli.interpreter.visit(t).toString());
                break;
            case "json":
                System.out.printf(">> %s%n", ShakeCli.json.visit(t).toString());
                break;

        }
    }

}
