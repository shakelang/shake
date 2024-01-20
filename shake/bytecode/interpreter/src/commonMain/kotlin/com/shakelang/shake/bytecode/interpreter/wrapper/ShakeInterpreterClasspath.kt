package com.shakelang.shake.bytecode.interpreter.wrapper

import com.shakelang.shake.bytecode.interpreter.ShakeInterpreter
import com.shakelang.shake.bytecode.interpreter.format.StorageFormat
import com.shakelang.shake.bytecode.interpreter.format.descriptor.PathDescriptor

interface ShakeInterpreterClasspath {
    val interpreter: ShakeInterpreter
    val packages: List<ShakeInterpreterPackage>

    fun getPackage(descriptor: PathDescriptor): ShakeInterpreterPackage?
    fun getPackage(descriptor: String): ShakeInterpreterPackage? = getPackage(PathDescriptor(descriptor.split("/", "").toTypedArray(), emptyArray(), ""))

    fun getClass(descriptor: PathDescriptor): ShakeInterpreterClass? = getPackage(descriptor)?.getClass(descriptor)
    fun getClass(descriptor: String): ShakeInterpreterClass? = getClass(PathDescriptor.parse(descriptor))

    fun getMethod(descriptor: PathDescriptor): ShakeInterpreterMethod? = getPackage(descriptor)?.getMethod(descriptor)
    fun getMethod(descriptor: String): ShakeInterpreterMethod? = getMethod(PathDescriptor.parse(descriptor))

    fun getField(descriptor: PathDescriptor): ShakeInterpreterField? = getPackage(descriptor)?.getField(descriptor)
    fun getField(descriptor: String): ShakeInterpreterField? = getField(PathDescriptor.parse(descriptor))

    fun load(storage: StorageFormat)
    fun load(vararg storages: StorageFormat) {
        for (it in storages) load(it)
    }

    fun load(storages: List<StorageFormat>) {
        for (it in storages) load(it)
    }

    companion object {
        fun create(interpreter: ShakeInterpreter): ShakeInterpreterClasspath {
            return object : ShakeInterpreterClasspath {
                override val interpreter = interpreter

                override val packages: MutableList<ShakeInterpreterPackage> = mutableListOf()

                override fun getPackage(descriptor: PathDescriptor): ShakeInterpreterPackage? {
                    val search = descriptor.packagePath
                    return packages.find {
                        it.name == search
                    }
                }

                override fun load(storage: StorageFormat) {
                    val pkg = getPackage(PathDescriptor.parse(storage.packageName))
                    if (pkg != null) {
                        pkg.load(storage)
                    } else {
                        packages.add(ShakeInterpreterPackage.of(listOf(storage), this))
                    }
                }
            }
        }
        fun create(interpreter: ShakeInterpreter, storages: List<StorageFormat>): ShakeInterpreterClasspath {
            val classpath = create(interpreter)
            storages.forEach {
                classpath.load(it)
            }
            return classpath
        }

        fun create(interpreter: ShakeInterpreter, vararg storages: StorageFormat): ShakeInterpreterClasspath {
            val classpath = create(interpreter)
            storages.forEach {
                classpath.load(it)
            }
            return classpath
        }
    }
}
