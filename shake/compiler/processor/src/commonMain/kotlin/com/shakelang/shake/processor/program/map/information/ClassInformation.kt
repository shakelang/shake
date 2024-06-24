package com.shakelang.shake.processor.program.map.information

class ClassInformation(
    val name: String,
    val flags: Short,
    val superClass: String,
    val interfaces: List<String>,
    val genericInformation: List<GenericInformation>,
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
        "genericInformation" to genericInformation.map { it.json() },
        "methodInformation" to methodInformation.map { it.json() },
        "fieldInformation" to fieldInformation.map { it.json() },
        "constructorInformation" to constructorInformation.map { it.json() },
        "classInformation" to classInformation.map { it.json() },
    )

    companion object {
        fun fromJson(json: Map<String, Any>): ClassInformation {
            val name = json["name"] as String
            val flags = json["flags"] as Short
            val superClass = json["superClass"] as String
            val interfaces = json["interfaces"] as List<String>
            val genericInformation = (json["genericInformation"] as List<Map<String, Any>>).map { GenericInformation.fromJson(it) }
            val methodInformation = (json["methodInformation"] as List<Map<String, Any>>).map { MethodInformation.fromJson(it) }
            val fieldInformation = (json["fieldInformation"] as List<Map<String, Any>>).map { FieldInformation.fromJson(it) }
            val constructorInformation = (json["constructorInformation"] as List<Map<String, Any>>).map { ConstructorInformation.fromJson(it) }
            val classInformation = (json["classInformation"] as List<Map<String, Any>>).map { ClassInformation.fromJson(it) }
            return ClassInformation(
                name,
                flags,
                superClass,
                interfaces,
                genericInformation,
                classInformation,
                constructorInformation,
                methodInformation,
                fieldInformation,
            )
        }
    }
}
