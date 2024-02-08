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

  (async () => {
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

  (async () => {
    const template = new Template("methods/parameter-method");
    for (const type of primitiveTypes) {
      generateTest(
        path.join(methodsDirectory, `parameter/1/${forFileName(type[1])}`),
        await template.shake,
        await template.json,
        await template.error,
        [
          [/%type%/g, type[0]],
          [/%type_name%/g, type[1]],
          [/%name%/g, "m"],
          [/%access%/g, "protected"],
          [/%final%/g, "false"],
          [/%attributes%/g, ""],
          [/%type0%/g, type[0]],
          [/%type0_name%/g, type[1]],
        ]
      );
    }
  })();

  (async () => {
    const template = new Template("methods/parameter-method");
    for (const type of primitiveTypes) {
      for (const type1 of primitiveTypes) {
        generateTest(
          path.join(
            methodsDirectory,
            `parameter/2/${forFileName(type[1])}_${forFileName(type1[1])}`
          ),
          await template.shake,
          await template.json,
          await template.error,
          [
            [/%type%/g, type[0]],
            [/%type_name%/g, type[1]],
            [/%name%/g, "m"],
            [/%access%/g, "protected"],
            [/%final%/g, "false"],
            [/%attributes%/g, ""],
            [/%type0%/g, type[0]],
            [/%type0_name%/g, type[1]],
            [/%type1%/g, type1[0]],
            [/%type1_name%/g, type1[1]],
          ]
        );
      }
    }
  })();
})();
