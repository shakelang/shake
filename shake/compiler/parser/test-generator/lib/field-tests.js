// This file automatically generates tests for fields.
// Its output is stored into the commonTest/resources/tests/fields directory.

const fs = require("fs-extra").promises;
const {
  forFileName,
  Template,
  fromBaseDir,
  primitiveTypes,
  generateTest,
} = require("./api");
const path = require("path");

(async () => {
  const fieldsDirectory = fromBaseDir("fields");
  await fs.mkdir(fieldsDirectory, { recursive: true });

  const types = primitiveTypes;

  const template0 = new Template("fields/field");
  const template1 = new Template("fields/initialized-field");
  const template2 = new Template("fields/static-field");

  for (const type of types) {
    /**
     *
     * @param {Template} template
     * @param {string} nameBase
     * @param {string} access
     * @param {string} staticVal
     * @param {string} finalVal
     * @param {string} attributes
     */
    async function generateTemplates(
      template,
      nameBase,
      access,
      staticVal,
      finalVal,
      attributes
    ) {
      await generateTest(
        path.resolve(fieldsDirectory, nameBase),
        await template.shake,
        await template.json,
        null,
        [
          [/%type%/g, type[0]],
          [/%type_name%/g, type[1]],
          [/%name%/g, "field"],
          [/%access%/g, access],
          [/%static%/g, staticVal],
          [/%final%/g, finalVal],
          [/%attributes%/g, attributes],
        ]
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

    await generateTest(
      path.resolve(fieldsDirectory, `${forFileName(type[1])}/static_field0`),
      await template2.shake,
      null,
      await template2.error,
      [
        [/%type%/g, type[0]],
        [/%type_name%/g, type[1]],
        [/%name%/g, "field"],
        [/%access%/g, "package"],
        [/%final%/g, "true"],
        [/%attributes%/g, ""],
      ]
    );
  }
})();
