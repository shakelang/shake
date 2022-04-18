package io.github.shakelang.shake.parser.node.objects

import io.github.shakelang.shake.parser.node.variables.ShakeVariableDeclarationNode
import io.github.shakelang.shake.parser.node.functions.ShakeFunctionDeclarationNode
import io.github.shakelang.shake.parser.node.ShakeAccessDescriber
import io.github.shakelang.parseutils.characters.position.PositionMap
import io.github.shakelang.shake.parser.node.ShakeFileChildNode
import io.github.shakelang.shake.parser.node.ShakeFileChildNodeImpl
import io.github.shakelang.shake.parser.node.ShakeValuedNodeImpl
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
    val isInClass: Boolean = false,
    val isStatic: Boolean = false,
    val isFinal: Boolean = false
) : ShakeFileChildNodeImpl(map) {

    constructor(
        map: PositionMap,
        name: String,
        fields: List<ShakeVariableDeclarationNode>,
        methods: List<ShakeFunctionDeclarationNode>,
        classes: List<ShakeClassDeclarationNode>,
        constructors: List<ShakeConstructorDeclarationNode>,
        access: ShakeAccessDescriber,
        isInClass: Boolean,
        isStatic: Boolean,
        isFinal: Boolean
    ) : this(
        map,
        name,
        fields.toTypedArray(),
        methods.toTypedArray(),
        classes.toTypedArray(),
        constructors.toTypedArray(),
        access,
        isInClass,
        isStatic,
        isFinal
    )

    constructor(
        map: PositionMap,
        name: String,
        fields: List<ShakeVariableDeclarationNode>,
        methods: List<ShakeFunctionDeclarationNode>,
        classes: List<ShakeClassDeclarationNode>,
        constructors: List<ShakeConstructorDeclarationNode>
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
            "is_in_class" to isInClass,
            "is_static" to isStatic,
            "is_final" to isFinal,
            "access" to access.toString(),
            "fields" to fields.map { it.json },
            "methods" to methods.map { it.json },
            "classes" to classes.map { it.json },
            "constructors" to constructors.map { it.json }
        )
}