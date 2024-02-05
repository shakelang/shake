// This file automatically generates tests for classes.
// Its output is stored into the commonTest/resources/tests/classes directory.

const path = require("path");
const fs = require("fs-extra").promises;
const {
  forFileName,
  Template,
  fromBaseDir,
  relativize,
  primitiveTypes,
  generateTest,
} = require("./api");

(async () => {
  // Class Tests
  const classTestDirectory = fromBaseDir("classes");
  await fs.mkdir(classTestDirectory, { recursive: true });

  // The following code will generate tests for a single field in a class
  // The tests will be stored in the commonTest/resources/tests/classes/fields directory

  await (async () => {
    // Class Fields
    const classFieldsDirectory = path.resolve(classTestDirectory, "fields");
    await fs.mkdir(classFieldsDirectory, { recursive: true });

    const types = primitiveTypes;

    const template = new Template("classes/class-field");

    for (const type of types) {
      function gentry(attributes, access, final, static) {
        return [
          [/%final%/g, final],
          [/%access%/g, access],
          [/%static%/g, static],
          [/%attributes%/g, attributes],
        ];
      }

      const base = [
        [/%type%/g, type[0]],
        [/%type_name%/g, type[1]],
        [/%name%/g, "field"],
      ];

      for (const entry of [
        gentry("", "package", false, false),
        gentry("public ", "public", false, false),
        gentry("private ", "private", false, false),
        gentry("protected ", "protected", false, false),

        gentry("static ", "package", false, true),
        gentry("public static ", "public", false, true),
        gentry("private static ", "private", false, true),
        gentry("protected static ", "protected", false, true),
        gentry("static public ", "public", false, true),
        gentry("static private ", "private", false, true),
        gentry("static protected ", "protected", false, true),

        gentry("final ", "package", true, false),
        gentry("public final ", "public", true, false),
        gentry("private final ", "private", true, false),
        gentry("protected final ", "protected", true, false),
        gentry("final public ", "public", true, false),
        gentry("final private ", "private", true, false),
        gentry("final protected ", "protected", true, false),

        gentry("final static ", "package", true, true),
        gentry("public final static ", "public", true, true),
        gentry("private final static ", "private", true, true),
        gentry("protected final static ", "protected", true, true),
        gentry("final static public ", "public", true, true),
        gentry("final static private ", "private", true, true),
        gentry("final static protected ", "protected", true, true),
      ].map((value, index) => ({ value, index }))) {
        generateTest(
          path.resolve(
            classFieldsDirectory,
            `class_field_${forFileName(type[1])}${entry.index}`
          ),
          await template.shake,
          await template.json,
          await template.error,
          [...base, ...entry.value]
        );
      }
    }
  })();
})();
