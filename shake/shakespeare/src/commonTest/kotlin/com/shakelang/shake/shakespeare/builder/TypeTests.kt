import com.shakelang.shake.shakespeare.spec.*
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class TypeTests : FreeSpec({

    "Type.Companion.of should return PrimitiveType.BYTE for 'byte'" {
        Type.of("byte") shouldBe PrimitiveType.BYTE
    }

    "Type.Companion.of should return PrimitiveType.SHORT for 'short'" {
        Type.of("short") shouldBe PrimitiveType.SHORT
    }

    "Type.Companion.of should return PrimitiveType.INT for 'int'" {
        Type.of("int") shouldBe PrimitiveType.INT
    }

    "Type.Companion.of should return PrimitiveType.LONG for 'long'" {
        Type.of("long") shouldBe PrimitiveType.LONG
    }

    "Type.Companion.of should return PrimitiveType.UNSIGNED_BYTE for 'unsigned byte'" {
        Type.of("unsigned byte") shouldBe PrimitiveType.UNSIGNED_BYTE
    }

    "Type.Companion.of should return PrimitiveType.UNSIGNED_SHORT for 'unsigned short'" {
        Type.of("unsigned short") shouldBe PrimitiveType.UNSIGNED_SHORT
    }

    "Type.Companion.of should return PrimitiveType.UNSIGNED_INT for 'unsigned int'" {
        Type.of("unsigned int") shouldBe PrimitiveType.UNSIGNED_INT
    }

    "Type.Companion.of should return PrimitiveType.UNSIGNED_LONG for 'unsigned long'" {
        Type.of("unsigned long") shouldBe PrimitiveType.UNSIGNED_LONG
    }

    "Type.Companion.of should return PrimitiveType.FLOAT for 'float'" {
        Type.of("float") shouldBe PrimitiveType.FLOAT
    }

    "Type.Companion.of should return PrimitiveType.DOUBLE for 'double'" {
        Type.of("double") shouldBe PrimitiveType.DOUBLE
    }

    "Type.Companion.of should return PrimitiveType.CHAR for 'char'" {
        Type.of("char") shouldBe PrimitiveType.CHAR
    }

    "Type.Companion.of should return PrimitiveType.BOOLEAN for 'boolean'" {
        Type.of("boolean") shouldBe PrimitiveType.BOOLEAN
    }

    "Type.Companion.of should return SimpleType for non-primitive 'String'" {
        Type.of("String") shouldBe SimpleType("String")
    }

    "Type.Companion.of should return SimpleType for non-primitive 'CustomType'" {
        Type.of("CustomType") shouldBe SimpleType("CustomType")
    }

    "SimpleType.generate should return type name for 'TestType'" {
        val simpleType = SimpleType("TestType")
        simpleType.generate(GenerationContext()) shouldBe "TestType"
    }

    "PrimitiveType.BYTE.generate should return 'byte'" {
        PrimitiveType.BYTE.generate(GenerationContext()) shouldBe "byte"
    }

    "PrimitiveType.SHORT.generate should return 'short'" {
        PrimitiveType.SHORT.generate(GenerationContext()) shouldBe "short"
    }

    "PrimitiveType.INT.generate should return 'int'" {
        PrimitiveType.INT.generate(GenerationContext()) shouldBe "int"
    }

    "PrimitiveType.LONG.generate should return 'long'" {
        PrimitiveType.LONG.generate(GenerationContext()) shouldBe "long"
    }

    "PrimitiveType.UNSIGNED_BYTE.generate should return 'unsigned byte'" {
        PrimitiveType.UNSIGNED_BYTE.generate(GenerationContext()) shouldBe "unsigned byte"
    }

    "PrimitiveType.UNSIGNED_SHORT.generate should return 'unsigned short'" {
        PrimitiveType.UNSIGNED_SHORT.generate(GenerationContext()) shouldBe "unsigned short"
    }

    "PrimitiveType.UNSIGNED_INT.generate should return 'unsigned int'" {
        PrimitiveType.UNSIGNED_INT.generate(GenerationContext()) shouldBe "unsigned int"
    }

    "PrimitiveType.UNSIGNED_LONG.generate should return 'unsigned long'" {
        PrimitiveType.UNSIGNED_LONG.generate(GenerationContext()) shouldBe "unsigned long"
    }

    "PrimitiveType.FLOAT.generate should return 'float'" {
        PrimitiveType.FLOAT.generate(GenerationContext()) shouldBe "float"
    }

    "PrimitiveType.DOUBLE.generate should return 'double'" {
        PrimitiveType.DOUBLE.generate(GenerationContext()) shouldBe "double"
    }

    "PrimitiveType.CHAR.generate should return 'char'" {
        PrimitiveType.CHAR.generate(GenerationContext()) shouldBe "char"
    }

    "PrimitiveType.BOOLEAN.generate should return 'boolean'" {
        PrimitiveType.BOOLEAN.generate(GenerationContext()) shouldBe "boolean"
    }

    "ClassType.generate should return class name for 'MyClass'" {
        val classType = ClassType("MyClass")
        classType.generate(GenerationContext()) shouldBe "MyClass"
    }
})
