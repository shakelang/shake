package com.shakelang.shake.processor.program.map.information

class ProjectInformation(
    val packages: List<PackageInformation>,
) {
    fun json(): Map<String, Any> = mapOf(
        "type" to "project",
        "packages" to packages.map { it.json() },
    )
}
