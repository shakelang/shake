package com.shakelang.shake.processor.program.map.information

class PackageInformation(
    val name: String,
    val subpackages: List<PackageInformation>,
    val classInformation: List<ClassInformation>,
    val methodInformation: List<MethodInformation>,
    val fieldInformation: List<FieldInformation>,
) {
    fun json(): Map<String, Any> = mapOf(
        "type" to "package",
        "name" to name,
        "subpackages" to subpackages.map { it.json() },
        "classInformation" to classInformation.map { it.json() },
        "methodInformation" to methodInformation.map { it.json() },
        "fieldInformation" to fieldInformation.map { it.json() },
    )
}
