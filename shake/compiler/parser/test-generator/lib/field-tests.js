// This file automatically generates tests for fields.
// Its output is stored into the commonTest/resources/generated-tests/fields directory.

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
    async function generate(template, nameBase) {
      combineTokens(["final", ["public", "private", "protected"]]).forEach(
        async (attributes, i) => {
          const { isFinal, access } = checkers.check(attributes);

          generateTemplates(
            template,
            `${forFileName(type[1])}/${nameBase}${i}`,
            access,
            isFinal,
            attributes
          );
        }
      );
    }

    generate(template0, "field");
    generate(template1, "initialized_field");
    generate(template2, "static_field");
    generate(template3, "static_initialized_field");
  }
})();
