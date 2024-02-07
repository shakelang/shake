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
  primitiveTypesIncludingVoid,
} = require("./api");
const path = require("path");

(async () => {
  const methodsDirectory = fromBaseDir("methods");
  await fs.mkdir(methodsDirectory, { recursive: true });

  const template = new Template("methods/method");
  for (const type of primitiveTypesIncludingVoid) {
    combineTokens(["final", ["public", "private", "protected"]]).forEach(
      async (attributes, i) => {
        const access = attributes.includes("private")
          ? "private"
          : attributes.includes("protected")
          ? "protected"
          : attributes.includes("public")
          ? "public"
          : "package";
        const finalVal = attributes.includes("final");

        await generateTest(
          path.join(methodsDirectory, `${forFileName(type[1])}/${i}`),
          await template.shake,
          await template.json,
          await template.error,
          [
            [/%type%/g, type[0]],
            [/%type_name%/g, type[1]],
            [/%name%/g, "m"],
            [/%access%/g, access],
            [/%final%/g, finalVal],
            [/%attributes%/g, attributes],
          ]
        );
      }
    );
  }
})();
