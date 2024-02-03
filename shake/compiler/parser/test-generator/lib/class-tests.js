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
  const classTestDirectory = fromBaseDir(
    "src/commonTest/resources/tests/classes"
  );
  await fs.mkdir(classTestDirectory, { recursive: true });

  // The following code will generate tests for a single field in a class
  // The tests will be stored in the commonTest/resources/tests/classes/fields directory

  await (async () => {
    // Class Fields
    const classFieldsDirectory = path.resolve(classTestDirectory, "fields");
    await fs.mkdir(classFieldsDirectory, { recursive: true });

    const types = primitiveTypes;

    const template = new Template("class-field");

    for (const type of types) {
      generateTest(
        path.resolve(
          classFieldsDirectory,
          `class_field_${forFileName(type[1])}`
        ),
        await template.shake,
        await template.json,
        null,
        [
          [/%type%/g, type[0]],
          [/%type_name%/g, type[1]],
          [/%name%/g, "field"],
        ] // Replace template
      );
    }
  })();
})();
