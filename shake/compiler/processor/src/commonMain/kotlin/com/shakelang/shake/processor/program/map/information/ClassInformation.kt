package com.shakelang.shake.processor.program.map.information

class ClassInformation(
    val name: String,
    val flags: Short,
    val superClass: String,
    val interfaces: List<String>,
    val classInformation: List<ClassInformation>,
    val constructorInformation: List<ConstructorInformation>,
    val methodInformation: List<MethodInformation>,
    val fieldInformation: List<FieldInformation>,
) {
    fun json(): Map<String, Any> = mapOf(
        "type" to "class",
        "name" to name,
        "flags" to flags,
        "superClass" to superClass,
        "interfaces" to interfaces,
        "methodInformation" to methodInformation.map { it.json() },
        "fieldInformation" to fieldInformation.map { it.json() },
        "constructorInformation" to constructorInformation.map { it.json() },
        "classInformation" to classInformation.map { it.json() },
    )
}
