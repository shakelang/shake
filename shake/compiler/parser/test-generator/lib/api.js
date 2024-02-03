// This file contains the basic api for generating our test cases
// It does not include any actions, but just defines basic utilities

const fs = require("fs").promises;
const path = require("path");

module.exports = {};

module.exports.forFileName = function forFileName(input) {
  return input
    .replace(/ /g, "_")
    .replace(/\./g, "_")
    .replace(/,/g, "_")
    .replace(/\(/g, "_")
    .replace(/\)/g, "_");
};

module.exports.Template = class Template {
  constructor(name) {
    this.name = name;
    this.shakeFile = `templates/${name}.shake`;
    this.jsonFile = `templates/${name}.json`;
    this.errorFile = `templates/${name}.error`;
    this.shake = fs.readFile(this.shakeFile, "utf8").catch(() => null);
    this.json = fs.readFile(this.jsonFile, "utf8").catch(() => null);
    this.error = fs.readFile(this.errorFile, "utf8").catch(() => null);
  }
};

module.exports.baseDir = path.resolve(
  __dirname,
  "../../src/commonTest/resources/tests/"
);
module.exports.fromBaseDir = function fromBaseDir(...args) {
  return path.resolve(module.exports.baseDir, ...args);
};
function relativize(...args) {
  return path.relative(module.exports.baseDir, path.resolve(...args));
}

module.exports.relativize = relativize;

/**
 * Apply a replace-template to a string
 * @param {string} string
 * @param {[RegExp, string][]} template
 */
function applyReplaceTemplate(string, template) {
  for (const [from, to] of template) {
    string = string.replace(from, to);
  }
  return string;
}

module.exports.primitiveTypes = [
  ["byte", "byte"],
  ["short", "short"],
  ["int", "integer"],
  ["long", "long"],
  ["float", "float"],
  ["double", "double"],
  ["char", "char"],
  ["boolean", "boolean"],
  ["unsigned byte", "unsigned_byte"],
  ["unsigned short", "unsigned_short"],
  ["unsigned int", "unsigned_integer"],
  ["unsigned long", "unsigned_long"],
];

/**
 * Generate a test case
 *
 * @param {string} test The test file (without extension)
 * @param {string} input the input contents
 * @param {string} output the output contents (or null if no output is expected)
 * @param {string} error the error contents (or null if no error is expected)
 * @param {[RegExp, string][]} template the template to use
 * @param {boolean} newlineTest whether to generate a second test with an appended newline
 */
async function generateTest(
  test,
  input,
  output,
  error,
  template,
  newlineTest = true
) {
  if (newlineTest) {
    await generateTest(
      `${test}_no_newline`,
      input,
      output,
      error,
      template,
      false
    );
    await generateTest(
      `${test}_newline`,
      `${input}\n`,
      output,
      error,
      template,
      false
    );
    return;
  }

  await fs.mkdir(path.dirname(test), { recursive: true });

  const testFile = `${test}.shake`;
  const outputFile = `${test}.json`;
  const errorFile = `${test}.error`;

  const Template = [...template, [/%file%/g, relativize(test)]];

  console.log(`Generating ${relativize(testFile)}...`);
  await fs.writeFile(testFile, applyReplaceTemplate(input, Template), "utf8");

  if (output !== null) {
    console.log(`Generating ${relativize(outputFile)}...`);
    await fs.writeFile(
      outputFile,
      applyReplaceTemplate(output, Template),
      "utf8"
    );
  }

  if (error !== null) {
    console.log(`Generating ${relativize(errorFile)}...`);
    await fs.writeFile(
      errorFile,
      applyReplaceTemplate(error, Template),
      "utf8"
    );
  }
}

module.exports.generateTest = generateTest;
