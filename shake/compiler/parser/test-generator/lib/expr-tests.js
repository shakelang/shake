// This file automatically generates tests for classes.
// Its output is stored into the commonTest/resources/generated-tests/expr directory.

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
  // Expression Tests
  const exprTestDirectory = fromBaseDir("expr");
  await fs.mkdir(exprTestDirectory, { recursive: true });

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

    const logicalOrTemplate = new Template("expr/logical-or");
    const logicalAndTemplate = new Template("expr/logical-and");
    const logicalNotTemplate = new Template("expr/logical-not");
    const logicalXorTemplate = new Template("expr/logical-xor");

    const bitwiseOrTemplate = new Template("expr/bitwise-or");
    const bitwiseAndTemplate = new Template("expr/bitwise-and");
    const bitwiseNotTemplate = new Template("expr/bitwise-not");
    const bitwiseXorTemplate = new Template("expr/bitwise-xor");

    const unaryMinusTemplate = new Template("expr/unary-minus");
    const unaryPlusTemplate = new Template("expr/unary-plus");

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
        shake: (await literalTemplate.shake)?.replace(/%value%/g, value),
        json: (await literalTemplate.json)?.replace(/%value%/g, value),
        error: (await literalTemplate.error)?.replace(/%value%/g, value),
      };
    }

    /**
     * Priority
     * @param {Promise<{shake: string, json: string, error: string}> | {shake: string, json: string, error: string} | string}
     * @returns {Promise<{shake: string, json: string, error: string}>}
     */
    async function priority(value) {
      return operator1(value, priorityTemplate);
    }

    /**
     * Calculation Template
     * @param {Promise<{shake: string, json: string, error: string}> | {shake: string, json: string, error: string} | string} value
     * @returns {Promise<{shake: string, json: string, error: string}>}
     */
    async function operator1(left, template) {
      const { shake, json, error } = await generateTemplate(left);

      const templ = [
        [/%value%/g, shake],
        [/%value_json%/g, json],
        [/%value_error%/g, error],
      ];

      return {
        shake: applyReplaceTemplate(await template.shake, templ),
        json: applyReplaceTemplate(await template.json, templ),
        error: applyReplaceTemplate(await template.error, templ),
      };
    }

    /**
     * Calculation Template
     * @param {Promise<{shake: string, json: string, error: string}> | {shake: string, json: string, error: string} | string} left
     * @param {Promise<{shake: string, json: string, error: string}> | {shake: string, json: string, error: string} | string} right
     * @returns {Promise<{shake: string, json: string, error: string}>}
     */
    async function operator2(left, right, template) {
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
      return operator2(left, right, addTemplate);
    }

    /**
     * Sub
     * @param {Promise<{shake: string, json: string, error: string}> | {shake: string, json: string, error: string} | string}
     * @param {Promise<{shake: string, json: string, error: string}> | {shake: string, json: string, error: string} | string}
     * @returns {Promise<{shake: string, json: string, error: string}>}
     */
    async function sub(left, right) {
      return operator2(left, right, subTemplate);
    }

    /**
     * Mul
     * @param {Promise<{shake: string, json: string, error: string}> | {shake: string, json: string, error: string} | string}
     * @param {Promise<{shake: string, json: string, error: string}> | {shake: string, json: string, error: string} | string}
     * @returns {Promise<{shake: string, json: string, error: string}>}
     */
    async function mul(left, right) {
      return operator2(left, right, mulTemplate);
    }

    /**
     * Div
     * @param {Promise<{shake: string, json: string, error: string}> | {shake: string, json: string, error: string} | string}
     * @param {Promise<{shake: string, json: string, error: string}> | {shake: string, json: string, error: string} | string}
     * @returns {Promise<{shake: string, json: string, error: string}>}
     */
    async function div(left, right) {
      return operator2(left, right, divTemplate);
    }

    /**
     * Mod
     * @param {Promise<{shake: string, json: string, error: string}> | {shake: string, json: string, error: string} | string}
     * @param {Promise<{shake: string, json: string, error: string}> | {shake: string, json: string, error: string} | string}
     * @returns {Promise<{shake: string, json: string, error: string}>}
     */
    async function mod(left, right) {
      return operator2(left, right, modTemplate);
    }

    /**
     * Pow
     * @param {Promise<{shake: string, json: string, error: string}> | {shake: string, json: string, error: string} | string}
     * @param {Promise<{shake: string, json: string, error: string}> | {shake: string, json: string, error: string} | string}
     * @returns {Promise<{shake: string, json: string, error: string}>}
     */
    async function pow(left, right) {
      return operator2(left, right, powTemplate);
    }

    /**
     * Logical Or
     * @param {Promise<{shake: string, json: string, error: string}> | {shake: string, json: string, error: string} | string}
     * @param {Promise<{shake: string, json: string, error: string}> | {shake: string, json: string, error: string} | string}
     * @returns {Promise<{shake: string, json: string, error: string}>}
     */
    async function logicalOr(left, right) {
      return operator2(left, right, logicalOrTemplate);
    }

    /**
     * Logical And
     * @param {Promise<{shake: string, json: string, error: string}> | {shake: string, json: string, error: string} | string}
     * @param {Promise<{shake: string, json: string, error: string}> | {shake: string, json: string, error: string} | string}
     * @returns {Promise<{shake: string, json: string, error: string}>}
     */
    async function logicalAnd(left, right) {
      return operator2(left, right, logicalAndTemplate);
    }

    /**
     * Logical Xor
     * @param {Promise<{shake: string, json: string, error: string}> | {shake: string, json: string, error: string} | string}
     * @param {Promise<{shake: string, json: string, error: string}> | {shake: string, json: string, error: string} | string}
     * @returns {Promise<{shake: string, json: string, error: string}>}
     */
    async function logicalXor(left, right) {
      return operator2(left, right, logicalXorTemplate);
    }

    /**
     * Logical Not
     * @param {Promise<{shake: string, json: string, error: string}> | {shake: string, json: string, error: string} | string}
     * @returns {Promise<{shake: string, json: string, error: string}>}
     */
    async function logicalNot(value) {
      return operator1(value, logicalNotTemplate);
    }

    /**
     * Bitwise Or
     * @param {Promise<{shake: string, json: string, error: string}> | {shake: string, json: string, error: string} | string}
     * @param {Promise<{shake: string, json: string, error: string}> | {shake: string, json: string, error: string} | string}
     * @returns {Promise<{shake: string, json: string, error: string}>}
     */
    async function bitwiseOr(left, right) {
      return operator2(left, right, bitwiseOrTemplate);
    }

    /**
     * Bitwise And
     * @param {Promise<{shake: string, json: string, error: string}> | {shake: string, json: string, error: string} | string} left
     * @param {Promise<{shake: string, json: string, error: string}> | {shake: string, json: string, error: string} | string} right
     * @returns {Promise<{shake: string, json: string, error: string}>}
     */
    async function bitwiseAnd(left, right) {
      return operator2(left, right, bitwiseAndTemplate);
    }

    /**
     * Bitwise Xor
     * @param {Promise<{shake: string, json: string, error: string}> | {shake: string, json: string, error: string} | string} left
     * @param {Promise<{shake: string, json: string, error: string}> | {shake: string, json: string, error: string} | string} right
     * @returns {Promise<{shake: string, json: string, error: string}>}
     */
    async function bitwiseXor(left, right) {
      return operator2(left, right, bitwiseXorTemplate);
    }

    /**
     * Bitwise Not
     * @param {Promise<{shake: string, json: string, error: string}> | {shake: string, json: string, error: string} | string}
     * @returns {Promise<{shake: string, json: string, error: string}>}
     */
    async function bitwiseNot(value) {
      return operator1(value, bitwiseNotTemplate);
    }

    /**
     * Unary Minus
     * @param {Promise<{shake: string, json: string, error: string}> | {shake: string, json: string, error: string} | string}
     * @returns {Promise<{shake: string, json: string, error: string}>}
     */
    async function unaryMinus(value) {
      return operator1(value, unaryMinusTemplate);
    }

    /**
     * Unary Plus
     * @param {Promise<{shake: string, json: string, error: string}> | {shake: string, json: string, error: string} | string}
     * @returns {Promise<{shake: string, json: string, error: string}>}
     */
    async function unaryPlus(value) {
      return operator1(value, unaryPlusTemplate);
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

      generate(`unary_minus${i}`, unaryMinus(literal(a)));
      generate(
        `unary_minus_addition${i}`,
        add(unaryMinus(literal(a)), literal(b))
      );
      generate(
        `unary_minus_subtraction${i}`,
        sub(unaryMinus(literal(a)), literal(b))
      );
      generate(
        `unary_minus_multiplication${i}`,
        mul(unaryMinus(literal(a)), literal(b))
      );
      generate(
        `unary_minus_division${i}`,
        div(unaryMinus(literal(a)), literal(b))
      );
      generate(
        `unary_minus_modulus${i}`,
        mod(unaryMinus(literal(a)), literal(b))
      );
      generate(
        `unary_minus_power${i}`,
        pow(unaryMinus(literal(a)), literal(b))
      );

      generate(`unary_plus${i}`, unaryPlus(literal(a)));
      generate(
        `unary_plus_addition${i}`,
        add(unaryPlus(literal(a)), literal(b))
      );
      generate(
        `unary_plus_subtraction${i}`,
        sub(unaryPlus(literal(a)), literal(b))
      );
      generate(
        `unary_plus_multiplication${i}`,
        mul(unaryPlus(literal(a)), literal(b))
      );
      generate(
        `unary_plus_division${i}`,
        div(unaryPlus(literal(a)), literal(b))
      );
      generate(
        `unary_plus_modulus${i}`,
        mod(unaryPlus(literal(a)), literal(b))
      );
      generate(`unary_plus_power${i}`, pow(unaryPlus(literal(a)), literal(b)));

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
