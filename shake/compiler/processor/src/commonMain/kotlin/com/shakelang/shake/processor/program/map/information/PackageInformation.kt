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

    companion object {
        fun fromJson(json: Map<String, Any>): PackageInformation {
            val name = json["name"] as String
            val subpackages = (json["subpackages"] as List<Map<String, Any>>).map { fromJson(it) }
            val classInformation = (json["classInformation"] as List<Map<String, Any>>).map { ClassInformation.fromJson(it) }
            val methodInformation = (json["methodInformation"] as List<Map<String, Any>>).map { MethodInformation.fromJson(it) }
            val fieldInformation = (json["fieldInformation"] as List<Map<String, Any>>).map { FieldInformation.fromJson(it) }
            return PackageInformation(name, subpackages, classInformation, methodInformation, fieldInformation)
        }
    }
}
