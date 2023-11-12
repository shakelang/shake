package com.shakelang.shake.util.changelog



class Version(
    val major: Int,
    val minor: Int,
    val patch: Int,
    val suffix: String
) {

    override fun toString(): String {
        return "$major.$minor.$patch-$suffix"
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
                val suffix = split2[1]
                return Version(major, minor, patch, suffix)
            } catch (e: Exception) {
                throw IllegalArgumentException("Invalid version string: $version", e)
            }
        }

    }
}