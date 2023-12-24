package com.shakelang.shake.processor.program.creation

import com.shakelang.shake.parser.node.ShakeImportNode
import com.shakelang.shake.parser.node.ShakeVariableType
import com.shakelang.shake.processor.ShakeASTProcessor
import com.shakelang.shake.processor.program.creation.code.CreationShakeInvokable
import com.shakelang.shake.processor.program.types.code.ShakeScope

abstract class CreationShakeScope : ShakeScope {
    val classRequirements = mutableListOf<CreationShakeProject.ClassRequirement>()
    private var initialized: Boolean = false

    abstract val project: CreationShakeProject

    abstract override val parent: CreationShakeScope?
    abstract override fun get(name: String): CreationShakeAssignable?
    abstract fun set(value: CreationShakeDeclaration)
    abstract override fun getFunctions(name: String): List<CreationShakeMethod>
    abstract fun setFunctions(function: CreationShakeMethod)
    abstract override fun getClass(name: String): CreationShakeClass?
    abstract fun setClass(klass: CreationShakeClass)
    override fun getInvokable(name: String): List<CreationShakeInvokable> {
        val functions = getFunctions(name)
        val variable = get(name)
        if (variable != null && variable is CreationShakeInvokable) {
            return listOf(variable, *functions.toTypedArray())
        }
        return functions
    }

    override fun use(name: String) {
        val declaration = get(name)
    }

    fun getClass(name: String, then: (CreationShakeClass) -> Unit) {
        this.init()
        this.classRequirements.add(CreationShakeProject.ClassRequirement(name, then))
    }

    fun getType(type: ShakeVariableType, then: (CreationShakeType) -> Unit) {
        when (type.type) {
            ShakeVariableType.Type.BYTE -> then(CreationShakeType.Primitives.BYTE)
            ShakeVariableType.Type.SHORT -> then(CreationShakeType.Primitives.SHORT)
            ShakeVariableType.Type.INTEGER -> then(CreationShakeType.Primitives.INT)
            ShakeVariableType.Type.LONG -> then(CreationShakeType.Primitives.LONG)
            ShakeVariableType.Type.FLOAT -> then(CreationShakeType.Primitives.FLOAT)
            ShakeVariableType.Type.DOUBLE -> then(CreationShakeType.Primitives.DOUBLE)
            ShakeVariableType.Type.UNSIGNED_BYTE -> then(CreationShakeType.Primitives.UBYTE)
            ShakeVariableType.Type.UNSIGNED_SHORT -> then(CreationShakeType.Primitives.USHORT)
            ShakeVariableType.Type.UNSIGNED_INTEGER -> then(CreationShakeType.Primitives.UINT)
            ShakeVariableType.Type.UNSIGNED_LONG -> then(CreationShakeType.Primitives.ULONG)
            ShakeVariableType.Type.BOOLEAN -> then(CreationShakeType.Primitives.BOOLEAN)
            ShakeVariableType.Type.CHAR -> then(CreationShakeType.Primitives.CHAR)
            ShakeVariableType.Type.OBJECT -> {
                val namespace = (type as ShakeVariableType.Object).namespace
                    ?: throw IllegalArgumentException("Object type must have subtype")
                val clzName = namespace.parts.joinToString(".")
                this.getClass(clzName) {
                    then(CreationShakeType.objectType(it))
                }
            }

            ShakeVariableType.Type.DYNAMIC -> then(CreationShakeType.Primitives.DYNAMIC)
            ShakeVariableType.Type.ARRAY -> {
                val subtype = (type as ShakeVariableType.Array).subtype
                this.getType(subtype) {
                    then(CreationShakeType.array(it))
                }
            }

            ShakeVariableType.Type.VOID -> then(CreationShakeType.Primitives.VOID)
            ShakeVariableType.Type.UNKNOWN -> then(CreationShakeType.Primitives.DYNAMIC) // TODO: Change this
        }
    }

    fun getType(clzName: String) : CreationShakeType {
        return CreationShakeType.objectType(this.getClass(clzName) ?: throw IllegalArgumentException("Class $clzName not found"))
    }

    fun getType(type: ShakeVariableType): CreationShakeType {
        return when(type.type) {
            ShakeVariableType.Type.OBJECT -> {
                val namespace = (type as ShakeVariableType.Object).namespace
                    ?: throw IllegalArgumentException("Object type must have subtype")
                val clzName = namespace.parts.joinToString(".")
                this.getType(clzName)
            }

            ShakeVariableType.Type.ARRAY -> {
                val subtype = (type as ShakeVariableType.Array).subtype
                this.getType(subtype)
            }

            ShakeVariableType.Type.BYTE -> CreationShakeType.Primitives.BYTE
            ShakeVariableType.Type.SHORT -> CreationShakeType.Primitives.SHORT
            ShakeVariableType.Type.INTEGER -> CreationShakeType.Primitives.INT
            ShakeVariableType.Type.LONG -> CreationShakeType.Primitives.LONG
            ShakeVariableType.Type.FLOAT -> CreationShakeType.Primitives.FLOAT
            ShakeVariableType.Type.DOUBLE -> CreationShakeType.Primitives.DOUBLE
            ShakeVariableType.Type.UNSIGNED_BYTE -> CreationShakeType.Primitives.UBYTE
            ShakeVariableType.Type.UNSIGNED_SHORT -> CreationShakeType.Primitives.USHORT
            ShakeVariableType.Type.UNSIGNED_INTEGER -> CreationShakeType.Primitives.UINT
            ShakeVariableType.Type.UNSIGNED_LONG -> CreationShakeType.Primitives.ULONG
            ShakeVariableType.Type.BOOLEAN -> CreationShakeType.Primitives.BOOLEAN
            ShakeVariableType.Type.CHAR -> CreationShakeType.Primitives.CHAR
            ShakeVariableType.Type.DYNAMIC -> CreationShakeType.Primitives.DYNAMIC
            ShakeVariableType.Type.VOID -> CreationShakeType.Primitives.VOID
            ShakeVariableType.Type.UNKNOWN -> CreationShakeType.Primitives.DYNAMIC // TODO: Change this
        }
    }

    open fun init(): CreationShakeScope {
        if (this.initialized) return this
        project.addScope(this)
        this.initialized = true
        return this
    }

    abstract val processor: ShakeASTProcessor
}

class CreationFileScope(
    override val project: CreationShakeProject,
    override val parent: CreationShakeScope,
    val imports: List<ShakeImportNode>,
    val imported: Array<CreationShakePackage?>
) : CreationShakeScope() {

    override fun get(name: String): CreationShakeAssignable? {
        return imports.zip(imported).filter {
            val last = it.first.import.last()
            last == name || last == "*"
        }.firstNotNullOfOrNull {
            it.second!!.fields.find { f -> f.name == name }
        } ?: parent.get(name)
    }

    override fun set(value: CreationShakeDeclaration) {
        parent.set(value)
    }

    override fun getFunctions(name: String): List<CreationShakeMethod> {
        return imports.zip(imported).filter {
            val last = it.first.import.last()
            last == name || last == "*"
        }.flatMap {
            it.second!!.functions.filter { f -> f.name == name }
        } + parent.getFunctions(name)
    }

    override fun setFunctions(function: CreationShakeMethod) {
        parent.setFunctions(function)
    }

    override fun getClass(name: String): CreationShakeClass? {
        return imports.zip(imported).filter {
            val last = it.first.import.last()
            last == name || last == "*"
        }.firstNotNullOfOrNull {
            it.second!!.classes.find { c -> c.name == name }
        } ?: parent.getClass(name)
    }

    override fun setClass(klass: CreationShakeClass) {
        parent.setClass(klass)
    }

    override val processor: ShakeASTProcessor
        get() = parent.processor
}
