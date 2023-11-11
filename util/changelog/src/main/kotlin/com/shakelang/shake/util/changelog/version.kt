package com.shakelang.shake.util.changelog



class Version(
    val major: Int,
    val minor: Int,
    val patch: Int,
    val suffix: String
) {

    override fun toString(): String {
        return "$major.$minor.$patch$suffix"
    }

    companion object {

        fun fromString(version: String): Version {
            val split = version.split(".")
            val major = split[0].toInt()
            val minor = split[1].toInt()
            val patch = split[2].toInt()
            val suffix = split[3]
            return Version(major, minor, patch, suffix)
        }

    }
}