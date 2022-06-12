package io.github.shakelang.shake.parser.node.objects

import io.github.shakelang.parseutils.characters.position.PositionMap
import io.github.shakelang.shake.parser.node.ShakeAccessDescriber
import io.github.shakelang.shake.parser.node.ShakeFileChildNodeImpl
import io.github.shakelang.shake.parser.node.functions.ShakeFunctionDeclarationNode
import io.github.shakelang.shake.parser.node.variables.ShakeVariableDeclarationNode
import kotlin.jvm.JvmOverloads

@Suppress("unused")
class ShakeClassDeclarationNode @JvmOverloads constructor(
    map: PositionMap,
    val name: String,
    val fields: Array<ShakeVariableDeclarationNode>,
    val methods: Array<ShakeFunctionDeclarationNode>,
    val classes: Array<ShakeClassDeclarationNode>,
    val constructors: Array<ShakeConstructorDeclarationNode>,
    val access: ShakeAccessDescriber = ShakeAccessDescriber.PACKAGE,
    val type: ShakeClassType = ShakeClassType.CLASS,
    val isStatic: Boolean = false,
    val isFinal: Boolean = false,
    val isAbstract: Boolean = false,
) : ShakeFileChildNodeImpl(map) {

    constructor(
        map: PositionMap,
        name: String,
        fields: List<ShakeVariableDeclarationNode>,
        methods: List<ShakeFunctionDeclarationNode>,
        classes: List<ShakeClassDeclarationNode>,
        constructors: List<ShakeConstructorDeclarationNode>,
        access: ShakeAccessDescriber,
        type: ShakeClassType,
        isStatic: Boolean,
        isFinal: Boolean,
        isAbstract: Boolean,
    ) : this(
        map,
        name,
        fields.toTypedArray(),
        methods.toTypedArray(),
        classes.toTypedArray(),
        constructors.toTypedArray(),
        access,
        type,
        isStatic,
        isFinal,
        isAbstract,
    )

    constructor(
        map: PositionMap,
        name: String,
        fields: List<ShakeVariableDeclarationNode>,
        methods: List<ShakeFunctionDeclarationNode>,
        classes: List<ShakeClassDeclarationNode>,
        constructors: List<ShakeConstructorDeclarationNode>,
    ) : this(
        map,
        name,
        fields.toTypedArray(),
        methods.toTypedArray(),
        classes.toTypedArray(),
        constructors.toTypedArray()
    )

    override fun toJson(): Map<String, *> =
        mapOf(
            "name" to "ClassDeclarationNode",
            "is_static" to isStatic,
            "is_final" to isFinal,
            "access" to access.toString(),
            "fields" to fields.map { it.json },
            "methods" to methods.map { it.json },
            "classes" to classes.map { it.json },
            "constructors" to constructors.map { it.json }
        )
}

enum class ShakeClassType {
    CLASS,
    INTERFACE,
    ENUM,
    OBJECT
}