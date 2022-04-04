package io.github.shakelang.shake.js.output

interface JsOutput {
    fun generate(indentAmount: Int = 0, indent: String = "  "): String
    fun toStatement(): JsStatement? {
        return if(this is JsStatement) this else null
    }
    fun toValue(): JsValue {
        return if(this is JsValue) this else throw IllegalStateException("Not a value")
    }
}

interface JsStatement : JsOutput
interface JsValue : JsOutput {
    val needsParens: Boolean
    open fun generateValue(indentAmount: Int = 0, indent: String = "  "): String {
        return if(needsParens) "(${generate(indentAmount, indent)})" else generate(indentAmount, indent)
    }
}

class JsTree(
    val children: List<JsStatement> = emptyList(),
) : JsOutput {
    override fun generate(indentAmount: Int, indent: String): String {
        return children.joinToString("\n") {
            indent.repeat(indentAmount) + it.generate(indentAmount, indent)
        }
    }
    fun generateBlock(indentAmount: Int, indent: String): String {
        if(children.isEmpty()) {
            return "{}"
        }
        if(children.size == 1) {
            return children.first().generate(indentAmount, indent)
        }
        return "{\n${generate(indentAmount + 1, indent)}\n}"
    }
}

class JsDouble(
    val value: Double
) : JsValue {
    override val needsParens: Boolean get() = false

    override fun generate(indentAmount: Int, indent: String): String {
        return value.toString()
    }
}

class JsInteger(
    val value: Int
) : JsValue {
    override val needsParens: Boolean get() = false

    override fun generate(indentAmount: Int, indent: String): String {
        return value.toString()
    }
}

class JsAdd(
    val left: JsValue,
    val right: JsValue
) : JsValue {
    override val needsParens: Boolean get() = true
    override fun generate(indentAmount: Int, indent: String): String {
        return "${left.generate(indentAmount, indent)} + ${right.generate(indentAmount, indent)}"
    }
}

class JsSubtract(
    val left: JsValue,
    val right: JsValue
) : JsValue {
    override val needsParens: Boolean get() = true
    override fun generate(indentAmount: Int, indent: String): String {
        return "${left.generate(indentAmount, indent)} - ${right.generate(indentAmount, indent)}"
    }
}

class JsMultiply(
    val left: JsValue,
    val right: JsValue
) : JsValue {
    override val needsParens: Boolean get() = true
    override fun generate(indentAmount: Int, indent: String): String {
        return "${left.generate(indentAmount, indent)} * ${right.generate(indentAmount, indent)}"
    }
}

class JsDivide(
    val left: JsValue,
    val right: JsValue
) : JsValue {
    override val needsParens: Boolean get() = true
    override fun generate(indentAmount: Int, indent: String): String {
        return "${left.generate(indentAmount, indent)} / ${right.generate(indentAmount, indent)}"
    }
}

class JsModulo(
    val left: JsValue,
    val right: JsValue
) : JsValue {
    override val needsParens: Boolean get() = true
    override fun generate(indentAmount: Int, indent: String): String {
        return "${left.generate(indentAmount, indent)} % ${right.generate(indentAmount, indent)}"
    }
}

class JsField (
    val name: String,
    val parent: JsValue? = null
) : JsValue {
    override val needsParens: Boolean get() = false
    override fun generate(indentAmount: Int, indent: String): String {
        return if(parent == null) name else "${parent.generateValue(indentAmount, indent)}.$name"
    }
}

class JsFunctionCall(
    val function: JsValue,
    val args: List<JsValue>,
) : JsValue, JsStatement {
    override val needsParens: Boolean get() = false
    override fun generate(indentAmount: Int, indent: String): String {
        return "${function.generate(indentAmount, indent)}(${args.joinToString(", ") { it.generate(indentAmount, indent) }})"
    }
}

abstract class JsDeclaration : JsStatement {
    abstract val name: String
    abstract val value: JsValue?
}

class JsVariableDeclaration (
    override val name: String,
    override val value: JsValue? = null
) : JsDeclaration() {
    override fun generate(indentAmount: Int, indent: String): String {
        return if(value == null) "var $name"
        else "var $name = ${value.generate(indentAmount, indent)}"
    }
}

class JsConstantDeclaration (
    override val name: String,
    override val value: JsValue
) : JsDeclaration() {
    override fun generate(indentAmount: Int, indent: String): String {
        return "const $name = ${value.generate(indentAmount, indent)}"
    }
}

class JsAssignment(
    val name: JsField,
    val value: JsValue
) : JsStatement, JsValue {
    override val needsParens: Boolean get() = true
    override fun generate(indentAmount: Int, indent: String): String {
        return "$name = ${value.generate(indentAmount, indent)}"
    }
}

class JsAddAssignment(
    val name: JsField,
    val value: JsValue
) : JsStatement, JsValue {
    override val needsParens: Boolean get() = true
    override fun generate(indentAmount: Int, indent: String): String {
        return "$name += ${value.generate(indentAmount, indent)}"
    }
}

class JsSubtractAssignment(
    val name: JsField,
    val value: JsValue
) : JsStatement, JsValue {
    override val needsParens: Boolean get() = true
    override fun generate(indentAmount: Int, indent: String): String {
        return "$name -= ${value.generate(indentAmount, indent)}"
    }
}

class JsMultiplyAssignment(
    val name: JsField,
    val value: JsValue
) : JsStatement, JsValue {
    override val needsParens: Boolean get() = true
    override fun generate(indentAmount: Int, indent: String): String {
        return "$name *= ${value.generate(indentAmount, indent)}"
    }
}

class JsDivideAssignment(
    val name: JsField,
    val value: JsValue
) : JsStatement, JsValue {
    override val needsParens: Boolean get() = true
    override fun generate(indentAmount: Int, indent: String): String {
        return "$name /= ${value.generate(indentAmount, indent)}"
    }
}

class JsModuloAssignment(
    val name: JsField,
    val value: JsValue
) : JsStatement, JsValue {
    override val needsParens: Boolean get() = true
    override fun generate(indentAmount: Int, indent: String): String {
        return "$name %= ${value.generate(indentAmount, indent)}"
    }
}

class JsIncrement(
    val name: JsField
) : JsStatement, JsValue {
    override val needsParens: Boolean get() = true
    override fun generate(indentAmount: Int, indent: String): String {
        return "$name++"
    }
}

class JsBeforeIncrement(
    val name: JsField
) : JsStatement, JsValue {
    override val needsParens: Boolean get() = true
    override fun generate(indentAmount: Int, indent: String): String {
        return "++$name"
    }
}

class JsDecrement(
    val name: JsField
) : JsStatement, JsValue {
    override val needsParens: Boolean get() = true
    override fun generate(indentAmount: Int, indent: String): String {
        return "$name--"
    }
}

class JsBeforeDecrement(
    val name: JsField
) : JsStatement, JsValue {
    override val needsParens: Boolean get() = true
    override fun generate(indentAmount: Int, indent: String): String {
        return "--$name"
    }
}

class JsEquals(
    val left: JsValue,
    val right: JsValue
) : JsValue {
    override val needsParens: Boolean get() = true
    override fun generate(indentAmount: Int, indent: String): String {
        return "${left.generate(indentAmount, indent)} == ${right.generate(indentAmount, indent)}"
    }
}

class JsNotEquals(
    val left: JsValue,
    val right: JsValue
) : JsValue {
    override val needsParens: Boolean get() = true
    override fun generate(indentAmount: Int, indent: String): String {
        return "${left.generate(indentAmount, indent)} != ${right.generate(indentAmount, indent)}"
    }
}

class JsLessThan(
    val left: JsValue,
    val right: JsValue
) : JsValue {
    override val needsParens: Boolean get() = true
    override fun generate(indentAmount: Int, indent: String): String {
        return "${left.generate(indentAmount, indent)} < ${right.generate(indentAmount, indent)}"
    }
}

class JsLessThanOrEquals(
    val left: JsValue,
    val right: JsValue
) : JsValue {
    override val needsParens: Boolean get() = true
    override fun generate(indentAmount: Int, indent: String): String {
        return "${left.generate(indentAmount, indent)} <= ${right.generate(indentAmount, indent)}"
    }
}

class JsGreaterThan(
    val left: JsValue,
    val right: JsValue
) : JsValue {
    override val needsParens: Boolean get() = true
    override fun generate(indentAmount: Int, indent: String): String {
        return "${left.generate(indentAmount, indent)} > ${right.generate(indentAmount, indent)}"
    }
}

class JsGreaterThanOrEquals(
    val left: JsValue,
    val right: JsValue
) : JsValue {
    override val needsParens: Boolean get() = true
    override fun generate(indentAmount: Int, indent: String): String {
        return "${left.generate(indentAmount, indent)} >= ${right.generate(indentAmount, indent)}"
    }
}

class JsAnd(
    val left: JsValue,
    val right: JsValue
) : JsValue {
    override val needsParens: Boolean get() = true
    override fun generate(indentAmount: Int, indent: String): String {
        return "${left.generate(indentAmount, indent)} && ${right.generate(indentAmount, indent)}"
    }
}

class JsOr(
    val left: JsValue,
    val right: JsValue
) : JsValue {
    override val needsParens: Boolean get() = true
    override fun generate(indentAmount: Int, indent: String): String {
        return "${left.generate(indentAmount, indent)} || ${right.generate(indentAmount, indent)}"
    }
}

class JsXOr(
    val left: JsValue,
    val right: JsValue
) : JsValue {
    override val needsParens: Boolean get() = true
    override fun generate(indentAmount: Int, indent: String): String {
        return "${left.generate(indentAmount, indent)} ^ ${right.generate(indentAmount, indent)}"
    }
}

class JsNot(
    val value: JsValue
) : JsValue {
    override val needsParens: Boolean get() = true
    override fun generate(indentAmount: Int, indent: String): String {
        return "!${value.generate(indentAmount, indent)}"
    }
}

class JsNegate(
    val value: JsValue
) : JsValue {
    override val needsParens: Boolean get() = true
    override fun generate(indentAmount: Int, indent: String): String {
        return "-${value.generate(indentAmount, indent)}"
    }
}

class JsIf(
    val condition: JsValue,
    val then: JsTree,
    val elseStatement: JsTree? = null
) : JsStatement {
    override fun generate(indentAmount: Int, indent: String): String {
        val statement = StringBuilder("if (${condition.generate(indentAmount, indent)}) ${then.generateBlock(indentAmount, indent)}")
        if(elseStatement != null) {
            statement.append(" else ${elseStatement.generateBlock(indentAmount, indent)}")
        }
        return statement.toString()
    }
}

class JsWhile(
    val condition: JsValue,
    val body: JsTree
) : JsStatement {
    override fun generate(indentAmount: Int, indent: String): String {
        return "while (${condition.generate(indentAmount, indent)}) ${body.generateBlock(indentAmount, indent)}"
    }
}

class JsDoWhile(
    val condition: JsValue,
    val body: JsTree
) : JsStatement {
    override fun generate(indentAmount: Int, indent: String): String {
        return "do ${body.generateBlock(indentAmount, indent)} while (${condition.generate(indentAmount, indent)})"
    }
}

class JsFor(
    val init: JsStatement,
    val condition: JsValue,
    val update: JsStatement,
    val body: JsTree
) : JsStatement {
    override fun generate(indentAmount: Int, indent: String): String {
        return "for (${init.generate(indentAmount, indent)}; ${condition.generate(indentAmount, indent)}; ${update.generate(indentAmount, indent)}) ${body.generateBlock(indentAmount, indent)}"
    }
}

class JsFunctionDeclaration(
    val name: String,
    val parameters: List<JsVariableDeclaration>,
    val body: JsTree
) : JsStatement {
    override fun generate(indentAmount: Int, indent: String): String {
        return "function $name${baseGenerate(indentAmount, indent)}"
    }

    fun generateInClass(indentAmount: Int, indent: String): String {
        return "$name${baseGenerate(indentAmount, indent)}"
    }

    fun inline(indentAmount: Int, indent: String): JsInlineFunction {
        return JsInlineFunction(parameters, body)
    }

    private fun baseGenerate(indentAmount: Int, indent: String): String {
        return "(${parameters.joinToString(", ") {
            if(it.value != null) {
                "${it.name} = ${it.value.generate(indentAmount, indent)}"
            } else {
                it.name
            }
        }}) {\n${body.generate(indentAmount + 1, indent)}\n}"
    }
}

class JsInlineFunction (
    val parameters: List<JsVariableDeclaration>,
    val body: JsTree
) : JsValue {
    override val needsParens: Boolean get() = true
    override fun generate(indentAmount: Int, indent: String): String {
        return "function (${parameters.joinToString(", ") {
            if(it.value != null) {
                "${it.name} = ${it.value.generate(indentAmount, indent)}"
            } else {
                it.name
            }
        }}) {\n${body.generate(indentAmount, indent)}\n}"
    }
}

class JsClassDeclaration(
    val name: String,
    val functions: List<JsFunctionDeclaration> = emptyList(),
    val staticFunctions: List<JsFunctionDeclaration> = emptyList(),
    val fields: List<JsDeclaration> = emptyList(),
    val staticFields: List<JsDeclaration> = emptyList(),
    val extends: JsValue? = null,
) : JsStatement {

    val constructor: JsFunctionDeclaration get() {
        val statements = mutableListOf<JsStatement>()
        fields.forEach {
            if(it.value != null) {
                statements.add(JsAssignment(JsField(it.name, JsField("this")), it.value!!))
            }
        }
        return JsFunctionDeclaration(name, emptyList(), JsTree(statements))
    }

    override fun generate(indentAmount: Int, indent: String): String {
        val statement = StringBuilder("class $name")
        if(extends != null) {
            statement.append(" extends ${extends.generate(indentAmount, indent)}")
        }
        statement.append(" {\n")
        statement.append(constructor.generateInClass(indentAmount, indent))
        statement.append("\n")
        statement.append(functions.joinToString("\n") { it.generateInClass(indentAmount, indent) })
        statement.append("\n}\n")
        staticFunctions.forEach {
            statement.append(JsAssignment(JsField(it.name, JsField(this.name)), it.inline(indentAmount, indent)))
            statement.append("\n")
        }
        staticFields.forEach {
            statement.append(JsAssignment(JsField(it.name, JsField(this.name)), it.value!!))
            statement.append("\n")
        }
        return statement.toString()
    }
}

class JsNew(
    val type: JsValue,
    val parameters: List<JsValue> = emptyList(),
) : JsValue {
    override val needsParens: Boolean get() = false
    override fun generate(indentAmount: Int, indent: String): String {
        return "new ${type.generate(indentAmount, indent)}(${parameters.joinToString(", ") { it.generate(indentAmount, indent) }})"
    }
}

enum class JsLiteral : JsValue {
    TRUE, FALSE, NULL;
    override val needsParens: Boolean get() = false

    override fun generate(indentAmount: Int, indent: String): String {
        return name.lowercase()
    }
}