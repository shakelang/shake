package com.github.nsc.de.shake.cli;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CliArgumentParser {

    private final List<CliOption> options;

    public CliArgumentParser(List<CliOption> options) {
        this.options = options;
    }

    public CliArgumentParser(CliOption[] options) {
        this(Arrays.asList(options));
    }

    public CliArgumentParser() {
        this(new ArrayList<>());
    }

    public CliArguments parse(String[] args) {

        List<ValuedCliOption> options = new ArrayList<>();
        List<String> arguments = new ArrayList<>();


        for(int i = 0; i < args.length; i++) {

            // full option-name
            if(args[i].startsWith("--")) {

                // get the name of the option
                String optionName = args[i].substring(2);

                // check if this option is already set (throw error if so)
                for(int o = 0; o < options.size(); o++)
                    if(options.get(o).getName().equals(optionName))
                        throw new Error(String.format("Option \"%s\" already defined!", optionName));

                // find specified option
                for(int o = 0; o < this.options.size(); o++) {

                    CliOption option = this.options.get(o);

                    if(option.getName().equals(optionName)) {

                        // This is the option we want!

                        // Create an array to contain all the option arguments
                        i = createValuedCliOption(args, options, i, optionName, option);

                    }

                }

            }

            // short option-name
            else if(args[i].startsWith("-")) {

                // get the name of the option
                String optionName = args[i].substring(1);

                // check if this option is already set (throw error if so)
                for(int o = 0; o < options.size(); o++)
                    if(options.get(o).getName().equals(optionName))
                        throw new Error(String.format("Option \"%s\" already defined!", optionName));

                // find specified option
                for(int o = 0; o < this.options.size(); o++) {

                    CliOption option = this.options.get(o);

                    if(option.getShortName().equals(optionName)) {

                        // This is the option we want!

                        // Create an array to contain all the option arguments
                        i = createValuedCliOption(args, options, i, optionName, option);
                        break;

                    }

                }

            } else {

                // in other case this is just a normal argument, so we will just add it to the normal arguments
                arguments.add(args[i]);

            }

        }

        // look up which options are not given and put them into the options list as a option with a false value
        for(int i = 0; i < this.options.size(); i++) {
            CliOption o = this.options.get(i);
            boolean isAdded = false;
            for(int c = 0; c < options.size(); c++) {
                if(options.get(c).getName().equals(o.getName())) {
                    isAdded = true;
                    break;
                }
            }
            if(!isAdded) options.add(o.valued());
        }

        // return a new CliArguments instance with the given options and arguments
        return new CliArguments(options, arguments);

    }

    /**
     * Just a part of the {@link #parse(String[])} function that we put outside of it
     *
     * @param args given from {@link #parse(String[])}
     * @param options given from {@link #parse(String[])}
     * @param i given from {@link #parse(String[])}
     * @param optionName given from {@link #parse(String[])}
     * @param option given from {@link #parse(String[])}
     * @return the new counter value for the for loop
     */
    private int createValuedCliOption(String[] args, List<ValuedCliOption> options, int i, String optionName, CliOption option) {
        String[] optionArguments = new String[option.getArgumentAmount()];

        for(int c = 0; c < option.getArgumentAmount(); c++) {

            // the index of the next option argument
            int index = c + i + 1;

            // check if the index exists, throw an error if not
            if(args.length <= index)
                throw new Error(String.format("Not enough arguments given for option \"--%s\"", optionName));

            optionArguments[c] = args[index];

        }

        options.add(option.valued(optionArguments));

        i += option.getArgumentAmount();
        return i;
    }


    public CliArgumentParser option(CliOption option) {
        this.options.add(option);
        return this;
    }

    public CliArgumentParser option(String name, String shortName, int argumentAmount, String[] defaults) {
        return this.option(new CliOption(name, shortName, argumentAmount, defaults));
    }

    public CliArgumentParser option(String name, String shortName, int argumentAmount) {
        return this.option(new CliOption(name, shortName, argumentAmount));
    }

    public CliArgumentParser option(String name, String shortName) {
        return this.option(new CliOption(name, shortName));
    }

    public static class CliOption {

        private final String name;
        private final String shortName;
        private final int argumentAmount;
        private final String[] default_value;

        public CliOption(String name, String shortName, int argumentAmount, String[] default_value) {

            this.name = name;
            this.shortName = shortName;
            this.argumentAmount = argumentAmount;
            this.default_value = default_value;

            if(this.default_value != null && this.default_value.length != argumentAmount)
                throw new Error("Default value length must match the amount of arguments required");
        }

        public CliOption(String name, String shortName, int argumentAmount) {

            this(name, shortName, argumentAmount, null);

        }

        public CliOption(String name, String shortName) {

            this(name, shortName, 0);

        }

        public String getName() {
            return name;
        }

        public String getShortName() {
            return shortName;
        }

        public int getArgumentAmount() {
            return argumentAmount;
        }

        public ValuedCliOption valued(String[] value) {
            return new ValuedCliOption(this, value != null ? value : this.default_value);
        }

        public ValuedCliOption valued() {
            return new ValuedCliOption(this, this.default_value);
        }

    }

    public static class ValuedCliOption extends CliOption {

        private final String[] values;

        public ValuedCliOption(String name, String shortName, int argumentAmount, String[] values) {

            super(name, shortName, argumentAmount);
            this.values = values;

        }

        public ValuedCliOption(CliOption option, String[] values) {

            this(option.getName(), option.getShortName(), option.getArgumentAmount(), values);

        }

        public String[] getValues() {
            return values;
        }

        public boolean isGiven() {
            return this.values != null;
        }
    }

    public class CliArguments {

        private final List<ValuedCliOption> options;
        private final List<String> arguments;

        public CliArguments(List<ValuedCliOption> options, List<String> arguments) {
            this.options = options;
            this.arguments = arguments;
        }

        public List<ValuedCliOption> getOptions() {
            return this.options;
        }

        public List<String> getArguments() {
            return arguments;
        }

        public ValuedCliOption getOption(String name) {
            for(ValuedCliOption option : this.getOptions()) {
                if(option.getName().equals(name)) return option;
            }
            return null;
        }
    }

}
