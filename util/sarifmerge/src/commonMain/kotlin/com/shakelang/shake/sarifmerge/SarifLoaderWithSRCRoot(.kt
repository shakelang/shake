package com.shakelang.shake.sarifmerge

import com.shakelang.shake.util.shason.elements.JsonArray
import com.shakelang.shake.util.shason.elements.JsonElement
import com.shakelang.shake.util.shason.elements.JsonObject


class SarifEntry(
    val level: String,
    val locations: JsonElement,
    val message: JsonElement,
    val ruleId: String
)

class SarifLoaderWithSRCRoot (
    val srcRoot: String
) {

    val entries = mutableListOf<SarifEntry>()

    fun addResult(entry: SarifEntry) {
        entries.add(entry)
    }

    fun addResult(json: JsonObject) {
        if(!json.containsKey("level")) throw IllegalArgumentException("SarifEntry does not contain 'level' key")
        if(!json.containsKey("locations")) throw IllegalArgumentException("SarifEntry does not contain 'locations' key")
        if(!json.containsKey("message")) throw IllegalArgumentException("SarifEntry does not contain 'message' key")
        if(!json.containsKey("ruleId")) throw IllegalArgumentException("SarifEntry does not contain 'ruleId' key")

        if(!json["level"]!!.isJsonPrimitive() || json["level"]!!.toJsonPrimitive().isString()) throw IllegalArgumentException("SarifEntry 'level' key is not a string")
        if(!json["ruleId"]!!.isJsonPrimitive() || json["ruleId"]!!.toJsonPrimitive().isString()) throw IllegalArgumentException("SarifEntry 'ruleId' key is not a string")

        val level = json["level"]!!.toJsonPrimitive().toStringElement().value
        val locations = json["locations"]!!
        val message = json["message"]!!
        val ruleId = json["ruleId"]!!.toJsonPrimitive().toStringElement().value

        addResult(SarifEntry(level, locations, message, ruleId))
    }

    fun addResults(json: JsonArray) {
        for (element in json) {
            if(!element.isJsonObject()) throw IllegalArgumentException("SarifEntry is not a JsonObject")
            addResult(element.toJsonObject())
        }
    }

    fun addResults(json: JsonElement) {
        if(!json.isJsonArray()) throw IllegalArgumentException("SarifEntry is not a JsonArray")
        addResults(json.toJsonArray())
    }

    fun acceptsRun (
        run: JsonObject
    ): Boolean {

        if(!run.containsKey("originalUriBaseIds")) throw IllegalArgumentException("Sarif run does not contain 'originalUriBaseIds' key")
        if(!run.containsKey("results")) throw IllegalArgumentException("Sarif run does not contain 'results' key")
        if(!run.containsKey("tool") || !run["tool"]!!.isJsonObject()) throw IllegalArgumentException("Sarif run does not contain 'tool' key or 'tool' key is not a JsonObject")

        if(!run["originalUriBaseIds"]!!.isJsonObject()) throw IllegalArgumentException("Sarif run 'originalUriBaseIds' key is not a JsonObject")
        if(!run["results"]!!.isJsonArray()) throw IllegalArgumentException("Sarif run 'results' key is not a JsonArray")
        if(!run["tool"]!!.isJsonObject()) throw IllegalArgumentException("Sarif run 'tool' key is not a JsonObject")

        val originalUriBaseIds = run["originalUriBaseIds"]!!.toJsonObject()
        if(!originalUriBaseIds.containsKey("SRCROOT")) throw IllegalArgumentException("Sarif run 'originalUriBaseIds' key does not contain 'SRCROOT' key")
        if(!originalUriBaseIds["SRCROOT"]!!.isJsonPrimitive()) throw IllegalArgumentException("Sarif run 'originalUriBaseIds' key 'SRCROOT' key is not a JsonPrimitive")
        if(!originalUriBaseIds["SRCROOT"]!!.toJsonPrimitive().isString()) throw IllegalArgumentException("Sarif run 'originalUriBaseIds' key 'SRCROOT' key is not a string")

        val srcRoot = originalUriBaseIds["SRCROOT"]!!.toJsonPrimitive().toStringElement().value

        return srcRoot == this.srcRoot
    }

    fun addRun (
        run: JsonObject
    ) {
        if(!acceptsRun(run)) throw IllegalArgumentException("Sarif run does not match SRCROOT")
        val results = run["results"]!!.toJsonArray()
        addResults(results)
    }

    fun addRun (
        run: JsonElement
    ) {
        if(!run.isJsonObject()) throw IllegalArgumentException("Sarif run is not a JsonObject")
        addRun(run.toJsonObject())
    }

    fun addRuns (
        runs: JsonArray
    ) {
        for (element in runs) {
            if(!element.isJsonObject()) throw IllegalArgumentException("Sarif run is not a JsonObject")
            addRun(element.toJsonObject())
        }
    }

    fun addRuns (
        runs: JsonElement
    ) {
        if(!runs.isJsonArray()) throw IllegalArgumentException("Sarif run is not a JsonArray")
        addRuns(runs.toJsonArray())
    }

}