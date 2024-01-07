package com.shakelang.shake.cli

import kotlin.jvm.JvmOverloads

@Suppress("unused")
class CliArgumentParser(
    private val options: MutableList<CliOption> = mutableListOf()
) {
    constructor(options: Array<CliOption>) : this(mutableListOf(*options))

    fun parse(args: Array<String>): CliArgumentParser.CliArguments {
        val options: MutableList<ValuedCliOption> = ArrayList()
        val arguments: MutableList<String> = ArrayList()
        run {
            var i = 0
            while (i < args.size) {
                // full option-name
                if (args[i].startsWith("--")) {
                    // get the name of the option
                    val optionName = args[i].substring(2)

                    // check if this option is already set (throw error if so)
                    for (o in options.indices) if (options[o].name == optionName) {
                        throw Error("Option \"$optionName\" already defined!")
                    }

                    // find a specified option
                    for (o in this.options.indices) {
                        val option = this.options[o]
                        if (option.name == optionName) {
                            // This is the option we want!

                            // Create an array to contain all the option arguments
                            i = createValuedCliOption(args, options, i, optionName, option)
                        }
                    }
                } else if (args[i].startsWith("-")) {
                    // get the name of the option
                    val optionName = args[i].substring(1)

                    // check if this option is already set (throw error if so)
                    for (o in options.indices) if (options[o].name == optionName) {
                        throw Error("Option \"$optionName\" already defined!")
                    }

                    // find a specified option
                    for (o in this.options.indices) {
                        val option = this.options[o]
                        if (option.shortName == optionName) {
                            // This is the option we want!

                            // Create an array to contain all the option arguments
                            i = createValuedCliOption(args, options, i, optionName, option)
                            break
                        }
                    }
                } else {
                    // in another case this is just a normal argument, so we will just add it to the normal arguments
                    arguments.add(args[i])
                }
                i++
            }
        }

        // look up which options are not given and put them into the options list as a option with a false value
        for (i in this.options.indices) {
            val o = this.options[i]
            var isAdded = false
            for (c in options.indices) {
                if (options[c].name == o.name) {
                    isAdded = true
                    break
                }
            }
            if (!isAdded) options.add(o.valued())
        }

        // return a new CliArguments instance with the given options and arguments
        return CliArguments(options, arguments)
    }

    /**
     * Just a part of the [.parse] function that we put outside of it
     *
     * @param args given from [.parse]
     * @param options given from [.parse]
     * @param i given from [.parse]
     * @param optionName given from [.parse]
     * @param option given from [.parse]
     * @return the new counter value for the for loop
     */
    private fun createValuedCliOption(
        args: Array<String>,
        options: MutableList<ValuedCliOption>,
        i: Int,
        optionName: String,
        option: CliOption
    ): Int {
        var i1 = i
        val optionArguments = arrayOfNulls<String>(option.argumentAmount)
        for (c in 0 until option.argumentAmount) {
            // the index of the next option argument
            val index = c + i1 + 1

            // check if the index exists, throw an error if not
            if (args.size <= index) throw Error("Not enough arguments given for option \"--$optionName\"")
            optionArguments[c] = args[index]
        }
        options.add(option.valued(optionArguments))
        i1 += option.argumentAmount
        return i1
    }

    fun option(option: CliOption): CliArgumentParser {
        options.add(option)
        return this
    }

    fun option(name: String, shortName: String, argumentAmount: Int, defaults: Array<String?>?): CliArgumentParser {
        return this.option(
            CliOption(
                name,
                shortName,
                argumentAmount,
                defaults
            )
        )
    }

    fun option(name: String, shortName: String, argumentAmount: Int): CliArgumentParser {
        return this.option(CliOption(name, shortName, argumentAmount))
    }

    fun option(name: String, shortName: String): CliArgumentParser {
        return this.option(CliOption(name, shortName))
    }

    open class CliOption @JvmOverloads constructor(
        val name: String,
        val shortName: String,
        val argumentAmount: Int = 0,
        private val default_value: Array<String?>? = null
    ) {
        fun valued(value: Array<String?>?): ValuedCliOption {
            return ValuedCliOption(this, value ?: default_value)
        }

        fun valued(): ValuedCliOption {
            return ValuedCliOption(this, default_value)
        }

        init {
            if (default_value != null && default_value.size != argumentAmount) throw Error("Default value length must match the amount of arguments required")
        }
    }

    class ValuedCliOption(name: String, shortName: String, argumentAmount: Int, val values: Array<String?>?) :
        CliOption(name, shortName, argumentAmount) {

        constructor(option: CliOption, values: Array<String?>?) : this(
            option.name,
            option.shortName,
            option.argumentAmount,
            values
        ) {
        }

        val isGiven: Boolean
            get() = values != null
    }

    inner class CliArguments(val options: List<ValuedCliOption>, val arguments: List<String>) {
        fun getOption(name: String): ValuedCliOption? {
            for (option in this.options) {
                if (option.name == name) return option
            }
            return null
        }
    }
}
