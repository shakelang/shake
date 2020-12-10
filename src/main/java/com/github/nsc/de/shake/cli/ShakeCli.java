package com.github.nsc.de.shake.cli;

import com.github.nsc.de.shake.generators.json.JsonGenerator;
import com.github.nsc.de.shake.interpreter.Interpreter;
import com.github.nsc.de.shake.lexer.Lexer;
import com.github.nsc.de.shake.lexer.characterinputstream.CharacterInputStream;
import com.github.nsc.de.shake.lexer.characterinputstream.StringCharacterInputStream;
import com.github.nsc.de.shake.lexer.token.TokenInputStream;
import com.github.nsc.de.shake.parser.Parser;
import com.github.nsc.de.shake.parser.node.Tree;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class ShakeCli {

    public static final Interpreter interpreter = new Interpreter();
    public static final JsonGenerator json = new JsonGenerator();
    public static boolean DEBUG;
    public static final String VERSION = "0.1.0";

    public static void main(String[] args) throws IOException {

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
            String file = new String(Files.readAllBytes(Paths.get(arguments.getArguments().get(0))));
            StringCharacterInputStream chars = new StringCharacterInputStream(
                    "<File: " + arguments.getArguments().get(0) + ">", file);
            Tree t = parse(chars);
            execute(t, generator);
        }
        else throw new Error("There are only 0-1 arguments allowed");

    }

    private static Tree parse(CharacterInputStream in) {
        Lexer lexer = new Lexer(in);
        TokenInputStream tokens = lexer.makeTokens();
        if(DEBUG) System.out.printf("[DEBUG] Lexer-Tokens: %s%n", tokens.toString());
        Parser parser = new Parser(tokens);
        Tree tree = parser.parse();
        if(DEBUG) System.out.printf("[DEBUG] Parsed Tree: %s%n", tree.toString());
        return tree;
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
