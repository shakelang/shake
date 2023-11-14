package com.shakelang.shake.util.changelog
import com.shakelang.shake.util.shason.elements.JsonElement
import com.shakelang.shake.util.shason.elements.JsonObject
import com.shakelang.shake.util.shason.json
import org.eclipse.jgit.api.Git
import org.eclipse.jgit.api.errors.GitAPIException
import org.eclipse.jgit.api.errors.NoFilepatternException
import org.eclipse.jgit.api.errors.NoHeadException
import org.eclipse.jgit.errors.RepositoryNotFoundException
import java.io.File

fun findGitRepository(file: File): Git? {
    // Check if the file is a Git repository
    try {
        return Git.open(file)
    } catch (e: RepositoryNotFoundException) {
        // File is not a Git repository
    } catch (e: NoHeadException) {
        // File is not a Git repository
    }

    // If the file is not a Git repository, try with the parent folder
    val parentFile = file.parentFile
    if (parentFile != null && parentFile.exists()) {
        return findGitRepository(parentFile)
    }

    // If no Git repository is found, return null
    return null
}

fun Changelog.git(): Git? {
    return findGitRepository(this.project.file("."))
}

fun <T : Git> T.commitAllChanges(commitMessage: String): T {
    try {
        // Add all changes to the index
        add().addFilepattern(".").call()

        // Commit the changes
        commit().setMessage(commitMessage).call()

        println("Changes committed successfully.")
    } catch (e: NoFilepatternException) {
        println("No changes to commit.")
    } catch (e: GitAPIException) {
        println("Error committing changes: ${e.message}")
    }

    return this
}

fun <T : Git> T.createTag(name: String) {
    try {
        tag().setName(name).call()
        println("Tag created successfully.")
    } catch (e: GitAPIException) {
        println("Error creating tag: ${e.message}")
    }
}

fun <T : Git> T.pushTags() {
    try {
        push().setPushTags().call()
        println("Tags pushed successfully.")
    } catch (e: GitAPIException) {
        println("Error pushing tags: ${e.message}")
    }
}

class TagStash (
    val name: String,
) {
    fun toObject(): Any {
        return mapOf(
            "name" to name,
        )
    }

    override fun toString(): String {
        return json.stringify(toObject())
    }

    companion object {
        fun fromObject(obj: JsonObject): TagStash {
            if (!obj.containsKey("name")) throw IllegalArgumentException("TagStash object has no name")
            val name = obj["name"]!!
            if(!name.isJsonPrimitive() || !name.toJsonPrimitive().isString()) throw IllegalArgumentException("TagStash name is not a string")
            return TagStash(name.toJsonPrimitive().toStringElement().value)
        }
    }
}

class TagStashList (
    val tags: List<TagStash>,
) {
    fun toObject(): Any {
        return mapOf(
            "tags" to tags.map { it.toObject() }
        )
    }

    override fun toString(): String {
        return json.stringify(toObject())
    }

    companion object {
        fun fromObject(obj: JsonObject): TagStashList {
            if (!obj.containsKey("tags")) throw IllegalArgumentException("TagStashList object has no tags")
            val tags = obj["tags"]!!
            if(!tags.isJsonArray()) throw IllegalArgumentException("TagStashList tags is not an array")
            return TagStashList(tags.toJsonArray().map {
                if(!it.isJsonObject()) throw IllegalArgumentException("TagStashList tags array contains non object")
                TagStash.fromObject(it.toJsonObject())
            })
        }

        fun fromObject(obj: JsonElement): TagStashList {
            if(!obj.isJsonObject()) throw IllegalArgumentException("TagStashList object is not an object")
            return fromObject(obj.toJsonObject())
        }

        fun empty(): TagStashList {
            return TagStashList(emptyList())
        }
    }
}

fun Changelog.getStash(): TagStashList {
    val file = project.file(".changelog/stash.json")
    if(!file.exists()) return TagStashList.empty()
    return TagStashList.fromObject(json.parse(file.readText()))
}

fun Changelog.setStash(stash: TagStashList) {
    val file = project.file(".changelog/stash.json")
    file.writeText(stash.toString())
}