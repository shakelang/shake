package com.shakelang.shake.js.native.shake.lang

import com.shakelang.shake.js.ShakeJsGenerator
import com.shakelang.shake.js.native.NativeClass
import com.shakelang.shake.js.native.NativeField
import com.shakelang.shake.js.native.NativeFunction
import com.shakelang.shake.js.output.*
import com.shakelang.shake.processor.program.types.code.ShakeInvocation
import com.shakelang.shake.processor.program.types.code.values.ShakeFieldUsage
import kotlin.String

// package shake.lang;
//
// native class String {
//
//     native static String valueOf(byte[] bytes)
//     native static String valueOf(byte[] bytes, int offset, int length)
//     native static String valueOf(char[] chars)
//     native static String valueOf(char[] chars, int offset, int length)
//     native static String valueOf(byte b)
//     native static String valueOf(shorts c)
//     native static String valueOf(int i)
//     native static String valueOf(long l)
//     native static String valueOf(float f)
//     native static String valueOf(doubles d)
//     native static String valueOf(boolean b)
//     native static String valueOf(Object obj)
//
//     native int length
//     native char charAt(int index)
//     native int indexOf(String s)
//     native int indexOf(String s, int fromIndex)
//     native int lastIndexOf(String s)
//     native int lastIndexOf(String s, int fromIndex)
//     native String substring(int beginIndex)
//     native String substring(int beginIndex, int endIndex)
//     native String toLowerCase()
//     native String toUpperCase()
//     native String trim()
//     native String replace(char oldChar, char newChar)
//     native String replace(String oldStr, String newStr)
//     native String concat(String str)
//     native String[] split(String regex)
//     native String[] split(String regex, int limit)
//     native String toString()
//     native byte[] getBytes()
//     native char[] toCharArray()
//     native override boolean equals(Object obj)
//     native override int hashCode()
// }

class String : NativeClass {

    override val qualifiedName: String = "shake.lang.String"

    companion object {
        val str = "Lshake.lang.String"
    }

    // / Static methods

    // valueOf(byte[] bytes)
    class FunctionValueOf0 : NativeFunction {
        override val signature: String = "valueOf([B)"
        override fun handle(generator: ShakeJsGenerator, invokation: ShakeInvocation): JsValuedStatement {
            if (invokation.arguments.size != 1) throw IllegalArgumentException("Expected 1 argument, got ${invokation.arguments.size}")
            if (invokation.parent != null) throw IllegalArgumentException("Cannot invoke static method from instance")
            val arg0 = generator.visitValue(invokation.arguments[0])
            return JsFunctionCall(JsField("fromCharCode", JsField("String")), listOf(arg0))
        }
    }

    // valueOf(byte[] bytes, int offset, int length)
    class FunctionValueOf1 : NativeFunction {
        override val signature: String = "valueOf([B, I, I)"
        override fun handle(generator: ShakeJsGenerator, invokation: ShakeInvocation): JsValuedStatement {
            if (invokation.arguments.size != 3) throw IllegalArgumentException("String.valueOf(byte[] bytes, int offset, int length) takes exactly 4 arguments")
            if (invokation.parent != null) throw IllegalArgumentException("Cannot invoke static method from instance")
            val arg0 = generator.visitValue(invokation.arguments[0])
            val arg1 = generator.visitValue(invokation.arguments[1])
            val arg2 = generator.visitValue(invokation.arguments[2])

            return JsFunctionCall(
                JsField("fromCharCode", JsField("String")),
                listOf(
                    JsFunctionCall(JsField("slice", arg0), listOf(arg1, JsSubtract(arg2, arg1))),
                ),
            )
        }
    }

    // valueOf(char[] chars)
    class FunctionValueOf2 : NativeFunction {
        override val signature: String = "valueOf(char[] chars)"
        override fun handle(generator: ShakeJsGenerator, invokation: ShakeInvocation): JsValuedStatement {
            if (invokation.arguments.size != 1) throw IllegalArgumentException("String.valueOf(char[] chars) takes exactly 1 argument")
            if (invokation.parent != null) throw IllegalArgumentException("Cannot invoke static method from instance")
            val arg0 = generator.visitValue(invokation.arguments[0])
            return JsFunctionCall(JsField("join", arg0), listOf(JsStringLiteral("")))
        }
    }

    // valueOf(char[] chars, int offset, int length)
    class FunctionValueOf3 : NativeFunction {
        override val signature: String = "valueOf(char[] chars, int offset, int length)"
        override fun handle(generator: ShakeJsGenerator, invokation: ShakeInvocation): JsValuedStatement {
            if (invokation.arguments.size != 3) throw IllegalArgumentException("String.valueOf(char[] chars, int offset, int length) takes exactly 4 arguments")
            if (invokation.parent != null) throw IllegalArgumentException("Cannot invoke static method from instance")

            val arg0 = generator.visitValue(invokation.arguments[0])
            val arg1 = generator.visitValue(invokation.arguments[1])
            val arg2 = generator.visitValue(invokation.arguments[2])

            return JsFunctionCall(
                JsField(
                    "join",
                    JsFunctionCall(JsField("slice", arg0), listOf(arg1, JsSubtract(arg2, arg1))),
                ),
                listOf(),
            )
        }
    }

    // valueOf(byte b)
    class FunctionValueOf4 : NativeFunction {
        override val signature: String = "valueOf(byte b)"
        override fun handle(generator: ShakeJsGenerator, invokation: ShakeInvocation): JsValuedStatement {
            if (invokation.arguments.size != 1) throw IllegalArgumentException("String.valueOf(byte b) takes exactly 1 argument")
            if (invokation.parent != null) throw IllegalArgumentException("Cannot invoke static method from instance")
            val arg0 = generator.visitValue(invokation.arguments[0])
            return JsFunctionCall(JsField("fromCharCode", JsField("String")), listOf(arg0))
        }
    }

    // valueOf(shorts c)
    class FunctionValueOf5 : NativeFunction {
        override val signature: String = "valueOf(shorts c)"
        override fun handle(generator: ShakeJsGenerator, invokation: ShakeInvocation): JsValuedStatement {
            if (invokation.arguments.size != 1) throw IllegalArgumentException("String.valueOf(shorts c) takes exactly 1 argument")
            if (invokation.parent != null) throw IllegalArgumentException("Cannot invoke static method from instance")
            val arg0 = generator.visitValue(invokation.arguments[0])
            return JsFunctionCall(JsField("fromCharCode", JsField("String")), listOf(arg0))
        }
    }

    // valueOf(int i)
    class FunctionValueOf6 : NativeFunction {
        override val signature: String = "valueOf(int i)"
        override fun handle(generator: ShakeJsGenerator, invokation: ShakeInvocation): JsValuedStatement {
            if (invokation.arguments.size != 1) throw IllegalArgumentException("String.valueOf(int i) takes exactly 1 argument")
            if (invokation.parent != null) throw IllegalArgumentException("Cannot invoke static method from instance")
            val arg0 = generator.visitValue(invokation.arguments[0])
            return JsFunctionCall(JsField("fromCharCode", JsField("String")), listOf(arg0))
        }
    }

    // valueOf(long l)
    class FunctionValueOf7 : NativeFunction {
        override val signature: String = "valueOf(long l)"
        override fun handle(generator: ShakeJsGenerator, invokation: ShakeInvocation): JsValuedStatement {
            if (invokation.arguments.size != 1) throw IllegalArgumentException("String.valueOf(long l) takes exactly 1 argument")
            if (invokation.parent != null) throw IllegalArgumentException("Cannot invoke static method from instance")
            val arg0 = generator.visitValue(invokation.arguments[0])
            return JsFunctionCall(JsField("fromCharCode", JsField("String")), listOf(arg0))
        }
    }

    // valueOf(float f)
    class FunctionValueOf8 : NativeFunction {
        override val signature: String = "valueOf(float f)"
        override fun handle(generator: ShakeJsGenerator, invokation: ShakeInvocation): JsValuedStatement {
            if (invokation.arguments.size != 1) throw IllegalArgumentException("String.valueOf(float f) takes exactly 1 argument")
            if (invokation.parent != null) throw IllegalArgumentException("Cannot invoke static method from instance")
            val arg0 = generator.visitValue(invokation.arguments[0])
            return JsFunctionCall(JsField("fromCharCode", JsField("String")), listOf(arg0))
        }
    }

    // valueOf(doubles d)
    class FunctionValueOf9 : NativeFunction {
        override val signature: String = "valueOf(doubles d)"
        override fun handle(generator: ShakeJsGenerator, invokation: ShakeInvocation): JsValuedStatement {
            if (invokation.arguments.size != 1) throw IllegalArgumentException("String.valueOf(doubles d) takes exactly 1 argument")
            if (invokation.parent != null) throw IllegalArgumentException("Cannot invoke static method from instance")
            val arg0 = generator.visitValue(invokation.arguments[0])
            return JsFunctionCall(JsField("fromCharCode", JsField("String")), listOf(arg0))
        }
    }

    // valueOf(boolean b)
    class FunctionValueOf10 : NativeFunction {
        override val signature: String = "valueOf(boolean b)"
        override fun handle(generator: ShakeJsGenerator, invokation: ShakeInvocation): JsValuedStatement {
            if (invokation.arguments.size != 1) throw IllegalArgumentException("String.valueOf(boolean b) takes exactly 1 argument")
            if (invokation.parent != null) throw IllegalArgumentException("Cannot invoke static method from instance")
            val arg0 = generator.visitValue(invokation.arguments[0])
            return JsFunctionCall(JsField("fromCharCode", JsField("String")), listOf(arg0))
        }
    }

    // valueOf(char c)
    class FunctionValueOf11 : NativeFunction {
        override val signature: String = "valueOf(char c)"
        override fun handle(generator: ShakeJsGenerator, invokation: ShakeInvocation): JsValuedStatement {
            if (invokation.arguments.size != 1) throw IllegalArgumentException("String.valueOf(char c) takes exactly 1 argument")
            if (invokation.parent != null) throw IllegalArgumentException("Cannot invoke static method from instance")
            val arg0 = generator.visitValue(invokation.arguments[0])
            return JsFunctionCall(JsField("fromCharCode", JsField("String")), listOf(arg0))
        }
    }

    // instance fields

    // length
    class FieldLength : NativeField {
        override val signature: String = "length"
        override fun handle(generator: ShakeJsGenerator, fieldUsage: ShakeFieldUsage): JsValue {
            if (fieldUsage.receiver == null) throw IllegalArgumentException("Cannot access instance field without receiver")
            return JsField("length", generator.visitValue(fieldUsage.receiver!!))
        }
    }

    // instance methods

    // charAt(int index)
    class MethodCharAt : NativeFunction {
        override val signature: String = "charAt(int index)"
        override fun handle(generator: ShakeJsGenerator, invokation: ShakeInvocation): JsValuedStatement {
            if (invokation.arguments.size != 1) throw IllegalArgumentException("String.charAt(int index) takes exactly 1 argument")
            if (invokation.parent == null) throw IllegalArgumentException("Cannot invoke instance method from static context")
            val arg0 = generator.visitValue(invokation.arguments[0])
            val receiver = generator.visitValue(invokation.parent!!)
            return JsFunctionCall(JsField("charAt", receiver), listOf(arg0))
        }
    }

    // indexOf(String str)
    class MethodIndexOf : NativeFunction {
        override val signature: String = "indexOf(String str)"
        override fun handle(generator: ShakeJsGenerator, invokation: ShakeInvocation): JsValuedStatement {
            if (invokation.arguments.size != 1) throw IllegalArgumentException("String.indexOf(String str) takes exactly 1 argument")
            if (invokation.parent == null) throw IllegalArgumentException("Cannot invoke instance method from static context")
            val arg0 = generator.visitValue(invokation.arguments[0])
            val receiver = generator.visitValue(invokation.parent!!)
            return JsFunctionCall(JsField("indexOf", receiver), listOf(arg0))
        }
    }

    // indexOf(String str, int fromIndex)
    class MethodIndexOf2 : NativeFunction {
        override val signature: String = "indexOf(String str, int fromIndex)"
        override fun handle(generator: ShakeJsGenerator, invokation: ShakeInvocation): JsValuedStatement {
            if (invokation.arguments.size != 2) throw IllegalArgumentException("String.indexOf(String str, int fromIndex) takes exactly 2 arguments")
            if (invokation.parent == null) throw IllegalArgumentException("Cannot invoke instance method from static context")
            val arg0 = generator.visitValue(invokation.arguments[0])
            val arg1 = generator.visitValue(invokation.arguments[1])
            val receiver = generator.visitValue(invokation.parent!!)
            return JsFunctionCall(JsField("indexOf", receiver), listOf(arg0, arg1))
        }
    }

    // lastIndexOf(String str)
    class MethodLastIndexOf : NativeFunction {
        override val signature: String = "lastIndexOf(String str)"
        override fun handle(generator: ShakeJsGenerator, invokation: ShakeInvocation): JsValuedStatement {
            if (invokation.arguments.size != 1) throw IllegalArgumentException("String.lastIndexOf(String str) takes exactly 1 argument")
            if (invokation.parent == null) throw IllegalArgumentException("Cannot invoke instance method from static context")
            val arg0 = generator.visitValue(invokation.arguments[0])
            val receiver = generator.visitValue(invokation.parent!!)
            return JsFunctionCall(JsField("lastIndexOf", receiver), listOf(arg0))
        }
    }

    // lastIndexOf(String str, int fromIndex)
    class MethodLastIndexOf2 : NativeFunction {
        override val signature: String = "lastIndexOf(String str, int fromIndex)"
        override fun handle(generator: ShakeJsGenerator, invokation: ShakeInvocation): JsValuedStatement {
            if (invokation.arguments.size != 2) throw IllegalArgumentException("String.lastIndexOf(String str, int fromIndex) takes exactly 2 arguments")
            if (invokation.parent == null) throw IllegalArgumentException("Cannot invoke instance method from static context")
            val arg0 = generator.visitValue(invokation.arguments[0])
            val arg1 = generator.visitValue(invokation.arguments[1])
            val receiver = generator.visitValue(invokation.parent!!)
            return JsFunctionCall(JsField("lastIndexOf", receiver), listOf(arg0, arg1))
        }
    }

    // substring(int beginIndex)
    class MethodSubstring : NativeFunction {
        override val signature: String = "substring(int beginIndex)"
        override fun handle(generator: ShakeJsGenerator, invokation: ShakeInvocation): JsValuedStatement {
            if (invokation.arguments.size != 1) throw IllegalArgumentException("String.substring(int beginIndex) takes exactly 1 argument")
            if (invokation.parent == null) throw IllegalArgumentException("Cannot invoke instance method from static context")
            val arg0 = generator.visitValue(invokation.arguments[0])
            val receiver = generator.visitValue(invokation.parent!!)
            return JsFunctionCall(JsField("substring", receiver), listOf(arg0))
        }
    }

    // substring(int beginIndex, int endIndex)
    class MethodSubstring2 : NativeFunction {
        override val signature: String = "substring(int beginIndex, int endIndex)"
        override fun handle(generator: ShakeJsGenerator, invokation: ShakeInvocation): JsValuedStatement {
            if (invokation.arguments.size != 2) throw IllegalArgumentException("String.substring(int beginIndex, int endIndex) takes exactly 2 arguments")
            if (invokation.parent == null) throw IllegalArgumentException("Cannot invoke instance method from static context")
            val arg0 = generator.visitValue(invokation.arguments[0])
            val arg1 = generator.visitValue(invokation.arguments[1])
            val receiver = generator.visitValue(invokation.parent!!)
            return JsFunctionCall(JsField("substring", receiver), listOf(arg0, arg1))
        }
    }

    // toLowerCase()
    class MethodToLowerCase : NativeFunction {
        override val signature: String = "toLowerCase()"
        override fun handle(generator: ShakeJsGenerator, invokation: ShakeInvocation): JsValuedStatement {
            if (invokation.arguments.size != 0) throw IllegalArgumentException("String.toLowerCase() takes exactly 0 arguments")
            if (invokation.parent == null) throw IllegalArgumentException("Cannot invoke instance method from static context")
            val receiver = generator.visitValue(invokation.parent!!)
            return JsFunctionCall(JsField("toLowerCase", receiver), listOf())
        }
    }

    // toUpperCase()
    class MethodToUpperCase : NativeFunction {
        override val signature: String = "toUpperCase()"
        override fun handle(generator: ShakeJsGenerator, invokation: ShakeInvocation): JsValuedStatement {
            if (invokation.arguments.size != 0) throw IllegalArgumentException("String.toUpperCase() takes exactly 0 arguments")
            if (invokation.parent == null) throw IllegalArgumentException("Cannot invoke instance method from static context")
            val receiver = generator.visitValue(invokation.parent!!)
            return JsFunctionCall(JsField("toUpperCase", receiver), listOf())
        }
    }

    // trim()
    class MethodTrim : NativeFunction {
        override val signature: String = "trim()"
        override fun handle(generator: ShakeJsGenerator, invokation: ShakeInvocation): JsValuedStatement {
            if (invokation.arguments.size != 0) throw IllegalArgumentException("String.trim() takes exactly 0 arguments")
            if (invokation.parent == null) throw IllegalArgumentException("Cannot invoke instance method from static context")
            val receiver = generator.visitValue(invokation.parent!!)
            return JsFunctionCall(JsField("trim", receiver), listOf())
        }
    }

    // replace(String oldSubstring, String newSubstring) TODO regex
    class MethodReplace : NativeFunction {
        override val signature: String = "replace(String oldSubstring, String newSubstring)"
        override fun handle(generator: ShakeJsGenerator, invokation: ShakeInvocation): JsValuedStatement {
            if (invokation.arguments.size != 2) throw IllegalArgumentException("String.replace(String oldSubstring, String newSubstring) takes exactly 2 arguments")
            if (invokation.parent == null) throw IllegalArgumentException("Cannot invoke instance method from static context")
            val arg0 = generator.visitValue(invokation.arguments[0])
            val arg1 = generator.visitValue(invokation.arguments[1])
            val receiver = generator.visitValue(invokation.parent!!)
            return JsFunctionCall(JsField("replace", receiver), listOf(arg0, arg1))
        }
    }

    // concat(String str)
    class MethodConcat : NativeFunction {
        override val signature: String = "concat(String str)"
        override fun handle(generator: ShakeJsGenerator, invokation: ShakeInvocation): JsValuedStatement {
            if (invokation.arguments.size != 1) throw IllegalArgumentException("String.concat(String str) takes exactly 1 argument")
            if (invokation.parent == null) throw IllegalArgumentException("Cannot invoke instance method from static context")
            val arg0 = generator.visitValue(invokation.arguments[0])
            val receiver = generator.visitValue(invokation.parent!!)
            return JsFunctionCall(JsField("concat", receiver), listOf(arg0))
        }
    }

    // split(String regex) TODO regex
    class MethodSplit : NativeFunction {
        override val signature: String = "split(String regex)"
        override fun handle(generator: ShakeJsGenerator, invokation: ShakeInvocation): JsValuedStatement {
            if (invokation.arguments.size != 1) throw IllegalArgumentException("String.split(String regex) takes exactly 1 argument")
            if (invokation.parent == null) throw IllegalArgumentException("Cannot invoke instance method from static context")
            val arg0 = generator.visitValue(invokation.arguments[0])
            val receiver = generator.visitValue(invokation.parent!!)
            return JsFunctionCall(JsField("split", receiver), listOf(arg0))
        }
    }

    // split(String regex, int limit) TODO regex
    class MethodSplit2 : NativeFunction {
        override val signature: String = "split(String regex, int limit)"
        override fun handle(generator: ShakeJsGenerator, invokation: ShakeInvocation): JsValuedStatement {
            if (invokation.arguments.size != 2) throw IllegalArgumentException("String.split(String regex, int limit) takes exactly 2 arguments")
            if (invokation.parent == null) throw IllegalArgumentException("Cannot invoke instance method from static context")
            val arg0 = generator.visitValue(invokation.arguments[0])
            val arg1 = generator.visitValue(invokation.arguments[1])
            val receiver = generator.visitValue(invokation.parent!!)
            return JsFunctionCall(JsField("split", receiver), listOf(arg0, arg1))
        }
    }

    // toString()
    class MethodToString : NativeFunction {
        override val signature: String = "toString()Lshake.lang.String"
        override fun handle(generator: ShakeJsGenerator, invokation: ShakeInvocation): JsValuedStatement {
            if (invokation.arguments.size != 0) throw IllegalArgumentException("String.toString() takes exactly 0 arguments")
            if (invokation.parent == null) throw IllegalArgumentException("Cannot invoke instance method from static context")
            val receiver = generator.visitValue(invokation.parent!!)
            return JsFunctionCall(JsField("toString", receiver), listOf())
        }
    }

    class MethodPlus : NativeFunction {
        override val signature: String = "plus($str)$str"
        override fun handleValue(generator: ShakeJsGenerator, invokation: ShakeInvocation): JsValue {
            if (invokation.arguments.size != 1) throw IllegalArgumentException("String.plus(String str) takes exactly 1 argument, but got ${invokation.arguments.size}")
            if (invokation.parent == null) throw IllegalArgumentException("Cannot invoke instance method from static context")
            val arg0 = generator.visitValue(invokation.arguments[0])
            val receiver = generator.visitValue(invokation.parent!!)
            return JsAdd(receiver, arg0)
        }
    }

    override val functions: List<NativeFunction> = listOf(
        FunctionValueOf0(),
        FunctionValueOf1(),
        FunctionValueOf2(),
        FunctionValueOf3(),
        FunctionValueOf4(),
        FunctionValueOf5(),
        FunctionValueOf6(),
        FunctionValueOf7(),
        FunctionValueOf8(),
        FunctionValueOf9(),
        FunctionValueOf10(),
        FunctionValueOf11(),
        MethodCharAt(),
        MethodIndexOf(),
        MethodIndexOf2(),
        MethodLastIndexOf(),
        MethodLastIndexOf2(),
        MethodSubstring(),
        MethodSubstring2(),
        MethodToLowerCase(),
        MethodToUpperCase(),
        MethodTrim(),
        MethodReplace(),
        MethodSplit(),
        MethodSplit2(),
        MethodConcat(),
        MethodToString(),
        MethodPlus(),
    )

    override val fields: List<NativeField> = listOf(
        FieldLength(),
    )
}
