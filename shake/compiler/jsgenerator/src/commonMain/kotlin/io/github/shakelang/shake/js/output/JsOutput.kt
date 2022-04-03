package io.github.shakelang.shake.js.output

interface JsOutput {
    fun generate(): String
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
    open fun generateValue(): String {
        return if(needsParens) "(${generate()})" else generate()
    }
}

class JsTree(
    val children: List<JsStatement> = emptyList(),
) : JsOutput {
    override fun generate(): String = generate(2)
    fun generate(indent: Int = 2): String {
        return children.joinToString("\n") { " ".repeat(indent) + generate() }
    }
    fun generateBlock(): String {
        if(children.isEmpty()) {
            return "{}"
        }
        if(children.size == 1) {
            return children.first().generate()
        }
        return "{\n${generate(2)}\n}"
    }
    fun generateBlock(indent: Int = 2): String {
        if(children.isEmpty()) {
            return "{}"
        }
        if(children.size == 1) {
            return children.first().generate()
        }
        return "{\n${generate(indent)}\n}"
    }
}

class JsDouble(
    val value: Double
) : JsValue {
    override val needsParens: Boolean get() = false

    override fun generate(): String {
        return value.toString()
    }
}

class JsInteger(
    val value: Int
) : JsValue {
    override val needsParens: Boolean get() = false

    override fun generate(): String {
        return value.toString()
    }
}

class JsAdd(
    val left: JsValue,
    val right: JsValue
) : JsValue {
    override val needsParens: Boolean get() = true
    override fun generate(): String {
        return "${left.generate()} + ${right.generate()}"
    }
}

class JsSubtract(
    val left: JsValue,
    val right: JsValue
) : JsValue {
    override val needsParens: Boolean get() = true
    override fun generate(): String {
        return "${left.generate()} - ${right.generate()}"
    }
}

class JsMultiply(
    val left: JsValue,
    val right: JsValue
) : JsValue {
    override val needsParens: Boolean get() = true
    override fun generate(): String {
        return "${left.generate()} * ${right.generate()}"
    }
}

class JsDivide(
    val left: JsValue,
    val right: JsValue
) : JsValue {
    override val needsParens: Boolean get() = true
    override fun generate(): String {
        return "${left.generate()} / ${right.generate()}"
    }
}

class JsModulo(
    val left: JsValue,
    val right: JsValue
) : JsValue {
    override val needsParens: Boolean get() = true
    override fun generate(): String {
        return "${left.generate()} % ${right.generate()}"
    }
}

class JsField (
    val name: String,
    val parent: JsValue? = null
) : JsValue {
    override val needsParens: Boolean get() = false
    override fun generate(): String {
        return if(parent == null) name else "${parent.generateValue()}.$name"
    }
}

class JsFunctionCall(
    val function: JsValue,
    val args: List<JsValue>,
) : JsValue {
    override val needsParens: Boolean get() = false
    override fun generate(): String {
        return "${function.generate()}(${args.joinToString(", ") { it.generate() }})"
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
    override fun generate(): String {
        return if(value == null) "var $name"
        else "var $name = ${value.generate()}"
    }
}

class JsConstantDeclaration (
    override val name: String,
    override val value: JsValue
) : JsDeclaration() {
    override fun generate(): String {
        return "const $name = ${value.generate()}"
    }
}

class JsAssignment(
    val name: JsField,
    val value: JsValue
) : JsStatement, JsValue {
    override val needsParens: Boolean get() = true
    override fun generate(): String {
        return "$name = ${value.generate()}"
    }
}

class JsAddAssignment(
    val name: JsField,
    val value: JsValue
) : JsStatement, JsValue {
    override val needsParens: Boolean get() = true
    override fun generate(): String {
        return "$name += ${value.generate()}"
    }
}

class JsSubtractAssignment(
    val name: JsField,
    val value: JsValue
) : JsStatement, JsValue {
    override val needsParens: Boolean get() = true
    override fun generate(): String {
        return "$name -= ${value.generate()}"
    }
}

class JsMultiplyAssignment(
    val name: JsField,
    val value: JsValue
) : JsStatement, JsValue {
    override val needsParens: Boolean get() = true
    override fun generate(): String {
        return "$name *= ${value.generate()}"
    }
}

class JsDivideAssignment(
    val name: JsField,
    val value: JsValue
) : JsStatement, JsValue {
    override val needsParens: Boolean get() = true
    override fun generate(): String {
        return "$name /= ${value.generate()}"
    }
}

class JsModuloAssignment(
    val name: JsField,
    val value: JsValue
) : JsStatement, JsValue {
    override val needsParens: Boolean get() = true
    override fun generate(): String {
        return "$name %= ${value.generate()}"
    }
}

class JsIncrement(
    val name: JsField
) : JsStatement, JsValue {
    override val needsParens: Boolean get() = true
    override fun generate(): String {
        return "$name++"
    }
}

class JsBeforeIncrement(
    val name: JsField
) : JsStatement, JsValue {
    override val needsParens: Boolean get() = true
    override fun generate(): String {
        return "++$name"
    }
}

class JsDecrement(
    val name: JsField
) : JsStatement, JsValue {
    override val needsParens: Boolean get() = true
    override fun generate(): String {
        return "$name--"
    }
}

class JsBeforeDecrement(
    val name: JsField
) : JsStatement, JsValue {
    override val needsParens: Boolean get() = true
    override fun generate(): String {
        return "--$name"
    }
}

class JsEquals(
    val left: JsValue,
    val right: JsValue
) : JsValue {
    override val needsParens: Boolean get() = true
    override fun generate(): String {
        return "${left.generate()} == ${right.generate()}"
    }
}

class JsNotEquals(
    val left: JsValue,
    val right: JsValue
) : JsValue {
    override val needsParens: Boolean get() = true
    override fun generate(): String {
        return "${left.generate()} != ${right.generate()}"
    }
}

class JsLessThan(
    val left: JsValue,
    val right: JsValue
) : JsValue {
    override val needsParens: Boolean get() = true
    override fun generate(): String {
        return "${left.generate()} < ${right.generate()}"
    }
}

class JsLessThanOrEquals(
    val left: JsValue,
    val right: JsValue
) : JsValue {
    override val needsParens: Boolean get() = true
    override fun generate(): String {
        return "${left.generate()} <= ${right.generate()}"
    }
}

class JsGreaterThan(
    val left: JsValue,
    val right: JsValue
) : JsValue {
    override val needsParens: Boolean get() = true
    override fun generate(): String {
        return "${left.generate()} > ${right.generate()}"
    }
}

class JsGreaterThanOrEquals(
    val left: JsValue,
    val right: JsValue
) : JsValue {
    override val needsParens: Boolean get() = true
    override fun generate(): String {
        return "${left.generate()} >= ${right.generate()}"
    }
}

class JsAnd(
    val left: JsValue,
    val right: JsValue
) : JsValue {
    override val needsParens: Boolean get() = true
    override fun generate(): String {
        return "${left.generate()} && ${right.generate()}"
    }
}

class JsOr(
    val left: JsValue,
    val right: JsValue
) : JsValue {
    override val needsParens: Boolean get() = true
    override fun generate(): String {
        return "${left.generate()} || ${right.generate()}"
    }
}

class JsXOr(
    val left: JsValue,
    val right: JsValue
) : JsValue {
    override val needsParens: Boolean get() = true
    override fun generate(): String {
        return "${left.generate()} ^ ${right.generate()}"
    }
}

class JsNot(
    val value: JsValue
) : JsValue {
    override val needsParens: Boolean get() = true
    override fun generate(): String {
        return "!${value.generate()}"
    }
}

class JsNegate(
    val value: JsValue
) : JsValue {
    override val needsParens: Boolean get() = true
    override fun generate(): String {
        return "-${value.generate()}"
    }
}

class JsIf(
    val condition: JsValue,
    val then: JsTree,
    val elseStatement: JsTree? = null
) : JsStatement {
    override fun generate(): String {
        val statement = StringBuilder("if (${condition.generate()}) ${then.generateBlock()}")
        if(elseStatement != null) {
            statement.append(" else ${elseStatement.generateBlock()}")
        }
        return statement.toString()
    }
}

class JsWhile(
    val condition: JsValue,
    val body: JsTree
) : JsStatement {
    override fun generate(): String {
        return "while (${condition.generate()}) ${body.generateBlock()}"
    }
}

class JsDoWhile(
    val condition: JsValue,
    val body: JsTree
) : JsStatement {
    override fun generate(): String {
        return "do ${body.generateBlock()} while (${condition.generate()})"
    }
}

class JsFor(
    val init: JsStatement,
    val condition: JsValue,
    val update: JsStatement,
    val body: JsTree
) : JsStatement {
    override fun generate(): String {
        return "for (${init.generate()}; ${condition.generate()}; ${update.generate()}) ${body.generateBlock()}"
    }
}

class JsFunctionDeclaration(
    val name: String,
    val parameters: List<JsVariableDeclaration>,
    val body: JsTree
) : JsStatement {
    override fun generate(): String {
        return "function name${baseGenerate()}"
    }

    fun generateInClass(): String {
        return "name ${baseGenerate()}"
    }

    fun inline(): JsInlineFunction {
        return JsInlineFunction(parameters, body)
    }

    private fun baseGenerate(): String {
        return "(${parameters.joinToString(", ") {
            if(it.value != null) {
                "${it.name} = ${it.value.generate()}"
            } else {
                it.name
            }
        }}) {\n${body.generate()}\n}"
    }
}

class JsInlineFunction (
    val parameters: List<JsVariableDeclaration>,
    val body: JsTree
) : JsValue {
    override val needsParens: Boolean get() = true
    override fun generate(): String {
        return "function (${parameters.joinToString(", ") {
            if(it.value != null) {
                "${it.name} = ${it.value.generate()}"
            } else {
                it.name
            }
        }}) {\n${body.generate()}\n}"
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

    override fun generate(): String {
        val statement = StringBuilder("class $name")
        if(extends != null) {
            statement.append(" extends ${extends.generate()}")
        }
        statement.append(" {\n")
        statement.append(constructor.generateInClass())
        statement.append("\n")
        statement.append(functions.joinToString("\n") { it.generateInClass() })
        statement.append("\n}\n")
        staticFunctions.forEach {
            statement.append(JsAssignment(JsField(it.name, JsField(this.name)), it.inline()))
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
    override fun generate(): String {
        return "new ${type.generate()}(${parameters.joinToString(", ") { it.generate() }})"
    }
}

enum class JsLiteral : JsValue {
    TRUE, FALSE, NULL;
    override val needsParens: Boolean get() = false

    override fun generate(): String {
        return name.lowercase()
    }
}