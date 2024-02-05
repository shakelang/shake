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
  const template3 = new Template("fields/initialized-static-field");

  for (const type of types) {
    /**
     * @param {Template} template
     * @param {string} nameBase
     * @param {string} access
     * @param {string} finalVal
     * @param {string} attributes
     */
    async function generateTemplates(
      template,
      nameBase,
      access,
      finalVal,
      attributes
    ) {
      await generateTest(
        path.resolve(fieldsDirectory, nameBase),
        await template.shake,
        await template.json,
        await template.error,
        [
          [/%type%/g, type[0]],
          [/%type_name%/g, type[1]],
          [/%name%/g, "field"],
          [/%access%/g, access],
          [/%final%/g, finalVal],
          [/%attributes%/g, attributes],
        ]
      );
    }

    /**
     * @param {Function} generator
     * @param {Template} template
     * @param {string} nameBase
     */
    async function generate(generator, template, nameBase) {
      generateTemplates(
        template,
        `${forFileName(type[1])}/${nameBase}0`,
        "package",
        "false",
        ""
      );

      generateTemplates(
        template,
        `${forFileName(type[1])}/${nameBase}1`,
        "public",
        "false",
        "public "
      );

      generateTemplates(
        template,
        `${forFileName(type[1])}/${nameBase}2`,
        "protected",
        "false",
        "protected "
      );

      generateTemplates(
        template,
        `${forFileName(type[1])}/${nameBase}3`,
        "private",
        "false",
        "private "
      );

      generateTemplates(
        template,
        `${forFileName(type[1])}/${nameBase}4`,
        "package",
        "true",
        "final "
      );

      generateTemplates(
        template,
        `${forFileName(type[1])}/${nameBase}5`,
        "public",
        "true",
        "public final "
      );

      generateTemplates(
        template,
        `${forFileName(type[1])}/${nameBase}6`,
        "protected",
        "true",
        "protected final "
      );

      generateTemplates(
        template,
        `${forFileName(type[1])}/${nameBase}7`,
        "private",
        "true",
        "private final "
      );

      generateTemplates(
        template,
        `${forFileName(type[1])}/${nameBase}8`,
        "public",
        "true",
        "final public "
      );

      generateTemplates(
        template,
        `${forFileName(type[1])}/${nameBase}9`,
        "protected",
        "true",
        "final protected "
      );

      generateTemplates(
        template,
        `${forFileName(type[1])}/${nameBase}10`,
        "private",
        "true",
        "final private "
      );
    }

    generate(generateTemplates, template0, "field");
    generate(generateTemplates, template1, "initialized_field");
    generate(generateTemplates, template2, "static_field");
    generate(generateTemplates, template3, "static_initialized_field");
  }
})();
