package io.github.shakelang.jvmlib.constants

class ConstantPool(val constants: List<ConstantInfo>) : List<ConstantInfo> {

    constructor(constants: Array<ConstantInfo>) : this(constants.toList())

    override val size: Int
        get() = constants.size

    override fun contains(element: ConstantInfo): Boolean {
        return constants.contains(element)
    }

    override fun containsAll(elements: Collection<ConstantInfo>): Boolean {
        return constants.containsAll(elements)
    }

    override fun get(index: Int): ConstantInfo {
        return constants[index-1]
    }

    override fun indexOf(element: ConstantInfo): Int {
        return constants.indexOf(element)
    }

    override fun isEmpty(): Boolean {
        return constants.isEmpty()
    }

    override fun iterator(): Iterator<ConstantInfo> {
        return constants.iterator()
    }

    override fun lastIndexOf(element: ConstantInfo): Int {
        return constants.lastIndexOf(element)
    }

    override fun listIterator(): ListIterator<ConstantInfo> {
        return constants.listIterator()
    }

    override fun listIterator(index: Int): ListIterator<ConstantInfo> {
        return constants.listIterator(index)
    }

    override fun subList(fromIndex: Int, toIndex: Int): List<ConstantInfo> {
        return constants.subList(fromIndex, toIndex)
    }

    fun toJson(): List<Map<String, Any>> {
        return this.constants.map { it.toJson() }
    }

    override fun toString(): String {
        return constants.toString()
    }
}