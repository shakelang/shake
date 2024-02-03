// This file automatically generates tests for fields.
// Its output is stored into the commonTest/resources/tests/fields directory.

const fs = require("fs-extra").promises;
const {
  forFileName,
  Template,
  fromBaseDir,
  relativize,
  primitiveTypes,
} = require("./api");
const path = require("path");

(async () => {
  const fieldsDirectory = fromBaseDir("src/commonTest/resources/tests/fields");
  await fs.mkdir(fieldsDirectory, { recursive: true });

  const types = primitiveTypes;

  const template0 = new Template("field");
  const template1 = new Template("initialized-field");

  for (const type of types) {
    async function generateTemplates(
      template,
      nameBase,
      access,
      staticVal,
      finalVal,
      attributes
    ) {
      const file = path.resolve(fieldsDirectory, `${nameBase}.shake`);
      fs.mkdir(path.dirname(file), { recursive: true });

      console.log(`Generating ${relativize(file)}...`);

      await fs.writeFile(
        file,
        (await template.shake)
          .replace("%type%", type[0])
          .replace("%type_name%", type[1])
          .replace("%name%", "field")
          .replace("%access%", access)
          .replace("%static%", staticVal)
          .replace("%final%", finalVal)
          .replace("%attributes%", attributes),
        "utf8"
      );

      const jsonFile = path.resolve(fieldsDirectory, `${nameBase}.json`);

      console.log(`Generating ${relativize(jsonFile)}...`);

      await fs.writeFile(
        jsonFile,
        (await template.json)
          .replace("%type%", type[0])
          .replace("%type_name%", type[1])
          .replace("%name%", "field")
          .replace("%access%", access)
          .replace("%static%", staticVal)
          .replace("%final%", finalVal)
          .replace("%attributes%", attributes),
        "utf8"
      );
    }

    await generateTemplates(
      template0,
      `${forFileName(type[1])}/field0`,
      "package",
      "false",
      "false",
      ""
    );
    await generateTemplates(
      template0,
      `${forFileName(type[1])}/field1`,
      "public",
      "false",
      "false",
      "public "
    );
    await generateTemplates(
      template0,
      `${forFileName(type[1])}/field2`,
      "protected",
      "false",
      "false",
      "protected "
    );
    await generateTemplates(
      template0,
      `${forFileName(type[1])}/field3`,
      "private",
      "false",
      "false",
      "private "
    );
    await generateTemplates(
      template1,
      `${forFileName(type[1])}/initialized_field0`,
      "package",
      "false",
      "false",
      ""
    );
    await generateTemplates(
      template1,
      `${forFileName(type[1])}/initialized_field1`,
      "public",
      "false",
      "false",
      "public "
    );
    await generateTemplates(
      template1,
      `${forFileName(type[1])}/initialized_field2`,
      "protected",
      "false",
      "false",
      "protected "
    );
    await generateTemplates(
      template1,
      `${forFileName(type[1])}/initialized_field3`,
      "private",
      "false",
      "false",
      "private "
    );
    await generateTemplates(
      template1,
      `${forFileName(type[1])}/initialized_field4`,
      "package",
      "false",
      "true",
      "final "
    );
    await generateTemplates(
      template1,
      `${forFileName(type[1])}/initialized_field5`,
      "public",
      "false",
      "true",
      "public final "
    );
    await generateTemplates(
      template1,
      `${forFileName(type[1])}/initialized_field6`,
      "protected",
      "false",
      "true",
      "protected final "
    );
    await generateTemplates(
      template1,
      `${forFileName(type[1])}/initialized_field7`,
      "private",
      "false",
      "true",
      "private final "
    );
    await generateTemplates(
      template1,
      `${forFileName(type[1])}/initialized_field8`,
      "public",
      "false",
      "true",
      "final public "
    );
    await generateTemplates(
      template1,
      `${forFileName(type[1])}/initialized_field8`,
      "protected",
      "false",
      "true",
      "final protected "
    );
    await generateTemplates(
      template1,
      `${forFileName(type[1])}/initialized_field9`,
      "private",
      "false",
      "true",
      "final private "
    );
  }
})();
