package io.github.shakelang.shake.processor.program.creation

import io.github.shakelang.shake.parser.node.ShakeImportNode
import io.github.shakelang.shake.parser.node.ShakeVariableType
import io.github.shakelang.shake.processor.ShakeCodeProcessor
import io.github.shakelang.shake.processor.program.creation.code.CreationShakeInvokable
import io.github.shakelang.shake.processor.program.types.code.ShakeScope

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
        if(variable != null && variable is CreationShakeInvokable) {
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
            ShakeVariableType.Type.BOOLEAN -> then(CreationShakeType.Primitives.BOOLEAN)
            ShakeVariableType.Type.CHAR -> then(CreationShakeType.Primitives.CHAR)
            ShakeVariableType.Type.OBJECT -> {
                val namespace = (type as ShakeVariableType.Object).namespace ?: throw IllegalArgumentException("Object type must have subtype")
                val clzName = namespace.parts.joinToString (".")
                this.getClass(clzName) {
                    then(CreationShakeType.objectType(it))
                }
            }
            ShakeVariableType.Type.DYNAMIC -> TODO()
            ShakeVariableType.Type.ARRAY -> {
                val subtype = (type as ShakeVariableType.Array).subtype
                this.getType(subtype) {
                    then(CreationShakeType.array(it))
                }
            }
            ShakeVariableType.Type.VOID -> TODO()
        }
    }

    fun getType(clzName: String, then: (CreationShakeType) -> Unit) {
        this.getClass(clzName) {
            then(CreationShakeType.objectType(it))
        }

    }

    fun finish() {
        this.classRequirements.forEach {
            it.then(this.getClass(it.name) ?: throw IllegalArgumentException("Class ${it.name} not found"))
        }
    }

    open fun init(): CreationShakeScope {
        if(this.initialized) return this
        project.addScope(this)
        this.initialized = true
        return this
    }

    abstract val processor : ShakeCodeProcessor
}

class CreationFileScope(
    override val project: CreationShakeProject,
    override val parent: CreationShakeScope,
    val imports: List<ShakeImportNode>,
    val imported: Array<CreationShakePackage?>,
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

    override val processor: ShakeCodeProcessor
        get() = parent.processor

}