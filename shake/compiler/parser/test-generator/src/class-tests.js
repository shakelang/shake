const path = require("path");
const fs = require("fs-extra").promises;
const { forFileName, Template, fromBaseDir, relativize } = require("./api");

(async () => {
  // Class Tests
  const classTestDirectory = fromBaseDir(
    "src/commonTest/resources/tests/classes"
  );
  await fs.mkdir(classTestDirectory, { recursive: true });

  // Class Fields
  const classFieldsDirectory = path.resolve(classTestDirectory, "fields");
  await fs.mkdir(classFieldsDirectory, { recursive: true });

  const types = [
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
