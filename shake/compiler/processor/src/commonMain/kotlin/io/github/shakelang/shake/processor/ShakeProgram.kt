package io.github.shakelang.shake.processor

import io.github.shakelang.shake.parser.node.*
import io.github.shakelang.shake.parser.node.functions.FunctionDeclarationNode
import io.github.shakelang.shake.parser.node.objects.ClassDeclarationNode
import io.github.shakelang.shake.parser.node.variables.VariableDeclarationNode

open class ShakeProject(
    open val subpackages: MutableList<ShakePackage> = mutableListOf(),
    open val classes: MutableMap<String, ShakeClass> = mutableMapOf(),
    open val functions: MutableMap<String, ShakeMethod> = mutableMapOf(),
    open val fields: MutableMap<String, ShakeField> = mutableMapOf()
) {

    open fun getPackage(name: String): ShakePackage {
        return subpackages.find { it.name == name } ?: ShakePackage(this, name).let {
            subpackages.add(it)
            it
        }
    }

    open fun getPackage(name: Array<String>): ShakePackage {
        if(name.isEmpty()) throw IllegalArgumentException("Cannot get package from empty name")
        return getPackage(name.first()).getPackage(name.drop(1).toTypedArray())
    }

    open fun putFile(name: String, contents: Tree) {
        contents.children.forEach {
            when (it) {
                is ClassDeclarationNode -> {
                    if(classes.containsKey(it.name)) {
                        throw Exception("Class ${it.name} already exists")
                    }
                    classes[it.name] = ShakeClass.from(this, it)
                }
                is FunctionDeclarationNode -> {
                    if(functions.containsKey(it.name)) {
                        throw Exception("Function ${it.name} already exists")
                    }
                    functions[it.name] = ShakeMethod.from(this, it)
                }
                is VariableDeclarationNode -> {
                    if(fields.containsKey(it.name)) {
                        throw Exception("Field ${it.name} already exists")
                    }
                    fields[it.name] = ShakeField.from(this, it)
                }
            }
        }
    }

    open fun putFile(name: Array<String>, contents: Tree) {
        val pkg = name.sliceArray(0 until name.size - 1)
        val file = name.last()
        getPackage(pkg).putFile(file, contents)
    }
    private val classRequirements = mutableListOf<ClassRequirement>()
    fun getClass(name: String, then: (ShakeClass) -> Unit) {
        this.classRequirements.add(ClassRequirement(name, then))
    }
    fun getClass(pkg: Array<String>, name: String): ShakeClass? {
        return this.getPackage(pkg).classes[name]
    }
    fun getClass(clz: String): ShakeClass? {
        val parts = clz.split(".")
        val name = parts.last()
        val pkg = parts.dropLast(1).toTypedArray()
        return if(pkg.isEmpty()) this.classes[name]
        else this.getPackage(pkg).classes[name]
    }
    fun getType(type: VariableType, then: (ShakeType) -> Unit) {
        when (type.type) {
            VariableType.Type.BYTE -> then(ShakeType.Primitives.BYTE)
            VariableType.Type.SHORT -> then(ShakeType.Primitives.SHORT)
            VariableType.Type.INTEGER -> then(ShakeType.Primitives.INT)
            VariableType.Type.LONG -> then(ShakeType.Primitives.LONG)
            VariableType.Type.FLOAT -> then(ShakeType.Primitives.FLOAT)
            VariableType.Type.DOUBLE -> then(ShakeType.Primitives.DOUBLE)
            VariableType.Type.BOOLEAN -> then(ShakeType.Primitives.BOOLEAN)
            VariableType.Type.CHAR -> then(ShakeType.Primitives.CHAR)
            VariableType.Type.OBJECT -> {
                val clz = mutableListOf<String>()
                var identifier: ValuedNode? = type.subtype!!
                while(identifier != null) {
                    if(identifier !is IdentifierNode) throw IllegalArgumentException("Invalid type ${type.subtype}")
                    clz.add(identifier.name)
                    identifier = identifier.parent
                }
                val clzName = clz.reversed().joinToString(".")
                this.getClass(clzName) {
                    then(ShakeType.objectType(it))
                }
            }
        }
    }
    fun finish() {
        classRequirements.forEach {
            val cls = this.getClass(it.name)
            it.then(cls!!)
        }
    }
    private class ClassRequirement(val name: String, val then: (ShakeClass) -> Unit)
}

open class ShakePackage (
    open val baseProject: ShakeProject,
    open val name: String,
    open val subpackages: MutableList<ShakePackage> = mutableListOf(),
    open val classes: MutableMap<String, ShakeClass> = mutableMapOf(),
    open val functions: MutableMap<String, ShakeMethod> = mutableMapOf(),
    open val fields: MutableMap<String, ShakeField> = mutableMapOf()
) {

    open fun getPackage(name: String): ShakePackage {
        return subpackages.find { it.name == name } ?: ShakePackage(baseProject, name).let {
            subpackages.add(it)
            it
        }
    }

    open fun getPackage(name: Array<String>): ShakePackage {
        return name.fold(this) { acc, pkgName -> acc.getPackage(pkgName) }
    }

    open fun putFile(name: String, contents: Tree) {
        contents.children.forEach {
            when (it) {
                is ClassDeclarationNode -> {
                    if(classes.containsKey(it.name)) {
                        throw Exception("Class ${it.name} already exists")
                    }
                    classes[it.name] = ShakeClass.from(baseProject, it)
                }
                is FunctionDeclarationNode -> {
                    if(functions.containsKey(it.name)) {
                        throw Exception("Function ${it.name} already exists")
                    }
                    functions[it.name] = ShakeMethod.from(baseProject, it)
                }
                is VariableDeclarationNode -> {
                    if(fields.containsKey(it.name)) {
                        throw Exception("Field ${it.name} already exists")
                    }
                    fields[it.name] = ShakeField.from(baseProject, it)
                }
            }
        }
    }

    open fun putFile(name: Array<String>, contents: Tree) {
        val pkg = name.sliceArray(0 until name.size - 1)
        val file = name.last()
        getPackage(pkg).putFile(file, contents)
    }
}

open class ShakeFile (
    open val name: String,
    open val contents: Tree,
)

class ShakeClass (
    val name: String,
    val methods: List<ShakeClassMethod>,
    val fields: List<ShakeClassField>,
    val staticMethods: List<ShakeClassMethod>,
    val staticFields: List<ShakeClassField>,
    val constructors: List<ShakeConstructor> = listOf(),
) {
    var superClass: ShakeClass? = null
        private set
    private var _interfaces: List<ShakeClass?> = listOf()
        private set
    val interfaces: List<ShakeClass>
        get() = _interfaces.map { it!! }

    fun lateinitSuper(): (ShakeClass?) -> ShakeClass? {
        return {
            superClass = it
            it
        }
    }

    fun lateinitInterfaces(number: Int): List<(ShakeClass) -> ShakeClass> {
        val interfaces = MutableList<ShakeClass?>(number) { null }
        this._interfaces = interfaces
        return interfaces.indices.map { i -> {
                interfaces[i] = it
                it
            }
        }
    }

    companion object {
        fun from(baseProject: ShakeProject, clz: ClassDeclarationNode): ShakeClass {
            val methods = clz.methods.filter { !it.isStatic }.map {
                val method = ShakeClassMethod(
                    it.name,
                    "",
                    it.isStatic,
                    it.isFinal,
                    false,
                    false,
                    false,
                    it.access == AccessDescriber.PRIVATE,
                    it.access == AccessDescriber.PROTECTED,
                    it.access == AccessDescriber.PUBLIC,
                )
                method.lateinitReturnType().let { run -> baseProject.getType(it.type) { type -> run(type) } }
                method
                    .lateinitParameterTypes(it.args.map { p -> p.name })
                    .forEachIndexed { i, run -> baseProject.getType(it.args[i].type) { type -> run(type) } }
                method
            }
            val staticMethods = clz.methods.filter { it.isStatic }.map {
                val method = ShakeClassMethod(
                    it.name,
                    "",
                    it.isStatic,
                    it.isFinal,
                    false,
                    false,
                    false,
                    it.access == AccessDescriber.PRIVATE,
                    it.access == AccessDescriber.PROTECTED,
                    it.access == AccessDescriber.PUBLIC,
                )
                method.lateinitReturnType().let { run -> baseProject.getType(it.type) { type -> run(type) } }
                method
                    .lateinitParameterTypes(it.args.map { p -> p.name })
                    .forEachIndexed { i, run -> baseProject.getType(it.args[i].type) { type -> run(type) } }
                method
            }
            val fields = clz.fields.filter { !it.isStatic }.map {
                val field = ShakeClassField(
                    it.name,
                    it.isStatic,
                    it.isFinal,
                    false,
                    it.access == AccessDescriber.PRIVATE,
                    it.access == AccessDescriber.PROTECTED,
                    it.access == AccessDescriber.PUBLIC,
                )
                field.lateinitType().let { run -> baseProject.getType(it.type) { type -> run(type) } }
                field
            }
            val staticFields = clz.fields.filter { it.isStatic }.map {
                val field = ShakeClassField(
                    it.name,
                    it.isStatic,
                    it.isFinal,
                    false,
                    it.access == AccessDescriber.PRIVATE,
                    it.access == AccessDescriber.PROTECTED,
                    it.access == AccessDescriber.PUBLIC,
                )
                field.lateinitType().let { run -> baseProject.getType(it.type) { type -> run(type) } }
                field
            }
            val constructors = clz.constructors.map {
                val constr = ShakeConstructor(
                    it.name,
                    "",
                    false,
                    it.access == AccessDescriber.PRIVATE,
                    it.access == AccessDescriber.PROTECTED,
                    it.access == AccessDescriber.PUBLIC,
                )
                constr.lateinitParameterTypes(it.args.map { p -> p.name })
                    .forEachIndexed { i, run -> baseProject.getType(it.args[i].type) { type -> run(type) } }
                constr
            }
            /*
            TODO Interface & Super
            val superClass = cl.lateinitSuper()
            val interfaces = cl.lateinitInterfaces(clz..size)
             */
            return ShakeClass(
                clz.name,
                methods,
                fields,
                staticMethods,
                staticFields,
                constructors,
            )
        }
    }
}

abstract class ShakeType (
    val name: String,
) {
    abstract val kind: Kind

    enum class Kind {
        PRIMITIVE,
        OBJECT,
        ARRAY,
    }
    enum class PrimitiveType {
        BOOLEAN,
        BYTE,
        CHAR,
        SHORT,
        INT,
        LONG,
        FLOAT,
        DOUBLE,
        UNSIGNED_BYTE,
        UNSIGNED_SHORT,
        UNSIGNED_INT,
        UNSIGNED_LONG,
        VOID,
    }
    open class Primitive (
        name: String,
        val type: PrimitiveType,
    ) : ShakeType(name) {
        override val kind: Kind
            get() = Kind.PRIMITIVE
    }

    class Object (
        name: String,
        val clazz: ShakeClass
    ) : ShakeType(name) {
        override val kind: Kind
            get() = Kind.OBJECT
    }

    class Array (
        name: String,
        val elementType: ShakeType
    ) : ShakeType(name) {
        override val kind: Kind
            get() = Kind.ARRAY
    }

    object Primitives {
        val BOOLEAN = Primitive("boolean", PrimitiveType.BOOLEAN)
        val BYTE = Primitive("byte", PrimitiveType.BYTE)
        val CHAR = Primitive("char", PrimitiveType.CHAR)
        val SHORT = Primitive("short", PrimitiveType.SHORT)
        val INT = Primitive("int", PrimitiveType.INT)
        val LONG = Primitive("long", PrimitiveType.LONG)
        val FLOAT = Primitive("float", PrimitiveType.FLOAT)
        val DOUBLE = Primitive("double", PrimitiveType.DOUBLE)
        val UNSIGNED_BYTE = Primitive("unsigned byte", PrimitiveType.UNSIGNED_BYTE)
        val UNSIGNED_SHORT = Primitive("unsigned short", PrimitiveType.UNSIGNED_SHORT)
        val UNSIGNED_INT = Primitive("unsigned int", PrimitiveType.UNSIGNED_INT)
        val UNSIGNED_LONG = Primitive("unsigned long", PrimitiveType.UNSIGNED_LONG)
        val VOID = Primitive("void", PrimitiveType.VOID)
    }

    companion object {
        fun array(elementType: ShakeType): ShakeType {
            return Array("${elementType.name}[]", elementType)
        }
        fun objectType(clazz: ShakeClass): ShakeType {
            return Object(clazz.name, clazz)
        }
    }
}

open class ShakeMethod (
    val name: String,
    val body: String,
    val isStatic: Boolean,
    val isFinal: Boolean,
    val isAbstract: Boolean,
    val isSynchronized: Boolean,
    val isStrict: Boolean,
    val isPrivate: Boolean,
    val isProtected: Boolean,
    val isPublic: Boolean,
) {
    lateinit var parameters: List<ShakeParameter>
        private set
    lateinit var returnType: ShakeType
        private set

    constructor(
        name: String,
        parameters: List<ShakeParameter>,
        returnType: ShakeType,
        body: String,
        isStatic: Boolean,
        isFinal: Boolean,
        isAbstract: Boolean,
        isSynchronized: Boolean,
        isStrict: Boolean,
        isPrivate: Boolean,
        isProtected: Boolean,
        isPublic: Boolean
    ): this(name, body, isStatic, isFinal, isAbstract, isSynchronized, isStrict, isPrivate, isProtected, isPublic) {
        this.parameters = parameters
        this.returnType = returnType
    }

    fun lateinitReturnType(): (ShakeType) -> ShakeType {
        return {
            returnType = it
            it
        }
    }

    fun lateinitParameterTypes(names: List<String>): List<(ShakeType) -> ShakeType> {
        this.parameters = names.map {
            ShakeParameter(it)
        }
        return this.parameters.map {
            it.lateinitType()
        }
    }

    companion object {
        fun from(baseProject: ShakeProject, node: FunctionDeclarationNode): ShakeMethod {
            return ShakeMethod(
                node.name,
                "",
                node.isStatic,
                node.isFinal,
                false,
                false,
                false,
                node.access == AccessDescriber.PRIVATE,
                node.access == AccessDescriber.PROTECTED,
                node.access == AccessDescriber.PUBLIC
            ).let {
                it.lateinitReturnType().let { run -> baseProject.getType(node.type) { t -> run(t) } }
                it.lateinitParameterTypes(node.args.map { p -> p.name })
                    .forEachIndexed { i, run -> baseProject.getType(node.args[i].type) { t -> run(t) } }
                it
            }
        }
    }
}

open class ShakeField (
    val name: String,
    val isStatic: Boolean,
    val isFinal: Boolean,
    val isAbstract: Boolean,
    val isPrivate: Boolean,
    val isProtected: Boolean,
    val isPublic: Boolean,
) {
    lateinit var type: ShakeType
        private set

    fun lateinitType(): (ShakeType) -> ShakeType {
        return {
            type = it
            it
        }
    }

    companion object {
        fun from(baseProject: ShakeProject, node: VariableDeclarationNode): ShakeField {
            return ShakeField(
                node.name,
                node.isStatic,
                node.isFinal,
                false,
                node.access == AccessDescriber.PRIVATE,
                node.access == AccessDescriber.PROTECTED,
                node.access == AccessDescriber.PUBLIC
            ).let {
                it.lateinitType().let { run -> baseProject.getType(node.type) { t -> run(t) } }
                it
            }
        }
    }
}

open class ShakeConstructor (
    val name: String?,
    val body: String,
    val isStrict: Boolean,
    val isPrivate: Boolean,
    val isProtected: Boolean,
    val isPublic: Boolean,
) {
    lateinit var parameters: List<ShakeParameter>
        private set

    constructor(
        name: String,
        parameters: List<ShakeParameter>,
        body: String,
        isStrict: Boolean,
        isPrivate: Boolean,
        isProtected: Boolean,
        isPublic: Boolean
    ): this(name, body, isStrict, isPrivate, isProtected, isPublic) {
        this.parameters = parameters
    }

    fun lateinitParameterTypes(names: List<String>): List<(ShakeType) -> ShakeType> {
        this.parameters = names.map {
            ShakeParameter(it)
        }
        return this.parameters.map {
            it.lateinitType()
        }
    }
}

class ShakeClassMethod (
    name: String,
    body: String,
    isStatic: Boolean,
    isFinal: Boolean,
    isAbstract: Boolean,
    isSynchronized: Boolean,
    isStrict: Boolean,
    isPrivate: Boolean,
    isProtected: Boolean,
    isPublic: Boolean,
) : ShakeMethod(
    name,
    body,
    isStatic,
    isFinal,
    isAbstract,
    isSynchronized,
    isStrict,
    isPrivate,
    isProtected,
    isPublic
) {
    lateinit var clazz: ShakeClass
        private set
}

class ShakeClassField (
    name: String,
    isStatic: Boolean,
    isFinal: Boolean,
    isAbstract: Boolean,
    isPrivate: Boolean,
    isProtected: Boolean,
    isPublic: Boolean,
): ShakeField(
    name,
    isStatic,
    isFinal,
    isAbstract,
    isPrivate,
    isProtected,
    isPublic
) {
    lateinit var clazz: ShakeClass
        private set
}

class ShakeParameter (
    val name: String,
) {
    constructor(name: String, type: ShakeType): this(name) {
        this.type = type
    }

    lateinit var type: ShakeType
        private set

    fun lateinitType(): (ShakeType) -> ShakeType {
        return {
            type = it
            it
        }
    }
}