package com.github.nsc.de.shake.cli;

import com.github.nsc.de.shake.generators.json.JsonGenerator;
import com.github.nsc.de.shake.interpreter.Interpreter;
import com.github.nsc.de.shake.lexer.Lexer;
import com.github.nsc.de.shake.lexer.characterinput.characterinputstream.CharacterInputStream;
import com.github.nsc.de.shake.lexer.characterinput.characterinputstream.SourceCharacterInputStream;
import com.github.nsc.de.shake.lexer.token.TokenInputStream;
import com.github.nsc.de.shake.parser.Parser;
import com.github.nsc.de.shake.parser.node.Tree;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;


/**
 * The main command-line CLI for Shake
 */
public class ShakeCli {

    /**
     * The interpreter for interpreting the code
     */
    public static final Interpreter interpreter = new Interpreter();

    /**
     * The json-generator for generating json form the input code
     */
    public static final JsonGenerator json = new JsonGenerator();

    /**
     * Is the program in debug mode?
     */
    public static boolean DEBUG;

    /**
     * The version of the program
     */
    public static final String VERSION = "0.1.0";

    /**
     * The Main-Method of the {@link ShakeCli}
     *
     * @param args The arguments for the main cli
     * @throws IOException This exception is thrown, when there is a problem with the file given as argument
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public static void main(String[] args) throws IOException {

        // Create a parser for the arguments
        CliArgumentParser argumentParser = new CliArgumentParser();

        // Define the options for the argumentParser
        argumentParser
                .option("generator", "g", 1, new String[] { "interpreter" })
                .option("debug", "d");


        // Parse the arguments given to the main-method
        CliArgumentParser.CliArguments arguments = argumentParser.parse(args);

        // Detect debug mode (check if debug option is set ("--debug"))
        ShakeCli.DEBUG = arguments.getOption("debug").isGiven();

        // Get the generator ("--generator")
        String generator = arguments.getOption("generator").getValues()[0];

        // If no normal argument is given, then we will open a prompt for entering code
        if(arguments.getArguments().size() == 0) {

            // Info message for Shake console
            System.out.printf("# Shake version %s %s%n" +
                    "# Enter Shake code below to execute!%n" +
                    "# Using %s to execute code%n",
                    ShakeCli.VERSION,
                    ShakeCli.DEBUG ? "in debug mode " : "",
                    generator);

            // Create Scanner to read from the console
            Scanner s = new Scanner(System.in);

            // Just create a infinite loop for reading from the console
            while(true) {

                // Try & Catch to prevent the console from crashing when
                // entering wrong code
                try {

                    // Print input-request-line-start
                    System.out.printf("%n$ > ");

                    // request the input from the console and create a StringCharacterInputStream from it
                    CharacterInputStream chars = new SourceCharacterInputStream("<Console>", s.nextLine());

                    // parse the CharacterInputStream into a Tree
                    Tree t = parse(chars);

                    // execute the tree using the specified generator
                    execute(t, generator);

                } catch(Throwable t) {
                    // When an error occurs while executing the code, just print it's stack and continue
                    t.printStackTrace();
                }
            }
        }

        // When we get one argument we will execute a file
        else if(arguments.getArguments().size() == 1) {

            // read the contents of the file
            String file = new String(Files.readAllBytes(Paths.get(arguments.getArguments().get(0))));

            // Create a new StringCharacterInputStream from the file's contents
            CharacterInputStream chars = new SourceCharacterInputStream(
                    "<File: " + arguments.getArguments().get(0) + ">", file);

            // Parse the CharacterInputStream
            Tree t = parse(chars);

            // Execute the Tree using the specified generator
            execute(t, generator);

        }

        // If we get more than 1 normal argument we will throw an error as we can only process
        // one file at the moment.
        else throw new Error("There are only 0-1 arguments allowed");

    }

    /**
     * This function parses a {@link CharacterInputStream} into a Tree
     *
     * @param in the {@link CharacterInputStream} to parse
     * @return the parsed {@link Tree}
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    private static Tree parse(CharacterInputStream in) {

        // Create a new Lexer from the CharacterInputStream
        Lexer lexer = new Lexer(in);

        // Generate the tokens using the lexer
        TokenInputStream tokens = lexer.makeTokens();

        // if debug is enabled we print out the lexer-tokens
        if(DEBUG) System.out.printf("[DEBUG] Lexer-Tokens: %s%n", tokens.toString());

        // Create a new Parser from the tokens
        Parser parser = new Parser(tokens);

        // Let the parser parse the tokens into a Tree
        Tree tree = parser.parse();

        // If debug is enabled we print out the tree
        if(DEBUG) System.out.printf("[DEBUG] Parsed Tree: %s%n", tree.toString());

        // return the Tree
        return tree;
    }

    /**
     * This function executes a {@link Tree} using a specified generator
     *
     * @param t the {@link Tree} to execute
     * @param generator the generator to use (just give the name of it)
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    private static void execute(Tree t, String generator) {

        switch (generator) {

            // if the generator argument is "interpreter" then use the interpreter to visit the Tree
            // and print it'S results to the console
            case "interpreter":
                System.out.printf(">> %s%n", ShakeCli.interpreter.visit(t).toString());
                break;
            // if the generator argument is "json" then use the json-generator to visit the Tree
            // and print it'S results to the console
            case "json":
                System.out.printf(">> %s%n", ShakeCli.json.visit(t).toString());
                break;

        }
    }

}
