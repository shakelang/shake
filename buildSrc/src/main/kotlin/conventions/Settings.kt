package conventions

import org.gradle.api.JavaVersion

const val MAJOR_JAVA_VERSION = 17
val JVM_TARGET = JavaVersion.VERSION_11
const val KOTLIN_TARGET = "11"
const val KOTLIN_VERSION = "1.9.23"

const val KOTEST_VERSION = "5.8.0"

const val GROUP = "com.shakelang"

fun projectGroup(name: String) = "$GROUP.$name"
