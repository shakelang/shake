package com.shakelang.shake.parser.node.objects

import com.shakelang.shake.lexer.token.ShakeToken
import com.shakelang.shake.parser.node.ShakeAccessDescriber
import com.shakelang.shake.parser.node.ShakeFileChildNodeImpl
import com.shakelang.shake.parser.node.ShakeNamespaceNode
import com.shakelang.shake.parser.node.functions.ShakeFunctionDeclarationNode
import com.shakelang.shake.parser.node.variables.ShakeFieldDeclarationNode
import com.shakelang.shake.parser.node.variables.ShakeLocalDeclarationNode
import com.shakelang.util.parseutils.characters.position.PositionMap

@Suppress("unused", "MemberVisibilityCanBePrivate")
class ShakeClassDeclarationNode(
    map: PositionMap,
    val classToken: ShakeToken,
    val nameToken: ShakeToken,
    val superClasses: Array<ShakeNamespaceNode>,
    val fields: Array<ShakeFieldDeclarationNode>,
    val methods: Array<ShakeFunctionDeclarationNode>,
    val classes: Array<ShakeClassDeclarationNode>,
    val constructors: Array<ShakeConstructorDeclarationNode>,
    val access: ShakeAccessDescriber,
    val staticToken: ShakeToken? = null,
    val finalToken: ShakeToken? = null,
    val abstractToken: ShakeToken? = null,
    val nativeToken: ShakeToken? = null,
) : ShakeFileChildNodeImpl(map) {
    val name: String
        get() = nameToken.value ?: throw IllegalStateException("Name token has no value")

    val isStatic: Boolean
        get() = staticToken != null

    val isFinal: Boolean
        get() = finalToken != null

    val isAbstract: Boolean
        get() = abstractToken != null

    val isNative: Boolean
        get() = nativeToken != null

    override fun toJson(): Map<String, *> =
        mapOf(
            "name" to nodeName,
            "is_static" to isStatic,
            "is_final" to isFinal,
            "access" to access.toString(),
            "fields" to fields.map { it.json },
            "methods" to methods.map { it.json },
            "classes" to classes.map { it.json },
            "constructors" to constructors.map { it.json },
        )

    override fun equalsIgnorePosition(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeClassDeclarationNode) return false
        if (name != other.name) return false
        if (!superClasses.contentEquals(other.superClasses)) return false
        if (!fields.contentEquals(other.fields)) return false
        if (!methods.contentEquals(other.methods)) return false
        if (!classes.contentEquals(other.classes)) return false
        if (!constructors.contentEquals(other.constructors)) return false
        if (access != other.access) return false
        if (isStatic != other.isStatic) return false
        if (isFinal != other.isFinal) return false
        if (isAbstract != other.isAbstract) return false
        if (isNative != other.isNative) return false
        return true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeClassDeclarationNode) return false
        if (name != other.name) return false
        if (!superClasses.contentEquals(other.superClasses)) return false
        if (!fields.contentEquals(other.fields)) return false
        if (!methods.contentEquals(other.methods)) return false
        if (!classes.contentEquals(other.classes)) return false
        if (!constructors.contentEquals(other.constructors)) return false
        if (access != other.access) return false
        if (isStatic != other.isStatic) return false
        if (isFinal != other.isFinal) return false
        if (isAbstract != other.isAbstract) return false
        if (isNative != other.isNative) return false
        if (map != other.map) return false
        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + superClasses.contentHashCode()
        result = 31 * result + fields.contentHashCode()
        result = 31 * result + methods.contentHashCode()
        result = 31 * result + classes.contentHashCode()
        result = 31 * result + constructors.contentHashCode()
        result = 31 * result + access.hashCode()
        result = 31 * result + isStatic.hashCode()
        result = 31 * result + isFinal.hashCode()
        result = 31 * result + isAbstract.hashCode()
        result = 31 * result + isNative.hashCode()
        result = 31 * result + map.hashCode()
        return result
    }
}

@Suppress("unused", "MemberVisibilityCanBePrivate")
class ShakeInterfaceDeclarationNode(
    map: PositionMap,
    val interfaceToken: ShakeToken,
    val nameToken: ShakeToken,
    val superClasses: Array<ShakeNamespaceNode>,
    val fields: Array<ShakeLocalDeclarationNode>,
    val methods: Array<ShakeFunctionDeclarationNode>,
    val classes: Array<ShakeClassDeclarationNode>,
    val access: ShakeAccessDescriber,
    val staticToken: ShakeToken? = null,
    val finalToken: ShakeToken? = null,
    val abstractToken: ShakeToken? = null,
    val nativeToken: ShakeToken? = null,
) : ShakeFileChildNodeImpl(map) {
    val name: String
        get() = nameToken.value ?: throw IllegalStateException("Name token has no value")

    val isStatic: Boolean
        get() = staticToken != null

    val isFinal: Boolean
        get() = finalToken != null

    val isAbstract: Boolean
        get() = abstractToken != null

    val isNative: Boolean
        get() = nativeToken != null

    override fun toJson(): Map<String, *> =
        mapOf(
            "name" to nodeName,
            "is_static" to isStatic,
            "is_final" to isFinal,
            "access" to access.toString(),
            "fields" to fields.map { it.json },
            "methods" to methods.map { it.json },
            "classes" to classes.map { it.json },
        )

    override fun equalsIgnorePosition(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeClassDeclarationNode) return false
        if (name != other.name) return false
        if (!superClasses.contentEquals(other.superClasses)) return false
        if (!fields.contentEquals(other.fields)) return false
        if (!methods.contentEquals(other.methods)) return false
        if (!classes.contentEquals(other.classes)) return false
        if (access != other.access) return false
        if (isStatic != other.isStatic) return false
        if (isFinal != other.isFinal) return false
        if (isAbstract != other.isAbstract) return false
        if (isNative != other.isNative) return false
        return true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeClassDeclarationNode) return false
        if (name != other.name) return false
        if (!superClasses.contentEquals(other.superClasses)) return false
        if (!fields.contentEquals(other.fields)) return false
        if (!methods.contentEquals(other.methods)) return false
        if (!classes.contentEquals(other.classes)) return false
        if (access != other.access) return false
        if (isStatic != other.isStatic) return false
        if (isFinal != other.isFinal) return false
        if (isAbstract != other.isAbstract) return false
        if (isNative != other.isNative) return false
        if (map != other.map) return false
        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + superClasses.contentHashCode()
        result = 31 * result + fields.contentHashCode()
        result = 31 * result + methods.contentHashCode()
        result = 31 * result + classes.contentHashCode()
        result = 31 * result + access.hashCode()
        result = 31 * result + isStatic.hashCode()
        result = 31 * result + isFinal.hashCode()
        result = 31 * result + isAbstract.hashCode()
        result = 31 * result + isNative.hashCode()
        result = 31 * result + map.hashCode()
        return result
    }
}
