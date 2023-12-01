package com.shakelang.shake.sarifmerge

import com.shakelang.shake.util.shason.elements.JsonArray
import com.shakelang.shake.util.shason.elements.JsonElement
import com.shakelang.shake.util.shason.elements.JsonObject


class SarifEntry(
    val level: String,
    val locations: JsonElement,
    val message: JsonElement,
    val ruleId: String
) {
    fun toJsonObject(): JsonObject {
        val obj = JsonObject.of(
            "level" to level,
            "locations" to locations,
            "message" to message,
            "ruleId" to ruleId
        )
        return obj
    }
}

class SarifLoaderWithSRCRoot(
    val srcRoot: String
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

        if (!json["level"]!!.isJsonPrimitive() || json["level"]!!.toJsonPrimitive()
                .isString()
        ) throw IllegalArgumentException("SarifEntry 'level' key is not a string")
        if (!json["ruleId"]!!.isJsonPrimitive() || json["ruleId"]!!.toJsonPrimitive()
                .isString()
        ) throw IllegalArgumentException("SarifEntry 'ruleId' key is not a string")

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

        if (!run.containsKey("originalUriBaseIds")) throw IllegalArgumentException("Sarif run does not contain 'originalUriBaseIds' key")
        if (!run.containsKey("results")) throw IllegalArgumentException("Sarif run does not contain 'results' key")
        if (!run.containsKey("tool") || !run["tool"]!!.isJsonObject()) throw IllegalArgumentException("Sarif run does not contain 'tool' key or 'tool' key is not a JsonObject")

        if (!run["originalUriBaseIds"]!!.isJsonObject()) throw IllegalArgumentException("Sarif run 'originalUriBaseIds' key is not a JsonObject")
        if (!run["results"]!!.isJsonArray()) throw IllegalArgumentException("Sarif run 'results' key is not a JsonArray")
        if (!run["tool"]!!.isJsonObject()) throw IllegalArgumentException("Sarif run 'tool' key is not a JsonObject")

        val originalUriBaseIds = run["originalUriBaseIds"]!!.toJsonObject()
        if (!originalUriBaseIds.containsKey("%SRCROOT%")) throw IllegalArgumentException("Sarif run 'originalUriBaseIds' key does not contain 'SRCROOT' key")
        if (!originalUriBaseIds["%SRCROOT%"]!!.isJsonPrimitive()) throw IllegalArgumentException("Sarif run 'originalUriBaseIds' key 'SRCROOT' key is not a JsonPrimitive")
        if (!originalUriBaseIds["%SRCROOT%"]!!.toJsonPrimitive()
                .isString()
        ) throw IllegalArgumentException("Sarif run 'originalUriBaseIds' key 'SRCROOT' key is not a string")

        val srcRoot = originalUriBaseIds["%SRCROOT%"]!!.toJsonPrimitive().toStringElement().value

        return srcRoot == this.srcRoot
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

    fun toRuns(limitEntriesPerRun: Int = -1): List<JsonObject> {
        val numberOfRuns = if (limitEntriesPerRun == -1) 1 else (entries.size / limitEntriesPerRun) + 1

        val runs = mutableListOf<JsonObject>()

        for (i in 0 until numberOfRuns) {

            val results = mutableListOf<JsonObject>()

            for (j in 0 until limitEntriesPerRun) {
                val index = i * limitEntriesPerRun + j
                if (index >= entries.size) break
                results.add(entries[index].toJsonObject())
            }

            runs.add(
                JsonObject.of(
                    "originalUriBaseIds" to JsonObject.of(
                        "%SRCROOT%" to srcRoot
                    ),
                    "results" to JsonArray.of(results),
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

        if (!run.containsKey("originalUriBaseIds")) throw IllegalArgumentException("Sarif run does not contain 'originalUriBaseIds' key")
        if (!run.containsKey("results")) throw IllegalArgumentException("Sarif run does not contain 'results' key")
        if (!run.containsKey("tool") || !run["tool"]!!.isJsonObject()) throw IllegalArgumentException("Sarif run does not contain 'tool' key or 'tool' key is not a JsonObject")

        if (!run["originalUriBaseIds"]!!.isJsonObject()) throw IllegalArgumentException("Sarif run 'originalUriBaseIds' key is not a JsonObject")
        if (!run["results"]!!.isJsonArray()) throw IllegalArgumentException("Sarif run 'results' key is not a JsonArray")
        if (!run["tool"]!!.isJsonObject()) throw IllegalArgumentException("Sarif run 'tool' key is not a JsonObject")

        val originalUriBaseIds = run["originalUriBaseIds"]!!.toJsonObject()
        if (!originalUriBaseIds.containsKey("%SRCROOT%")) throw IllegalArgumentException("Sarif run 'originalUriBaseIds' key does not contain 'SRCROOT' key")
        if (!originalUriBaseIds["%SRCROOT%"]!!.isJsonPrimitive()) throw IllegalArgumentException("Sarif run 'originalUriBaseIds' key 'SRCROOT' key is not a JsonPrimitive")

        val srcRoot = originalUriBaseIds["%SRCROOT%"]!!.toJsonPrimitive().toStringElement().value

        for (loader in subLoaders) {
            if (loader.srcRoot == srcRoot) {
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

    fun getRuns(): List<JsonObject> {
        val runs = mutableListOf<JsonObject>()
        for (loader in subLoaders) {
            runs.addAll(loader.toRuns())
        }
        return runs
    }

    fun getRuns(limitEntriesPerRun: Int): List<JsonObject> {
        val runs = mutableListOf<JsonObject>()
        for (loader in subLoaders) {
            runs.addAll(loader.toRuns(limitEntriesPerRun))
        }
        return runs
    }
}