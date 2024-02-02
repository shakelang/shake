import com.shakelang.util.changelog.public
import com.shakelang.util.changelog.resolveVersion
import com.shakelang.util.embed.plugin.Embed
import com.shakelang.util.embed.plugin.getEmbedExtension
import conventions.dependencies
import conventions.projectGroup

plugins {
    id("conventions.all")
    id("conventions.publishing")
    id("io.kotest.multiplatform")
}

public = true
group = projectGroup("shake.compiler")
version = resolveVersion()
description = "Utilities for parsing stuff with kotlin"

repositories {
    mavenCentral()
}

kotlin {
    dependencies {
        implementation(project(":util:parseutils"))
        implementation(project(":util:shason"))
        implementation(project(":util:logger"))
        implementation(project(":shake:compiler:lexer"))
        testImplementation(kotlin("stdlib"))
        kotest()
    }
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

apply<Embed>()

getEmbedExtension(project).configuration {
    sourceSet.set("commonTest")
    distPackage.set("com.shakelang.shake.parser.test")
    distName.set("ShakeParserTestInput")
    embed("**/*.shake")
}

getEmbedExtension(project).configuration {
    sourceSet.set("commonTest")
    distPackage.set("com.shakelang.shake.parser.test")
    distName.set("ShakeParserTestOutput")
    embed("**/*.json")
}

fun String.forFileName() = this.replace(" ", "_").replace(".", "_").replace(",", "_").replace("(", "_").replace(")", "_")
inner class Template(val name: String) {
    val shakeFile = project.file("test-templates/$name.shake")
    val jsonFile = project.file("test-templates/$name.json")

    val shake = shakeFile.readText()
    val json = jsonFile.readText()
}

tasks.register("generateTests") {
    description = "Generates the test files for the parser"

    doLast {
        run {
            // Class Tests
            val classTestDirectory = project.file("src/commonTest/resources/tests/classes")
            classTestDirectory.mkdirs()

            run {
                // Class Fields
                val classFieldsDirectory = classTestDirectory.resolve("fields")
                classFieldsDirectory.mkdirs()

                val types = listOf(
                    "byte" to "byte",
                    "short" to "short",
                    "int" to "integer",
                    "long" to "long",
                    "float" to "float",
                    "double" to "double",
                    "char" to "char",
                    "boolean" to "boolean",
                    "unsigned byte" to "unsigned_byte",
                    "unsigned short" to "unsigned_short",
                    "unsigned int" to "unsigned_integer",
                    "unsigned long" to "unsigned_long",
                )

                val template = Template("class-field")

                types.forEach { type ->
                    val file = classFieldsDirectory.resolve("class_field_${type.second.forFileName()}.shake")
                    file.writeText(
                        template.shake
                            .replace("%type%", type.first)
                            .replace("%type_name%", type.second)
                            .replace("%name%", "field")

                    )

                    val jsonFile = classFieldsDirectory.resolve("class_field_${type.second.forFileName()}.json")
                    jsonFile.writeText(
                        template.json
                            .replace("%type%", type.first)
                            .replace("%type_name%", type.second)
                            .replace("%name%", "field")
                    )
                }
            }
        }
    }
}
