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
    const priorityTemplate = new Template("expr/priority");
    const literalTemplate = new Template("expr/literal");
    const addTemplate = new Template("expr/add");
    const subTemplate = new Template("expr/sub");
    const mulTemplate = new Template("expr/mul");
    const divTemplate = new Template("expr/div");
    const modTemplate = new Template("expr/mod");
    const powTemplate = new Template("expr/pow");

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
     * Priority
     * @param {Promise<{shake: string, json: string, error: string}> | {shake: string, json: string, error: string} | string}
     * @returns {Promise<{shake: string, json: string, error: string}>}
     */
    async function priority(value) {
      const { shake, json, error } = await generateTemplate(value);

      const template = [
        [/%literal%/g, shake],
        [/%literal_json%/g, json],
        [/%literal_error%/g, error],
      ];

      return {
        shake: applyReplaceTemplate(await priorityTemplate.shake, template),
        json: applyReplaceTemplate(await priorityTemplate.json, template),
        error: applyReplaceTemplate(await priorityTemplate.error, template),
      };
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

    /**
     * Generate Test
     * @param {string} name
     * @param { Promise<{shake: string, json: string, error: string}> | {shake: string, json: string, error: string} | string} contents
     * @returns {Promise<void>}
     */
    async function generate(name, contents) {
      const { shake, json, error } = await generateTemplate(base(contents));

      return generateTest(
        path.resolve(exprTestDirectory, name),
        shake,
        JSON.stringify(JSON.parse(json), null, 2),
        error,
        []
      );
    }

    [
      [120, 7, 19],
      [77, 3, 25],
      [1, 2, 3],
    ].forEach(async ([a, b, c, d, e], i) => {
      // Normal additon
      generate(`simple_addition${i}`, add(literal(a), literal(b)));
      generate(`simple_subtraction${i}`, sub(literal(a), literal(b)));
      generate(`simple_multiplication${i}`, mul(literal(a), literal(b)));
      generate(`simple_division${i}`, div(literal(a), literal(b)));
      generate(`simple_modulus${i}`, mod(literal(a), literal(b)));
      generate(`simple_power${i}`, pow(literal(a), literal(b)));
      generate(`simple_priority${i}`, priority(literal(a)));

      generate(
        `addition_addition${i}`,
        add(add(literal(a), literal(b)), literal(c))
      );

      generate(
        `addition_subtraction${i}`,
        add(sub(literal(a), literal(b)), literal(c))
      );

      generate(
        `addition_multiplication${i}`,
        add(mul(literal(a), literal(b)), literal(c))
      );

      generate(
        `addition_division${i}`,
        add(div(literal(a), literal(b)), literal(c))
      );

      generate(
        `addition_modulus${i}`,
        add(mod(literal(a), literal(b)), literal(c))
      );

      generate(
        `addition_power${i}`,
        add(pow(literal(a), literal(b)), literal(c))
      );

      generate(
        `subtraction_addition${i}`,
        sub(add(literal(a), literal(b)), literal(c))
      );

      generate(
        `subtraction_subtraction${i}`,
        sub(sub(literal(a), literal(b)), literal(c))
      );

      generate(
        `subtraction_multiplication${i}`,
        sub(mul(literal(a), literal(b)), literal(c))
      );

      generate(
        `subtraction_division${i}`,
        sub(div(literal(a), literal(b)), literal(c))
      );

      generate(
        `subtraction_modulus${i}`,
        sub(mod(literal(a), literal(b)), literal(c))
      );

      generate(
        `subtraction_power${i}`,
        sub(pow(literal(a), literal(b)), literal(c))
      );

      generate(
        `multiplication_addition${i}`,
        add(literal(a), mul(literal(b), literal(c)))
      );

      generate(
        `multiplication_subtraction${i}`,
        sub(literal(a), mul(literal(b), literal(c)))
      );

      generate(
        `multiplication_multiplication${i}`,
        mul(mul(literal(a), literal(b)), literal(c))
      );

      generate(
        `multiplication_division${i}`,
        mul(div(literal(a), literal(b)), literal(c))
      );

      generate(
        `multiplication_modulus${i}`,
        mul(mod(literal(a), literal(b)), literal(c))
      );

      generate(
        `multiplication_power${i}`,
        mul(pow(literal(a), literal(b)), literal(c))
      );

      generate(
        `division_addition${i}`,
        add(literal(a), div(literal(b), literal(c)))
      );

      generate(
        `division_subtraction${i}`,
        sub(literal(a), div(literal(b), literal(c)))
      );

      generate(
        `division_multiplication${i}`,
        div(mul(literal(a), literal(b)), literal(c))
      );

      generate(
        `division_division${i}`,
        div(div(literal(a), literal(b)), literal(c))
      );

      generate(
        `division_modulus${i}`,
        div(mod(literal(a), literal(b)), literal(c))
      );

      generate(
        `division_power${i}`,
        div(pow(literal(a), literal(b)), literal(c))
      );

      generate(
        `modulus_addition${i}`,
        add(literal(a), mod(literal(b), literal(c)))
      );

      generate(
        `modulus_subtraction${i}`,
        sub(literal(a), mod(literal(b), literal(c)))
      );

      generate(
        `modulus_multiplication${i}`,
        mod(mul(literal(a), literal(b)), literal(c))
      );

      generate(
        `modulus_division${i}`,
        mod(div(literal(a), literal(b)), literal(c))
      );

      generate(
        `modulus_modulus${i}`,
        mod(mod(literal(a), literal(b)), literal(c))
      );

      generate(
        `modulus_power${i}`,
        mod(pow(literal(a), literal(b)), literal(c))
      );

      generate(
        `power_addition${i}`,
        add(literal(a), pow(literal(b), literal(c)))
      );

      generate(
        `power_subtraction${i}`,
        sub(literal(a), pow(literal(b), literal(c)))
      );

      generate(
        `power_multiplication${i}`,
        mul(literal(a), pow(literal(b), literal(c)))
      );

      generate(
        `power_division${i}`,
        div(literal(a), pow(literal(b), literal(c)))
      );

      generate(
        `power_modulus${i}`,
        mod(literal(a), pow(literal(b), literal(c)))
      );

      generate(`power_power${i}`, pow(pow(literal(a), literal(b)), literal(c)));

      generate(`priority_addition${i}`, priority(add(literal(a), literal(b))));

      generate(
        `priority_subtraction${i}`,
        priority(sub(literal(a), literal(b)))
      );

      generate(
        `priority_multiplication${i}`,
        priority(mul(literal(a), literal(b)))
      );

      generate(`priority_division${i}`, priority(div(literal(a), literal(b))));

      generate(`priority_modulus${i}`, priority(mod(literal(a), literal(b))));

      generate(`priority_power${i}`, priority(pow(literal(a), literal(b))));

      generate(`priority_priority${i}`, priority(priority(literal(a))));

      generate(
        `priority_addition_over_addition${i}`,
        add(literal(a), priority(add(literal(b), literal(c))))
      );

      generate(
        `priority_addition_over_subtraction${i}`,
        sub(literal(a), priority(sub(literal(b), literal(c))))
      );

      generate(
        `priority_addition_over_multiplication${i}`,
        mul(literal(a), priority(mul(literal(b), literal(c))))
      );

      generate(
        `priority_addition_over_division${i}`,
        div(literal(a), priority(div(literal(b), literal(c))))
      );

      generate(
        `priority_addition_over_modulus${i}`,
        mod(literal(a), priority(mod(literal(b), literal(c))))
      );

      generate(
        `priority_addition_over_power${i}`,
        pow(literal(a), priority(pow(literal(b), literal(c))))
      );

      generate(
        `priority_subtraction_over_addition${i}`,
        add(literal(a), priority(add(literal(b), literal(c))))
      );

      generate(
        `priority_subtraction_over_subtraction${i}`,
        sub(literal(a), priority(sub(literal(b), literal(c))))
      );

      generate(
        `priority_subtraction_over_multiplication${i}`,
        mul(literal(a), priority(mul(literal(b), literal(c))))
      );

      generate(
        `priority_subtraction_over_division${i}`,
        div(literal(a), priority(div(literal(b), literal(c))))
      );

      generate(
        `priority_subtraction_over_modulus${i}`,
        mod(literal(a), priority(mod(literal(b), literal(c))))
      );

      generate(
        `priority_subtraction_over_power${i}`,
        pow(literal(a), priority(pow(literal(b), literal(c))))
      );

      generate(
        `priority_multiplication_over_addition${i}`,
        add(literal(a), priority(add(literal(b), literal(c))))
      );

      generate(
        `priority_multiplication_over_subtraction${i}`,
        sub(literal(a), priority(sub(literal(b), literal(c))))
      );

      generate(
        `priority_multiplication_over_multiplication${i}`,
        mul(literal(a), priority(mul(literal(b), literal(c))))
      );

      generate(
        `priority_multiplication_over_division${i}`,
        div(literal(a), priority(div(literal(b), literal(c))))
      );

      generate(
        `priority_multiplication_over_modulus${i}`,
        mod(literal(a), priority(mod(literal(b), literal(c))))
      );

      generate(
        `priority_multiplication_over_power${i}`,
        pow(literal(a), priority(pow(literal(b), literal(c))))
      );

      generate(
        `priority_division_over_addition${i}`,
        add(literal(a), priority(add(literal(b), literal(c))))
      );

      generate(
        `priority_division_over_subtraction${i}`,
        sub(literal(a), priority(sub(literal(b), literal(c))))
      );

      generate(
        `priority_division_over_multiplication${i}`,
        mul(literal(a), priority(mul(literal(b), literal(c))))
      );

      generate(
        `priority_division_over_division${i}`,
        div(literal(a), priority(div(literal(b), literal(c))))
      );

      generate(
        `priority_division_over_modulus${i}`,
        mod(literal(a), priority(mod(literal(b), literal(c))))
      );

      generate(
        `priority_division_over_power${i}`,
        pow(literal(a), priority(pow(literal(b), literal(c))))
      );

      generate(
        `priority_modulus_over_addition${i}`,
        add(literal(a), priority(add(literal(b), literal(c))))
      );

      generate(
        `priority_modulus_over_subtraction${i}`,
        sub(literal(a), priority(sub(literal(b), literal(c))))
      );

      generate(
        `priority_modulus_over_multiplication${i}`,
        mul(literal(a), priority(mul(literal(b), literal(c))))
      );

      generate(
        `priority_modulus_over_division${i}`,
        div(literal(a), priority(div(literal(b), literal(c))))
      );

      generate(
        `priority_modulus_over_modulus${i}`,
        mod(literal(a), priority(mod(literal(b), literal(c))))
      );

      generate(
        `priority_modulus_over_power${i}`,
        pow(literal(a), priority(pow(literal(b), literal(c))))
      );

      generate(
        `priority_power_over_addition${i}`,
        add(literal(a), priority(add(literal(b), literal(c))))
      );

      generate(
        `priority_power_over_subtraction${i}`,
        sub(literal(a), priority(sub(literal(b), literal(c))))
      );

      generate(
        `priority_power_over_multiplication${i}`,
        mul(literal(a), priority(mul(literal(b), literal(c))))
      );

      generate(
        `priority_power_over_division${i}`,
        div(literal(a), priority(div(literal(b), literal(c))))
      );

      generate(
        `priority_power_over_modulus${i}`,
        mod(literal(a), priority(mod(literal(b), literal(c))))
      );

      generate(
        `priority_power_over_power${i}`,
        pow(literal(a), priority(pow(literal(b), literal(c))))
      );
    });
  })();
})();
