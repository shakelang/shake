package io.github.shakelang.shake.interpreter.values

import io.github.shakelang.shake.interpreter.Scope
import io.github.shakelang.shake.interpreter.UnformattedInterpreterError
import io.github.shakelang.shake.interpreter.Variable
import io.github.shakelang.shake.parser.node.functions.ShakeFunctionCallNode
import io.github.shakelang.shake.parser.node.objects.ShakeClassConstructionNode
import org.reflections.Reflections
import java.beans.Expression
import java.lang.reflect.Modifier
import java.util.*
import kotlin.reflect.KClass

class Java : InterpreterValue {
    /**
     * This function will be executed when getting a child (variable.child)
     *
     * @param c the child to get
     * @return the child variable
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    override fun getChild(c: String): Variable {
        return Variable.finalOf(c, JavaUnknown(c))
    }

    /**
     * Returns the name of the type of [InterpreterValue] (To identify the type of value)
     *
     * @return the name of the [InterpreterValue]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    override val name: String = "java"

    class JavaUnknown(val unknownName: String) : InterpreterValue {

        /**
         * This function will be executed when getting a child (variable.child)
         *
         * @param c the child to get
         * @return the child variable
         *
         * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
         */
        override fun getChild(c: String): Variable {
            val name = "$unknownName.$c"
            return try {
                Variable.finalOf(c, JavaClass(Class.forName(name)))
            } catch (e: ClassNotFoundException) {
                Variable.finalOf(c, JavaUnknown(name))
            }
        }

        /**
         * This function will be executed when getting all child keys
         *
         * @return the keys of all children
         *
         * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
         */
        override val children: Array<String> get() {
            // Get names of all classes in this package using reflections
            val reflections = Reflections(unknownName)
            val allClasses = reflections.getSubTypesOf(
                Any::class.java
            ).toTypedArray()

            return (allClasses.map { it.simpleName }).toTypedArray()
        }

        /**
         * Returns the name of the type of [InterpreterValue] (To identify the type of value)
         *
         * @return the name of the [InterpreterValue]
         *
         * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
         */
        override val name: String get() = "java-unknown"

        override fun toString(): String {
            return "JavaUnknown{unknownName='$unknownName'}"
        }
    }

    class JavaClass(val javaClass: Class<*>) : InterpreterValue {

        /**
         * This function will be executed when getting a child (variable.child)
         *
         * @param c the child to get
         * @return the child variable
         *
         * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
         */
        override fun getChild(c: String): Variable {
            val fields = this.javaClass.fields
            for (i in fields.indices) if (fields[i].name == c && Modifier.isStatic(fields[i].modifiers)) {
                return try {
                    Variable.finalOf(c, JavaValue(fields[i].type, fields[i][null]))
                } catch (e: IllegalAccessException) {
                    e.printStackTrace()
                    throw UnformattedInterpreterError(e)
                }
            }
            val methods = this.javaClass.methods
            for (i in methods.indices) if (methods[i].name == c && Modifier.isStatic(methods[i].modifiers))
                return Variable.finalOf(c, JavaMethod(c, javaClass))
            val classes = this.javaClass.classes
            for (i in classes.indices) if (classes[i].simpleName == c && Modifier.isStatic(classes[i].modifiers))
                return Variable.finalOf(c, JavaClass(classes[i]))
            return Variable.finalOf(c, NullValue.NULL)
        }

        override val children: Array<String> get() {
            val children: MutableList<String> = ArrayList()
            val fields = this.javaClass.fields
            for (i in fields.indices) if (!containsString(children, fields[i].name)) children.add(
                fields[i].name
            )
            for (i in javaClass.methods.indices) if (!containsString(children, fields[i].name)) children.add(
                fields[i].name
            )
            for (i in javaClass.classes.indices) if (!containsString(children, fields[i].name)) children.add(
                fields[i].name
            )
            return children.toTypedArray()
        }

        override fun newInstance(node: ShakeClassConstructionNode, scope: Scope): InterpreterValue {
            val args = arrayOfNulls<Any>(node.args.size)
            for (i in 0 until node.args.size) {
                args[i] = scope.interpreter.visit(node.args[i], scope).toJava()
            }
            try {
                val allConstructors = this.javaClass.declaredConstructors
                for (constructor in allConstructors) {
                    val pType = constructor.parameterTypes
                    var matches = true
                    var i = 0
                    while (i < pType.size) {
                        if (i >= args.size || !pType[i].isInstance(args[i])) {
                            matches = false
                            break
                        }
                        i++
                    }
                    if (matches && i == args.size) {
                        return InterpreterValue.of(constructor.newInstance(*args))
                    }
                }
                throw UnformattedInterpreterError(
                    String.format("No constructor of class %s for arguments %s found",
                        this.javaClass.name, Arrays.toString(
                            Arrays.stream(args)
                                .map { arg: Any? -> arg!!.javaClass }.toArray()
                        )
                    )
                )
            } catch (e: Exception) {
                throw UnformattedInterpreterError(e)
            }
        }

        /**
         * Returns the name of the type of [InterpreterValue] (To identify the type of value)
         *
         * @return the name of the [InterpreterValue]
         *
         * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
         */
        override val name: String get() = "java-class"

        override fun toString(): String {
            return "JavaClass{javaClass=$javaClass}"
        }

        override fun toJava(): Any {
            return this.javaClass
        }

        companion object {
            private fun containsString(l: List<String>, s: String): Boolean {
                return l.stream().filter { str: String -> str == s }.findFirst().isPresent
            }
        }
    }

    @Suppress("unused")
    class JavaMethod : InterpreterValue {
        private val methodName: String
        private val obj: Any?

        constructor(name: String, `object`: Any?) : super() {
            this.methodName = name
            this.obj = `object`
        }

        constructor(name: String) {
            this.methodName = name
            obj = null
        }

        override fun invoke(node: ShakeFunctionCallNode, scope: Scope): InterpreterValue {
            val args = arrayOfNulls<Any>(node.args.size)
            for (i in 0 until node.args.size) {
                args[i] = scope.interpreter.visit(node.args[i], scope).toJava()
            }
            return try {
                val s = Expression(obj, methodName, args)
                s.execute()
                InterpreterValue.of(s.value)
            } catch (e: Exception) {
                throw UnformattedInterpreterError(e)
            }
        }

        /**
         * Returns the name of the type of [InterpreterValue] (To identify the type of value)
         *
         * @return the name of the [InterpreterValue]
         * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
         */
        override val name: String get() = "java-method"

        override fun toString(): String = "JavaMethod{name='$methodName, object=$obj}"
    }

    class JavaValue<T>(val type: Class<*>, val obj: T) : InterpreterValue {

        /**
         * This function will be executed when getting a child (variable.child)
         *
         * @param c the child to get
         * @return the child variable
         * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
         */
        override fun getChild(c: String): Variable {
            try {
                val fields = type.fields
                for (i in fields.indices) if (fields[i].name == c && !Modifier.isStatic(fields[i].modifiers)) return Variable.finalOf(
                    c, JavaValue(fields[i].type, fields[i][obj])
                )
                val methods = type.methods
                for (i in methods.indices) {
                    if (methods[i].name == c && !Modifier.isStatic(methods[i].modifiers)) return Variable.finalOf(
                        c,
                        JavaMethod(c, obj)
                    )
                }
                val classes = type.classes
                for (i in classes.indices) if (classes[i].simpleName == c && !Modifier.isStatic(
                        classes[i].modifiers
                    )
                ) throw UnformattedInterpreterError("Can't get non-static inner classes")
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            }
            return Variable.finalOf(c, NullValue.NULL)
        }

        override val children: Array<String> get() = JavaClass(this.javaClass).children

        /**
         * Returns the name of the type of [InterpreterValue] (To identify the type of value)
         *
         * @return the name of the [InterpreterValue]
         * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
         */
        override val name: String get() = "java-value"

        override fun toString(): String = "JavaValue{value=$obj}"

        @Suppress("UNCHECKED_CAST")
        override fun <T : InterpreterValue> to(type: KClass<T>): T {
            return if (type.isInstance(this)) this as T else InterpreterValue.of(obj).to(type)
        }

        override fun toJava(): T = obj
    }
}