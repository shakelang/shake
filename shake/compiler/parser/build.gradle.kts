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
                            .replace("%name%", "field"),

                        )

                    val jsonFile = classFieldsDirectory.resolve("class_field_${type.second.forFileName()}.json")
                    jsonFile.writeText(
                        template.json
                            .replace("%type%", type.first)
                            .replace("%type_name%", type.second)
                            .replace("%name%", "field"),
                    )
                }
            }
        }

        run {
            // Class Tests
            val fieldsDirectory = project.file("src/commonTest/resources/tests/fields")
            fieldsDirectory.mkdirs()

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

            val template0 = Template("field")
            val template1 = Template("initialized-field")

            types.forEach { type ->

                fun generateTemplates(
                    template: Template,
                    nameBase: String,
                    access: String,
                    static: String,
                    final: String,
                    attributes: String,
                ) {
                    val file = fieldsDirectory.resolve("$nameBase.shake")
                    file.parentFile.mkdirs()
                    file.writeText(
                        template.shake
                            .replace("%type%", type.first)
                            .replace("%type_name%", type.second)
                            .replace("%name%", "field")
                            .replace("%access%", access)
                            .replace("%static%", static)
                            .replace("%final%", final)
                            .replace("%attributes%", attributes),
                    )

                    val jsonFile = fieldsDirectory.resolve("$nameBase.json")
                    jsonFile.writeText(
                        template.json
                            .replace("%type%", type.first)
                            .replace("%type_name%", type.second)
                            .replace("%name%", "field")
                            .replace("%access%", access)
                            .replace("%static%", static)
                            .replace("%final%", final)
                            .replace("%attributes%", attributes),
                    )
                }

                generateTemplates(
                    template0,
                    "${type.second.forFileName()}/field0",
                    "package",
                    "false",
                    "false",
                    "",
                )

                generateTemplates(
                    template0,
                    "${type.second.forFileName()}/field1",
                    "public",
                    "false",
                    "false",
                    "public ",
                )

                generateTemplates(
                    template0,
                    "${type.second.forFileName()}/field2",
                    "protected",
                    "false",
                    "false",
                    "protected ",
                )


                generateTemplates(
                    template0,
                    "${type.second.forFileName()}/field3",
                    "private",
                    "false",
                    "false",
                    "private ",
                )

                generateTemplates(
                    template1,
                    "${type.second.forFileName()}/initialized_field0",
                    "package",
                    "false",
                    "false",
                    "",
                )

                generateTemplates(
                    template1,
                    "${type.second.forFileName()}/initialized_field1",
                    "public",
                    "false",
                    "false",
                    "public ",
                )

                generateTemplates(
                    template1,
                    "${type.second.forFileName()}/initialized_field2",
                    "protected",
                    "false",
                    "false",
                    "protected ",
                )

                generateTemplates(
                    template1,
                    "${type.second.forFileName()}/initialized_field3",
                    "private",
                    "false",
                    "false",
                    "private ",
                )

                generateTemplates(
                    template1,
                    "${type.second.forFileName()}/initialized_field4",
                    "package",
                    "false",
                    "true",
                    "final ",
                )

                generateTemplates(
                    template1,
                    "${type.second.forFileName()}/initialized_field5",
                    "public",
                    "false",
                    "true",
                    "public final ",
                )

                generateTemplates(
                    template1,
                    "${type.second.forFileName()}/initialized_field6",
                    "protected",
                    "false",
                    "true",
                    "protected final ",
                )

                generateTemplates(
                    template1,
                    "${type.second.forFileName()}/initialized_field7",
                    "private",
                    "false",
                    "true",
                    "private final ",
                )

                generateTemplates(
                    template1,
                    "${type.second.forFileName()}/initialized_field8",
                    "public",
                    "false",
                    "true",
                    "final public ",
                )

                generateTemplates(
                    template1,
                    "${type.second.forFileName()}/initialized_field8",
                    "protected",
                    "false",
                    "true",
                    "final protected ",
                )

                generateTemplates(
                    template1,
                    "${type.second.forFileName()}/initialized_field9",
                    "private",
                    "false",
                    "true",
                    "final private ",
                )
            }
        }
    }
}
