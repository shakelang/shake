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
  checkers,
} = require("./api");
const path = require("path");

(async () => {
  const methodsDirectory = fromBaseDir("methods");
  await fs.mkdir(methodsDirectory, { recursive: true });

  const template0 = new Template("methods/method");
  const template1 = new Template("methods/parameter-method");
  const template2 = new Template("methods/parameter2-method");

  combineTokens(["final", ["public", "private", "protected"]]).forEach(
    async (attributes, i) => {
      const { isFinal, isStatic, access } = checkers.check(attributes);
      for (const type of primitiveTypesIncludingVoid) {
        generateTest(
          path.join(methodsDirectory, `${forFileName(type[1])}/0/${i}`),
          await template0.shake,
          await template0.json,
          await template0.error,
          [
            [/%type%/g, type[0]],
            [/%type_name%/g, type[1]],
            [/%name%/g, "m"],
            [/%access%/g, access],
            [/%final%/g, isFinal],
            [/%static%/g, isStatic],
            [/%attributes%/g, attributes],
          ]
        );
      }

      const type_ = "void";
      for (const type0 of primitiveTypes) {
        generateTest(
          path.join(
            methodsDirectory,
            `${forFileName(type_[1])}/1/${forFileName(type0[1])}${i}`
          ),
          await template1.shake,
          await template1.json,
          await template1.error,
          [
            [/%type%/g, type0[0]],
            [/%type_name%/g, type0[1]],
            [/%name%/g, "m"],
            [/%access%/g, "protected"],
            [/%final%/g, "false"],
            [/%attributes%/g, ""],
            [/%type%/g, type0[0]],
            [/%type_name%/g, type0[1]],
            [/%type0%/g, type0[0]],
            [/%type0_name%/g, type0[1]],
          ]
        );
      }

      const type = "void";

      for (const type0 of primitiveTypes) {
        for (const type1 of primitiveTypes) {
          generateTest(
            path.join(
              methodsDirectory,
              `${forFileName(type_[1])}/2/${forFileName(
                type0[1]
              )}_${forFileName(type1[1])}${i}`
            ),
            await template2.shake,
            await template2.json,
            await template2.error,
            [
              [/%type%/g, type0[0]],
              [/%type_name%/g, type0[1]],
              [/%name%/g, "m"],
              [/%access%/g, "protected"],
              [/%final%/g, "false"],
              [/%attributes%/g, ""],
              [/%type%/g, type0[0]],
              [/%type_name%/g, type0[1]],
              [/%type0%/g, type0[0]],
              [/%type0_name%/g, type0[1]],
              [/%type1%/g, type1[0]],
              [/%type1_name%/g, type1[1]],
            ]
          );
        }
      }
    }
  );
})();
