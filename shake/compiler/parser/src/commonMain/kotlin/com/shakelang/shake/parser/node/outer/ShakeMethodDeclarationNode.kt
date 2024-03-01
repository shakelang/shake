package com.shakelang.shake.parser.node.outer

import com.shakelang.shake.lexer.token.ShakeToken
import com.shakelang.shake.parser.node.ShakeFileChildNodeImpl
import com.shakelang.shake.parser.node.misc.ShakeAccessDescriber
import com.shakelang.shake.parser.node.misc.ShakeVariableType
import com.shakelang.shake.parser.node.statements.ShakeBlockNode
import com.shakelang.util.parseutils.characters.position.PositionMap

/**
 * A node representing a method declaration
 */
@Suppress("MemberVisibilityCanBePrivate")
class ShakeMethodDeclarationNode(
    map: PositionMap,

    /**
     * The type this method expands (So this method can be
     * called on a type)
     */
    val expandedType: ShakeVariableType?,

    /**
     * The name (token) of the method
     */
    val nameToken: ShakeToken,

    /**
     * The body of the method
     */
    val body: ShakeBlockNode?,

    /**
     * The arguments of the method
     */
    val args: Array<ShakeParameterNode>,

    /**
     * The type of the method
     */
    val type: ShakeVariableType,

    /**
     * The access describer of the method
     */
    val access: ShakeAccessDescriber,

    /**
     * The static modifier (token) of the method
     */
    val staticToken: ShakeToken?,

    /**
     * The final modifier (token) of the method
     */
    val finalToken: ShakeToken?,

    /**
     * The abstract modifier (token) of the method
     */
    val abstractToken: ShakeToken?,

    /**
     * The override modifier (token) of the method
     */
    val overrideToken: ShakeToken?,

    /**
     * The synchronized modifier (token) of the method
     */
    val synchronizedToken: ShakeToken?,

    /**
     * The native modifier (token) of the method
     */
    val nativeToken: ShakeToken?,

    /**
     * The operator modifier (token) of the method
     */
    val operatorToken: ShakeToken?,

    /**
     * The inline modifier (token) of the method
     */
    val inlineToken: ShakeToken?,

    /**
     * The fun token of the method
     */
    val funToken: ShakeToken,

    /**
     * The lparen token of the method
     */
    val lparenToken: ShakeToken,

    /**
     * The rparen token of the method
     */
    val rparenToken: ShakeToken,

    /**
     * The colon token of the method
     */
    val colonToken: ShakeToken?,

    /**
     * The comma tokens of the method
     */
    val commaTokens: Array<ShakeToken>,

    /**
     * The expanding dot token of the method
     */
    val expandingDotToken: ShakeToken?,
) : ShakeFileChildNodeImpl(map) {

    /**
     * The name of the method
     */
    val name: String get() = nameToken.value ?: throw Exception("Name of method token is null")

    /**
     * Whether the method has the `static` attribute
     */
    val isStatic: Boolean get() = staticToken != null

    /**
     * Whether the method has the `final` attribute
     */
    val isFinal: Boolean get() = finalToken != null

    /**
     * Whether the method has the `abstract` attribute
     */
    val isAbstract: Boolean get() = abstractToken != null

    /**
     * Whether the method has the `override` attribute
     */
    val isOverride: Boolean get() = overrideToken != null

    /**
     * Whether the method has the `synchronized` attribute
     */
    val isSynchronized: Boolean get() = synchronizedToken != null

    /**
     * Whether the method has the `native` attribute
     */
    val isNative: Boolean get() = nativeToken != null

    /**
     * Whether the method has the `operator` attribute
     */
    val isOperator: Boolean get() = operatorToken != null

    /**
     * Whether the method has the `inline` attribute
     */
    val isInline: Boolean get() = inlineToken != null

    /**
     * Whether the method is public
     */
    val isPublic: Boolean get() = access.type == ShakeAccessDescriber.ShakeAccessDescriberType.PUBLIC

    /**
     * Whether the method is protected
     */
    val isProtected: Boolean get() = access.type == ShakeAccessDescriber.ShakeAccessDescriberType.PROTECTED

    /**
     * Whether the method is private
     */
    val isPrivate: Boolean get() = access.type == ShakeAccessDescriber.ShakeAccessDescriberType.PRIVATE

    /**
     * Whether the method is package
     */
    val isPackage: Boolean get() = access.type == ShakeAccessDescriber.ShakeAccessDescriberType.PACKAGE

    override fun toJson(): Map<String, *> =
        mapOf(
            "name" to nodeName,
            "method_name" to name,
            "args" to args.map { it.json },
            "body" to body?.json,
            "type" to type.toString(),
            "access" to access.toString(),
            "is_static" to isStatic,
            "is_final" to isFinal,
        )

    override fun equalsIgnorePosition(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeMethodDeclarationNode) return false

        if (expandedType != other.expandedType) return false
        if (name != other.name) return false
        if (body != other.body) return false
        if (!args.contentEquals(other.args)) return false
        if (type != other.type) return false
        if (access != other.access) return false
        if (isStatic != other.isStatic) return false
        if (isFinal != other.isFinal) return false
        if (isAbstract != other.isAbstract) return false
        if (isOverride != other.isOverride) return false
        if (isSynchronized != other.isSynchronized) return false
        if (isNative != other.isNative) return false
        if (isOperator != other.isOperator) return false
        if (isInline != other.isInline) return false

        return true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeMethodDeclarationNode) return false

        if (expandedType != other.expandedType) return false
        if (name != other.name) return false
        if (body != other.body) return false
        if (!args.contentEquals(other.args)) return false
        if (type != other.type) return false
        if (access != other.access) return false
        if (isStatic != other.isStatic) return false
        if (isFinal != other.isFinal) return false
        if (isAbstract != other.isAbstract) return false
        if (isOverride != other.isOverride) return false
        if (isSynchronized != other.isSynchronized) return false
        if (isNative != other.isNative) return false
        if (isOperator != other.isOperator) return false
        if (isInline != other.isInline) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (expandedType?.hashCode() ?: 0)
        result = 31 * result + name.hashCode()
        result = 31 * result + (body?.hashCode() ?: 0)
        result = 31 * result + args.contentHashCode()
        result = 31 * result + type.hashCode()
        result = 31 * result + (access?.hashCode() ?: 0)
        result = 31 * result + isStatic.hashCode()
        result = 31 * result + isFinal.hashCode()
        result = 31 * result + isAbstract.hashCode()
        result = 31 * result + isOverride.hashCode()
        result = 31 * result + isSynchronized.hashCode()
        result = 31 * result + isNative.hashCode()
        result = 31 * result + isOperator.hashCode()
        result = 31 * result + isInline.hashCode()
        return result
    }
}
