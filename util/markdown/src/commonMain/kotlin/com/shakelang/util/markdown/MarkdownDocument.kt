package com.shakelang.util.markdown

interface MarkdownDocument {
    val elements: List<MarkdownElement>
    fun render(): String = elements.joinToString("\n\n") { it.toMarkdown().joinToString("\n") }
}

@Suppress("unused")
class MarkdownDocumentImpl(override val elements: List<MarkdownElement>) : MarkdownDocument {
    override fun toString(): String = elements.joinToString("\n") { it.toMarkdown().joinToString("\n") }
}

class MutableMarkdownDocument() : MarkdownDocument, MarkdownContext() {

    constructor(creator: MutableMarkdownDocument.() -> Unit) : this() {
        creator()
    }

    fun code(code: String, type: String = "") = elements.add(MarkdownCodeElement(code, type))
    fun header(text: String, level: Int = 1) = elements.add(MarkdownHeaderElement(text, level))
    fun h1(text: String) = header(text, 1)
    fun h2(text: String) = header(text, 2)
    fun h3(text: String) = header(text, 3)
    fun h4(text: String) = header(text, 4)
    fun h5(text: String) = header(text, 5)
    fun h6(text: String) = header(text, 6)
    fun list(elements: List<MarkdownElement>, ordered: Boolean = false) =
        this.elements.add(MarkdownListElement(elements, ordered))

    fun ulist(elements: List<MarkdownElement>) = list(elements, false)
    fun olist(elements: List<MarkdownElement>) = list(elements, true)
    fun ul(vararg elements: MarkdownElement) = ulist(elements.toList())
    fun ol(vararg elements: MarkdownElement) = olist(elements.toList())
    fun table(headers: List<String>, rows: List<List<MarkdownElement>>) =
        elements.add(MarkdownTableElement(headers, rows))

    fun html(html: String) = elements.add(MarkdownHtmlElement(html))

    override fun render(): String = elements.joinToString("\n\n") { it.toMarkdown().joinToString("\n") }
}

open class MarkdownContext() {

    constructor(creator: MarkdownContext.() -> Unit) : this() {
        creator()
    }

    val elements = mutableListOf<MarkdownElement>()

    fun link(text: String, url: String) = elements.add(MarkdownLinkElement(text, url))
    fun link(url: String, creator: MarkdownContext.() -> Unit) =
        elements.add(MarkdownLinkElement(MarkdownContext().apply(creator).render(), url))

    fun image(text: String, url: String) = elements.add(MarkdownImageElement(text, url))
    fun image(url: String, creator: MarkdownContext.() -> Unit) =
        elements.add(MarkdownImageElement(MarkdownContext().apply(creator).render(), url))

    fun quote(elements: List<MarkdownElement>) = this.elements.add(MarkdownQuoteElement(elements))
    fun quote(creator: MarkdownContext.() -> Unit) =
        this.elements.add(MarkdownQuoteElement(MarkdownContext().apply(creator).elements))

    fun bold(elements: List<MarkdownElement>) = this.elements.add(MarkdownBoldElement(elements))
    fun bold(creator: MarkdownContext.() -> Unit) =
        this.elements.add(MarkdownBoldElement(MarkdownContext().apply(creator).elements))

    fun italic(elements: List<MarkdownElement>) = this.elements.add(MarkdownItalicElement(elements))
    fun italic(creator: MarkdownContext.() -> Unit) =
        this.elements.add(MarkdownItalicElement(MarkdownContext().apply(creator).elements))

    fun strikethrough(elements: List<MarkdownElement>) = this.elements.add(MarkdownStrikethroughElement(elements))
    fun strikethrough(creator: MarkdownContext.() -> Unit) =
        this.elements.add(MarkdownStrikethroughElement(MarkdownContext().apply(creator).elements))

    fun text(text: String) = elements.add(MarkdownTextElement(text))
    fun text(creator: MarkdownContext.() -> Unit) =
        elements.add(MarkdownTextElement(MarkdownContext().apply(creator).render()))

    fun p(text: String) = text(text)
    fun p(creator: MarkdownContext.() -> Unit) = text(creator)
    open fun render(): String = elements.joinToString("\n") { it.toMarkdown().joinToString("\n") }
}

interface MarkdownElement {
    fun toMarkdown(): List<String>
}

class MarkdownTextElement(val text: String) : MarkdownElement {
    override fun toMarkdown(): List<String> = text.split("\n")
}

class MarkdownCodeElement(val code: String, val type: String) : MarkdownElement {
    override fun toMarkdown(): List<String> = listOf("```$type\n$code\n```")
}

class MarkdownLinkElement(val text: String, val url: String) : MarkdownElement {
    override fun toMarkdown() = listOf("[$text]($url)")
}

class MarkdownImageElement(val text: String, val url: String) : MarkdownElement {
    override fun toMarkdown() = listOf("![$text]($url)")
}

class MarkdownHeaderElement(val text: String, val level: Int) : MarkdownElement {
    override fun toMarkdown() = listOf("#".repeat(level) + " $text")
}

class MarkdownListElement(val elements: List<MarkdownElement>, val ordered: Boolean) : MarkdownElement {
    override fun toMarkdown() =
        elements.flatMap {
            it.toMarkdown().mapIndexed { index, s ->
                if (ordered) "${index + 1}. $s" else "* $s"
            }
        }
}

class MarkdownTableElement(val headers: List<String>, val rows: List<List<MarkdownElement>>) : MarkdownElement {
    override fun toMarkdown(): List<String> {
        val header = "| ${headers.joinToString(" | ")} |"
        val separator = "| ${headers.joinToString(" | ") { "---" }} |"
        val rows = rows.map { "| ${it.joinToString(" | ") { it.toMarkdown().joinToString("\n") }} |" }
        return listOf(header, separator) + rows
    }
}

class MarkdownQuoteElement(val elements: List<MarkdownElement>) : MarkdownElement {
    override fun toMarkdown() = elements.flatMap { it.toMarkdown().map { "> $it" } }
}

class MarkdownBoldElement(val elements: List<MarkdownElement>) : MarkdownElement {
    override fun toMarkdown() = elements.flatMap { it.toMarkdown().map { "**$it**" } }
}

class MarkdownItalicElement(val elements: List<MarkdownElement>) : MarkdownElement {
    override fun toMarkdown() = elements.flatMap { it.toMarkdown().map { "*$it*" } }
}

class MarkdownStrikethroughElement(val elements: List<MarkdownElement>) : MarkdownElement {
    override fun toMarkdown() = elements.flatMap { it.toMarkdown().map { "~~$it~~" } }
}

class MarkdownHorizontalRuleElement : MarkdownElement {
    override fun toMarkdown() = listOf("---")
}

class MarkdownLineBreakElement : MarkdownElement {
    override fun toMarkdown() = listOf("  ")
}

class MarkdownHtmlElement(val html: String) : MarkdownElement {
    override fun toMarkdown() = listOf(html)
}
