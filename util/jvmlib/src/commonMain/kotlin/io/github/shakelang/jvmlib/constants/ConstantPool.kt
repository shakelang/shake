package io.github.shakelang.jvmlib.constants

class ConstantPool(val constants: List<CONSTANT>) : List<CONSTANT> {

    constructor(constants: Array<CONSTANT>) : this(constants.toList())

    override val size: Int
        get() = constants.size

    override fun contains(element: CONSTANT): Boolean {
        return constants.contains(element)
    }

    override fun containsAll(elements: Collection<CONSTANT>): Boolean {
        return constants.containsAll(elements)
    }

    override fun get(index: Int): CONSTANT {
        return constants[index-1]
    }

    override fun indexOf(element: CONSTANT): Int {
        return constants.indexOf(element)
    }

    override fun isEmpty(): Boolean {
        return constants.isEmpty()
    }

    override fun iterator(): Iterator<CONSTANT> {
        return constants.iterator()
    }

    override fun lastIndexOf(element: CONSTANT): Int {
        return constants.lastIndexOf(element)
    }

    override fun listIterator(): ListIterator<CONSTANT> {
        return constants.listIterator()
    }

    override fun listIterator(index: Int): ListIterator<CONSTANT> {
        return constants.listIterator(index)
    }

    override fun subList(fromIndex: Int, toIndex: Int): List<CONSTANT> {
        return constants.subList(fromIndex, toIndex)
    }

    override fun toString(): String {
        return constants.toString()
    }
}