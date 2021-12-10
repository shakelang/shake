package io.github.shakelang.jvmlib.attributes

import io.github.shakelang.jvmlib.infos.constants.ConstantPool
import io.github.shakelang.parseutils.bytes.toBytes
import io.github.shakelang.parseutils.streaming.DataInputStream

open class AttributeMap(open val map: Map<String, Attribute>) : Map<String, Attribute> {

    override fun get(key: String): Attribute? = map[key]
    override val entries: Set<Map.Entry<String, Attribute>>
        get() = map.entries

    override val keys: Set<String>
        get() = map.keys

    override val size: Int
        get() = map.size

    override val values: Collection<Attribute>
        get() = map.values

    override fun containsKey(key: String): Boolean = map.containsKey(key)
    override fun containsValue(value: Attribute): Boolean = map.containsValue(value)
    override fun isEmpty(): Boolean = map.isEmpty()

    fun toBytes(): Array<Byte> {
        val childBytes = mutableListOf<Byte>()
        childBytes.addAll(this.size.toUShort().toBytes().toList())
        for (i in map.values.indices) {
            val attribute = map.values.elementAt(i)
            childBytes.addAll(attribute.toBytes().toList())
        }
        return childBytes.toTypedArray()
    }

    companion object {

        fun fromStream(pool: ConstantPool, stream: DataInputStream): AttributeMap {
            val size = stream.readUnsignedShort()
            val map = mutableMapOf<String, Attribute>()
            for (i in 0 until size.toInt()) {
                val attribute = Attribute.fromStream(pool, stream)
                map[attribute.name] = attribute
            }
            return AttributeMap(map)
        }

    }

}

class MutableAttributeMap(map: MutableMap<String, Attribute>) : AttributeMap(map), MutableMap<String, Attribute> {

    override val map: MutableMap<String, Attribute>
        get() = super.map as MutableMap<String, Attribute>

    override val entries: MutableSet<MutableMap.MutableEntry<String, Attribute>>
        get() = map.entries

    override val keys: MutableSet<String>
        get() = map.keys

    override val values: MutableCollection<Attribute>
        get() = map.values

    override fun clear() = map.clear()

    override fun put(key: String, value: Attribute): Attribute?
        = map.put(key, value)

    override fun putAll(from: Map<out String, Attribute>)
        = map.putAll(from)

    override fun remove(key: String): Attribute?
        = map.remove(key)

}