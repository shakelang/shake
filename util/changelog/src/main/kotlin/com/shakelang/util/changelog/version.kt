package com.shakelang.util.changelog

class Version(
    var major: Int,
    var minor: Int,
    var patch: Int,
    var suffix: String
) {

    override fun toString(): String {
        return "$major.$minor.$patch${if (suffix != "") "-$suffix" else ""}"
    }

    fun incrementMajor() {
        major++
        minor = 0
        patch = 0
        suffix = ""
    }

    fun incrementMinor() {
        minor++
        patch = 0
        suffix = ""
    }

    fun incrementPatch() {
        patch++
        suffix = ""
    }

    fun clone(): Version {
        return Version(major, minor, patch, suffix)
    }

    companion object {

        fun fromString(version: String): Version {
            try {
                val split = version.split(".")
                val major = split[0].toInt()
                val minor = split[1].toInt()
                val third = split[2]
                val split2 = third.split("-")
                val patch = split2[0].toInt()
                val suffix = if (split2.size > 1) split2[1] else ""
                return Version(major, minor, patch, suffix)
            } catch (e: Exception) {
                throw IllegalArgumentException("Invalid version string: $version", e)
            }
        }
    }
}
