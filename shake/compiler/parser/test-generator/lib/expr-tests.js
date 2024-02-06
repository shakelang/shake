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
    async function operator1(value, template) {
      const { shake, json, error } = await generateTemplate(value);

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

    class TestGenerator {
      /**
       * @param {string} name
       * @param {Function} generator
       * @param {number} argNum
       */
      constructor(name, generator, argNum) {
        this.name = name;
        this.generator = generator;
        this.argNum = argNum;
        this.level = 0;
      }
    }

    const levels = [
      [new TestGenerator("priority", priority, 1)],
      [
        new TestGenerator("unary_minus", unaryMinus, 1),
        new TestGenerator("unary_plus", unaryPlus, 1),
        new TestGenerator("logical_not", logicalNot, 1),
        new TestGenerator("bitwise_not", bitwiseNot, 1),
      ],
      [new TestGenerator("power", pow, 2)],
      [
        new TestGenerator("multiplication", mul, 2),
        new TestGenerator("division", div, 2),
        new TestGenerator("modulus", mod, 2),
      ],
      [
        new TestGenerator("addition", add, 2),
        new TestGenerator("subtraction", sub, 2),
      ],

      // bitwise
      [new TestGenerator("bitwise_and", bitwiseAnd, 2)],
      [new TestGenerator("bitwise_xor", bitwiseXor, 2)],
      [new TestGenerator("bitwise_or", bitwiseOr, 2)],

      // logical
      [new TestGenerator("logical_and", logicalAnd, 2)],
      [new TestGenerator("logical_xor", logicalXor, 2)],
      [new TestGenerator("logical_or", logicalOr, 2)],
    ];

    levels.forEach((generators, j) => {
      generators.forEach((generator) => {
        generator.level = j;
      });
    });

    const generators = levels.flat();

    [
      [120, 7, 19],
      [77, 3, 25],
      [1, 2, 3],
    ].forEach(async (literals, i) => {
      generators.forEach(async (generator) => {
        generate(
          `simple_${generator.name}${i}.shake`,
          generator.generator(
            ...literals.slice(0, generator.argNum).map(literal)
          )
        );

        generators.forEach(async (generator2) => {
          // The expression should look like this:
          // a operator1 b operator2 c
          // we need to know which operator has higher priority

          if (generator.level > generator2.level) {
            // a operator1 (b operator2 c)
            // The result of the second operator will be put into the last argument of the first operator
            await generate(
              `nested_${generator.name}_${generator2.name}${i}`,
              generator.generator(
                ...literals.slice(0, generator.argNum - 1).map(literal),
                generator2.generator(
                  ...literals
                    .slice(
                      generator.argNum - 1,
                      generator2.argNum + generator.argNum - 1
                    )
                    .map(literal)
                )
              )
            );
          } else {
            // (a operator1 b) operator2 c
            // The result of the first operator will be put into the first argument of the second operator
            // This can also handle operator1.level === operator2.level (because then we will parse from left to right)

            await generate(
              `nested_${generator.name}_${generator2.name}${i}`,
              generator2.generator(
                generator.generator(
                  ...literals.slice(0, generator.argNum).map(literal)
                ),
                ...literals
                  .slice(
                    generator.argNum,
                    generator2.argNum + generator.argNum - 1
                  )
                  .map(literal)
              )
            );
          }
        });
      });
    });
  })();
})();
