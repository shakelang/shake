package com.shakelang.shake.sarifmerge

import com.shakelang.shake.util.shason.elements.JsonArray
import com.shakelang.shake.util.shason.elements.JsonElement
import com.shakelang.shake.util.shason.elements.JsonObject
import com.shakelang.shake.util.shason.json
import java.io.File

class SarifEntry(
    val level: String,
    val locations: JsonElement,
    val message: JsonElement,
    val ruleId: String
) {
    fun toJsonObject(): Any {
        val obj = mapOf(
            "level" to level,
            "locations" to locations,
            "message" to message,
            "ruleId" to ruleId
        )
        return obj
    }
}

class SarifLoaderWithSRCRoot(
    val srcRoot: String?
) {

    val entries = mutableListOf<SarifEntry>()
    var tool: JsonObject? = null

    fun addResult(entry: SarifEntry) {
        entries.add(entry)
    }

    fun addResult(json: JsonObject) {
        if (!json.containsKey("level")) throw IllegalArgumentException("SarifEntry does not contain 'level' key")
        if (!json.containsKey("locations")) throw IllegalArgumentException("SarifEntry does not contain 'locations' key")
        if (!json.containsKey("message")) throw IllegalArgumentException("SarifEntry does not contain 'message' key")
        if (!json.containsKey("ruleId")) throw IllegalArgumentException("SarifEntry does not contain 'ruleId' key")

        if (!json["level"]!!.isJsonPrimitive() || !json["level"]!!.toJsonPrimitive().isString()) {
            throw IllegalArgumentException("SarifEntry 'level' key is not a string")
        }

        if (!json["ruleId"]!!.isJsonPrimitive() || !json["ruleId"]!!.toJsonPrimitive().isString()) {
            throw IllegalArgumentException("SarifEntry 'ruleId' key is not a string")
        }

        val level = json["level"]!!.toJsonPrimitive().toStringElement().value
        val locations = json["locations"]!!
        val message = json["message"]!!
        val ruleId = json["ruleId"]!!.toJsonPrimitive().toStringElement().value

        addResult(SarifEntry(level, locations, message, ruleId))
    }

    fun addResults(json: JsonArray) {
        for (element in json) {
            if (!element.isJsonObject()) throw IllegalArgumentException("SarifEntry is not a JsonObject")
            addResult(element.toJsonObject())
        }
    }

    fun addResults(json: JsonElement) {
        if (!json.isJsonArray()) throw IllegalArgumentException("SarifEntry is not a JsonArray")
        addResults(json.toJsonArray())
    }

    fun acceptsRun(
        run: JsonObject
    ): Boolean {
        if (!run.containsKey("results")) throw IllegalArgumentException("Sarif run does not contain 'results' key")
        if (!run.containsKey("tool") || !run["tool"]!!.isJsonObject()) throw IllegalArgumentException("Sarif run does not contain 'tool' key or 'tool' key is not a JsonObject")

        val srcRoot = if (run.containsKey("originalUriBaseIds") && run["originalUriBaseIds"]!!.isJsonObject()) {
            parseSourceRootElement(run["originalUriBaseIds"]!!.toJsonObject()["%SRCROOT%"]!!)
        } else {
            null
        }

        if (!run["results"]!!.isJsonArray()) throw IllegalArgumentException("Sarif run 'results' key is not a JsonArray")
        if (!run["tool"]!!.isJsonObject()) throw IllegalArgumentException("Sarif run 'tool' key is not a JsonObject")

        return srcRoot == this.srcRoot || srcRoot == null || this.srcRoot == ""
    }

    fun addRun(
        run: JsonObject
    ) {
        if (!acceptsRun(run)) throw IllegalArgumentException("Sarif run does not match SRCROOT")
        val results = run["results"]!!.toJsonArray()
        this.tool = run["tool"]!!.toJsonObject()
        addResults(results)
    }

    fun addRun(
        run: JsonElement
    ) {
        if (!run.isJsonObject()) throw IllegalArgumentException("Sarif run is not a JsonObject")
        addRun(run.toJsonObject())
    }

    fun addRuns(
        runs: JsonArray
    ) {
        for (element in runs) {
            if (!element.isJsonObject()) throw IllegalArgumentException("Sarif run is not a JsonObject")
            addRun(element.toJsonObject())
        }
    }

    fun addRuns(
        runs: JsonElement
    ) {
        if (!runs.isJsonArray()) throw IllegalArgumentException("Sarif run is not a JsonArray")
        addRuns(runs.toJsonArray())
    }

    fun toRuns(limitEntriesPerRun: Int = -1): List<Any> {
        val numberOfRuns = if (limitEntriesPerRun == -1) 1 else (entries.size / limitEntriesPerRun) + 1

        val runs = mutableListOf<Any>()

        for (i in 0 until numberOfRuns) {
            val results = mutableListOf<Any>()

            for (j in 0 until limitEntriesPerRun) {
                val index = i * limitEntriesPerRun + j
                if (index >= entries.size) break
                results.add(entries[index].toJsonObject())
            }

            runs.add(
                mapOf(
                    "originalUriBaseIds" to mapOf(
                        "%SRCROOT%" to mapOf(
                            "uri" to (srcRoot ?: "")
                        )
                    ),
                    "results" to results,
                    "tool" to tool!!
                )
            )
        }

        return runs
    }
}

class SarifLoader {
    val subLoaders = mutableListOf<SarifLoaderWithSRCRoot>()

    val version: String = "2.1.0"
    val schema = "https://raw.githubusercontent.com/oasis-tcs/sarif-spec/master/Schemata/sarif-schema-2.1.0.json"

    fun addRun(
        run: JsonObject
    ) {
        if (!run.containsKey("results")) throw IllegalArgumentException("Sarif run does not contain 'results' key")

        val srcRoot = if (run.containsKey("originalUriBaseIds") && run["originalUriBaseIds"]!!.isJsonObject()) {
            parseSourceRootElement(run["originalUriBaseIds"]!!.toJsonObject()["%SRCROOT%"]!!)
        } else {
            null
        }
        if (!run["results"]!!.isJsonArray()) throw IllegalArgumentException("Sarif run 'results' key is not a JsonArray")

        for (loader in subLoaders) {
            if (loader.acceptsRun(run)) {
                loader.addRun(run)
                return
            }
        }

        val loader = SarifLoaderWithSRCRoot(srcRoot)
        loader.addRun(run)
        subLoaders.add(loader)
    }

    fun addRun(
        run: JsonElement
    ) {
        if (!run.isJsonObject()) throw IllegalArgumentException("Sarif run is not a JsonObject")
        addRun(run.toJsonObject())
    }

    fun addRuns(
        runs: JsonArray
    ) {
        for (element in runs) {
            if (!element.isJsonObject()) throw IllegalArgumentException("Sarif run is not a JsonObject")
            addRun(element.toJsonObject())
        }
    }

    fun addRuns(
        runs: JsonElement
    ) {
        if (!runs.isJsonArray()) throw IllegalArgumentException("Sarif run is not a JsonArray")
        addRuns(runs.toJsonArray())
    }

    fun addFile(
        file: JsonObject
    ) {
        if (!file.containsKey("version")) throw IllegalArgumentException("Sarif file does not contain 'version' key")
        if (!file.containsKey("\$schema")) throw IllegalArgumentException("Sarif file does not contain '\$schema' key")
        if (!file.containsKey("runs")) throw IllegalArgumentException("Sarif file does not contain 'runs' key")

        if (!file["version"]!!.isJsonPrimitive() || !file["version"]!!.toJsonPrimitive().isString()) {
            throw IllegalArgumentException("Sarif file 'version' key is not a string, but '${file["version"]!!::class.java}'")
        }

        if (!file["\$schema"]!!.isJsonPrimitive() || !file["\$schema"]!!.toJsonPrimitive().isString()) {
            throw IllegalArgumentException("Sarif file '\$schema' key is not a string, but '${file["\$schema"]!!::class.java}'")
        }

        if (!file["runs"]!!.isJsonArray()) {
            throw IllegalArgumentException("Sarif file 'runs' key is not a JsonArray, but '${file["runs"]!!::class.java}'")
        }

        val version = file["version"]!!.toJsonPrimitive().toStringElement().value
        val schema = file["\$schema"]!!.toJsonPrimitive().toStringElement().value
        val runs = file["runs"]!!.toJsonArray()

        if (version != this.version) throw IllegalArgumentException("Sarif file version does not match")
        if (schema != this.schema) throw IllegalArgumentException("Sarif file schema does not match")

        addRuns(runs)
    }

    fun addFile(
        file: JsonElement
    ) {
        if (!file.isJsonObject()) throw IllegalArgumentException("Sarif file is not a JsonObject")
        addFile(file.toJsonObject())
    }

    fun addFile(
        file: File
    ) {
        val reader = file.reader()
        val contents = reader.readText()
        reader.close()
        try {
            addFile(json.parse(contents))
        } catch (e: Exception) {
            throw IllegalArgumentException("Sarif file ${file.path} is not a valid sarif file", e)
        }
    }

    fun addFiles(
        files: Set<File>
    ) {
        for (file in files) {
            addFile(file)
        }
    }

    fun getRuns(): List<Any> {
        val runs = mutableListOf<Any>()
        for (loader in subLoaders) {
            runs.addAll(loader.toRuns())
        }
        return runs
    }

    fun getRuns(limitEntriesPerRun: Int): List<Any> {
        val runs = mutableListOf<Any>()
        for (loader in subLoaders) {
            runs.addAll(loader.toRuns(limitEntriesPerRun))
        }
        return runs
    }

    fun generateFiles(
        limitRunsPerFile: Int = -1,
        limitEntriesPerRun: Int = -1
    ): List<Any> {
        val runs = getRuns(limitEntriesPerRun)
        val numberOfFiles = if (limitRunsPerFile == -1) 1 else (runs.size / limitRunsPerFile) + 1

        val files = mutableListOf<Any>()

        for (i in 0 until numberOfFiles) {
            val runsInFile = mutableListOf<Any>()

            for (j in 0 until limitRunsPerFile) {
                val index = i * limitRunsPerFile + j
                if (index >= runs.size) break
                runsInFile.add(runs[index])
            }

            files.add(
                mapOf(
                    "version" to version,
                    "\$schema" to schema,
                    "runs" to runsInFile
                )
            )
        }

        return files
    }
}

fun parseSourceRootElement(element: JsonElement): String {
    return if (element.isJsonPrimitive() && element.toJsonPrimitive()
        .isString()
    ) {
        element.toJsonPrimitive().toStringElement().value
    } else if (element.isJsonObject() && element.toJsonObject()
        .containsKey("uri") && element.toJsonObject()["uri"]!!.isJsonPrimitive() &&
        element.toJsonObject()["uri"]!!.toJsonPrimitive().isString()
    ) {
        element.toJsonObject()["uri"]!!.toJsonPrimitive().toStringElement().value
    } else {
        throw IllegalArgumentException("Sarif run 'originalUriBaseIds' key '%SRCROOT%' key is invalid (${element::class.java})}")
    }
}
