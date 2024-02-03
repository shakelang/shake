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

module.exports.baseDir = path.resolve(__dirname, "..", "..");
module.exports.fromBaseDir = function fromBaseDir(...args) {
  return path.resolve(module.exports.baseDir, ...args);
};
module.exports.relativize = function relativize(...args) {
  return path.relative(module.exports.baseDir, path.resolve(...args));
};

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
