# Shake Parser Test Generator Scripts

This is a basic project of javascript files used for generating unit tests for our shake parser.
We want to test as many weird edge cases as possible, so we can be sure that our parser is robust.
For that reason we've decided to automatically generate the tests.
You don't need to generate these tests yourself, they are committed to the repository.
This is the generator code, which is not part of the project, but is used to generate the tests.

## Setup

To run the generator, you need to have node.js installed. Then you can run the following commands:

```bash
npm install
npm run generate
```

## How are the tests structured?

One test case consists of two files. First of all we have an input file, which is the shake code that we want to parse,
and named `<name>.shake`.

Then we have an output file, which describes either the expected parse tree or the expected error.
The AST is stored in a JSON format, and the file is named `<name>.json`. The error is stored in a file named `<name>.error`, and contains the error in plain text.

## How does the generator work?

The generator consists of a bunch of scripts and template files (stored in the `templates` directory).
