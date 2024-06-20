package com.shakelang.shake.processor.program.map.information

class ProjectInformation(
    val packages: List<PackageInformation>,
) {
    fun json(): Map<String, Any> = mapOf(
        "type" to "project",
        "packages" to packages.map { it.json() },
    )

    companion object {
        fun fromJson(json: Map<String, Any>): ProjectInformation {
            val packages = (json["packages"] as List<Map<String, Any>>).map { PackageInformation.fromJson(it) }
            return ProjectInformation(packages)
        }
    }
}
