package io.github.shakelang.shake.parser.node.objects

import io.github.shakelang.shake.parser.node.ValuedNode
import io.github.shakelang.shake.parser.node.variables.VariableDeclarationNode
import io.github.shakelang.shake.parser.node.functions.FunctionDeclarationNode
import io.github.shakelang.shake.parser.node.AccessDescriber
import io.github.shakelang.parseutils.characters.position.PositionMap
import kotlin.jvm.JvmOverloads

@Suppress("unused")
class ClassDeclarationNode @JvmOverloads constructor(
    map: PositionMap,
    val name: String,
    val fields: Array<VariableDeclarationNode>,
    val methods: Array<FunctionDeclarationNode>,
    val classes: Array<ClassDeclarationNode>,
    val constructors: Array<ConstructorDeclarationNode>,
    val access: AccessDescriber = AccessDescriber.PACKAGE,
    val isInClass: Boolean = false,
    val isStatic: Boolean = false,
    val isFinal: Boolean = false
) : ValuedNode(map) {

    constructor(
        map: PositionMap,
        name: String,
        fields: List<VariableDeclarationNode>,
        methods: List<FunctionDeclarationNode>,
        classes: List<ClassDeclarationNode>,
        constructors: List<ConstructorDeclarationNode>,
        access: AccessDescriber,
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
        fields: List<VariableDeclarationNode>,
        methods: List<FunctionDeclarationNode>,
        classes: List<ClassDeclarationNode>,
        constructors: List<ConstructorDeclarationNode>
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