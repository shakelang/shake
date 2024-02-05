// This file automatically generates tests for classes.
// Its output is stored into the commonTest/resources/tests/classes directory.

const path = require("path");
const fs = require("fs-extra").promises;
const {
  Template,
  fromBaseDir,
  primitiveTypes,
  generateTest,
  applyReplaceTemplate,
} = require("./api");

(async () => {
  // Class Tests
  const exprTestDirectory = fromBaseDir("expr");
  await fs.mkdir(exprTestDirectory, { recursive: true });

  // The following code will generate tests for a single field in a class
  // The tests will be stored in the commonTest/resources/tests/classes/fields directory

  await (async () => {
    const types = primitiveTypes;

    const baseTemplate = new Template("expr/base");
    const literalTemplate = new Template("expr/literal");
    const addTemplate = new Template("expr/add");
    const subTemplate = new Template("expr/sub");
    const mulTemplate = new Template("expr/mul");
    const divTemplate = new Template("expr/div");
    const modTemplate = new Template("expr/mod");
    const powTemplate = new Template("expr/pow");

    /**
     * Literal
     * @param {string} value
     * @returns {Promise<{shake: string, json: string, error: string}>}
     */
    async function literal(value) {
      return {
        shake: (await literalTemplate.shake)?.replace(/%literal%/g, value),
        json: (await literalTemplate.json)?.replace(/%literal%/g, value),
        error: (await literalTemplate.error)?.replace(/%literal%/g, value),
      };
    }

    /**
     * Generate Template
     * @param {Promise<{shake: string, json: string, error: string}> | {shake: string, json: string, error: string} | string} value
     * @returns {Promise<{shake: string, json: string, error: string}>}
     */
    async function generateTemplate(value) {
      value = await value;
      const shake = typeof value === "string" ? value : value.shake;
      const json = typeof value === "string" ? value : value.json;
      const error = typeof value === "string" ? value : value.error;
      return { shake, json, error };
    }

    /**
     * Calculation Template
     * @param {Promise<{shake: string, json: string, error: string}> | {shake: string, json: string, error: string} | string} left
     * @param {Promise<{shake: string, json: string, error: string}> | {shake: string, json: string, error: string} | string} right
     * @returns {Promise<{shake: string, json: string, error: string}>}
     */
    async function calc(left, right, template) {
      const {
        shake: leftShake,
        json: leftJson,
        error: leftError,
      } = await generateTemplate(left);

      const {
        shake: rightShake,
        json: rightJson,
        error: rightError,
      } = await generateTemplate(right);

      const templ = [
        [/%left%/g, leftShake],
        [/%right%/g, rightShake],
        [/%left_json%/g, leftJson],
        [/%right_json%/g, rightJson],
        [/%left_error%/g, leftError],
        [/%right_error%/g, rightError],
      ];

      return {
        shake: applyReplaceTemplate(await template.shake, templ),
        json: applyReplaceTemplate(await template.json, templ),
        error: applyReplaceTemplate(await template.error, templ),
      };
    }

    /**
     * Add
     * @param {Promise<{shake: string, json: string, error: string}> | {shake: string, json: string, error: string} | string}
     * @param {Promise<{shake: string, json: string, error: string}> | {shake: string, json: string, error: string} | string}
     * @returns {Promise<{shake: string, json: string, error: string}>}
     */
    async function add(left, right) {
      return calc(left, right, addTemplate);
    }

    /**
     * Sub
     * @param {Promise<{shake: string, json: string, error: string}> | {shake: string, json: string, error: string} | string}
     * @param {Promise<{shake: string, json: string, error: string}> | {shake: string, json: string, error: string} | string}
     * @returns {Promise<{shake: string, json: string, error: string}>}
     */
    async function sub(left, right) {
      return calc(left, right, subTemplate);
    }

    /**
     * Mul
     * @param {Promise<{shake: string, json: string, error: string}> | {shake: string, json: string, error: string} | string}
     * @param {Promise<{shake: string, json: string, error: string}> | {shake: string, json: string, error: string} | string}
     * @returns {Promise<{shake: string, json: string, error: string}>}
     */
    async function mul(left, right) {
      return calc(left, right, mulTemplate);
    }

    /**
     * Div
     * @param {Promise<{shake: string, json: string, error: string}> | {shake: string, json: string, error: string} | string}
     * @param {Promise<{shake: string, json: string, error: string}> | {shake: string, json: string, error: string} | string}
     * @returns {Promise<{shake: string, json: string, error: string}>}
     */
    async function div(left, right) {
      return calc(left, right, divTemplate);
    }

    /**
     * Mod
     * @param {Promise<{shake: string, json: string, error: string}> | {shake: string, json: string, error: string} | string}
     * @param {Promise<{shake: string, json: string, error: string}> | {shake: string, json: string, error: string} | string}
     * @returns {Promise<{shake: string, json: string, error: string}>}
     */
    async function mod(left, right) {
      return calc(left, right, modTemplate);
    }

    /**
     * Pow
     * @param {Promise<{shake: string, json: string, error: string}> | {shake: string, json: string, error: string} | string}
     * @param {Promise<{shake: string, json: string, error: string}> | {shake: string, json: string, error: string} | string}
     * @returns {Promise<{shake: string, json: string, error: string}>}
     */
    async function pow(left, right) {
      return calc(left, right, powTemplate);
    }

    /**
     * Base Template
     * @param {Promise<{shake: string, json: string, error: string}> | {shake: string, json: string, error: string} | string}
     * @returns {Promise<{shake: string, json: string, error: string}>}
     */
    async function base(value) {
      const { shake, json, error } = await generateTemplate(value);

      const template = [
        [/%expr%/g, shake],
        [/%expr_json%/g, json],
        [/%expr_error%/g, error],
      ];

      return {
        shake: applyReplaceTemplate(await baseTemplate.shake, template),
        json: applyReplaceTemplate(await baseTemplate.json, template),
        error: applyReplaceTemplate(await baseTemplate.error, template),
      };
    }

    const { shake, json, error } = await generateTemplate(
      base(add(literal("1"), literal("2")))
    );

    await generateTest(
      path.resolve(exprTestDirectory, `1`),
      shake,
      JSON.stringify(JSON.parse(json), null, 2),
      error,
      []
    );
  })();
})();
