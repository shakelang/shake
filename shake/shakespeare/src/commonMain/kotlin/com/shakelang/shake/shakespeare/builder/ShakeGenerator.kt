package com.shakelang.shake.shakespeare.builder

import com.shakelang.shake.shakespeare.spec.Identifier
import com.shakelang.shake.shakespeare.spec.Type
import com.shakelang.shake.shakespeare.spec.code.ValueSpec
import com.shakelang.shake.shakespeare.spec.code.VariableDeclarationSpec
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

open class ShakeGeneratorError(message: String, cause: Throwable? = null) : RuntimeException(message, cause)

class ShakeGeneratorUnsetPropertyError(property: KProperty<*>, clazz: KClass<*>, cause: Throwable? = null) :
    ShakeGeneratorError("Property ${property.name} of class ${clazz.simpleName} is not set", cause)

@Suppress("ktlint:standard:property-naming")
class VariableDeclarationBuilder
internal constructor(init: VariableDeclarationBuilder.() -> Unit) {

    private var _identifier: Identifier? = null
    private var _type: Type? = null
    private var _value: ValueSpec? = null

    var identifier: Identifier
        get() = _identifier ?: throw ShakeGeneratorUnsetPropertyError(VariableDeclarationBuilder::identifier, this::class)
        set(value) {
            _identifier = value
        }

    var type: Type
        get() = _type ?: throw ShakeGeneratorUnsetPropertyError(VariableDeclarationBuilder::type, this::class)
        set(value) {
            _type = value
        }

    var value: ValueSpec
        get() = _value ?: throw ShakeGeneratorUnsetPropertyError(VariableDeclarationBuilder::value, this::class)
        set(value) {
            _value = value
        }

    val hasIdentifier: Boolean get() = _identifier != null
    val hasType: Boolean get() = _type != null
    val hasValue: Boolean get() = _value != null

    fun build(): VariableDeclarationSpec {
        return VariableDeclarationSpec.builder()
            .name(identifier)
            .type(type)
            .value(value)
            .build()
    }

    init {
        init()
    }
}
