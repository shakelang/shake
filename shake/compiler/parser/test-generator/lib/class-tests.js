// This file automatically generates tests for classes.
// Its output is stored into the commonTest/resources/generated-tests/classes directory.

const path = require("path");
const fs = require("fs-extra").promises;
const {
  forFileName,
  Template,
  fromBaseDir,
  primitiveTypes,
  generateTest,
  combineTokens,
  checkers,
} = require("./api");

(async () => {
  // Class Tests
  const classTestDirectory = fromBaseDir("classes");
  await fs.mkdir(classTestDirectory, { recursive: true });

  // Base class
  await (async () => {
    combineTokens(["final", ["public", "private", "protected"]]).map(
      async (attributes, i) => {
        const { isFinal, isStatic, access } = checkers.check(attributes);

        const template = new Template("classes/class");

        generateTest(
          path.resolve(classTestDirectory, `class${i}`),
          await template.shake,
          await template.json,
          await template.error,
          [
            [/%final%/g, isFinal],
            [/%static%/g, isStatic],
            [/%access%/g, access],
            [/%attributes%/g, attributes],
          ]
        );
      }
    );
  })();

  // Inner class
  await (async () => {
    combineTokens(["final", "static", ["public", "private", "protected"]]).map(
      async (attributes, i) => {
        const finalVal = attributes.includes("final");
        const staticVal = attributes.includes("static");
        const access = attributes.includes("public")
          ? "public"
          : attributes.includes("protected")
          ? "protected"
          : attributes.includes("private")
          ? "private"
          : "package";

        const template = new Template("classes/inner-class");

        generateTest(
          path.resolve(classTestDirectory, `inner_class${i}`),
          await template.shake,
          await template.json,
          await template.error,
          [
            [/%final%/g, finalVal],
            [/%static%/g, staticVal],
            [/%access%/g, access],
            [/%attributes%/g, attributes],
          ]
        );
      }
    );
  })();

  // The following code will generate tests for a single field in a class
  // The tests will be stored in the commonTest/resources/generated-tests/classes/fields directory

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

      for (const entry of combineTokens([
        "final",
        "static",
        ["public", "private", "protected"],
      ])
        .map(async (attributes, i) => {
          const finalVal = attributes.includes("final");
          const staticVal = attributes.includes("static");
          const access = attributes.includes("public")
            ? "public"
            : attributes.includes("protected")
            ? "protected"
            : attributes.includes("private")
            ? "private"
            : "package";

          return gentry(attributes, access, finalVal, staticVal);
        })
        .map((value, index) => ({ value, index }))) {
        generateTest(
          path.resolve(
            classFieldsDirectory,
            `${forFileName(type[1])}/field${entry.index}`
          ),
          await template.shake,
          await template.json,
          await template.error,
          [...base, ...(await entry.value)]
        );
        generateTest(
          path.resolve(
            classFieldsDirectory,
            `${forFileName(type[1])}/initialized_field${entry.index}`
          ),
          await template.shake,
          await template.json,
          await template.error,
          [...base, ...(await entry.value)]
        );
      }
    }
  })();
})();
