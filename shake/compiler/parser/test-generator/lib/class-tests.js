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
      const fileName = `class_field_${forFileName(type[1])}.shake`;
      const file = path.resolve(classFieldsDirectory, fileName);

      console.log(`Generating ${relativize(file)}...`);

      await fs.writeFile(
        file,
        (
          await template.shake
        )
          .replace(/%type%/g, type[0])
          .replace(/%type_name%/g, type[1])
          .replace(/%name%/g, "field"),
        "utf8"
      );

      const jsonFile = path.resolve(
        classFieldsDirectory,
        `class_field_${forFileName(type[1])}.json`
      );

      console.log(`Generating ${relativize(jsonFile)}...`);

      await fs.writeFile(
        jsonFile,
        (
          await template.json
        )
          .replace(/%type%/g, type[0])
          .replace(/%type_name%/g, type[1])
          .replace(/%name%/g, "field"),
        "utf8"
      );
    }
  })();
})();
