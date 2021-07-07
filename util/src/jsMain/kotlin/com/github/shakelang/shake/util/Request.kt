package com.github.shakelang.shake.util

import org.w3c.xhr.XMLHttpRequest
import kotlin.js.Promise


@Suppress("unused")
object Request {

    fun request(method: RequestMethod, url: String): Promise<XMLHttpRequest> {
        return Promise {
            rs, rj ->
            run {
                val xhttp = XMLHttpRequest()
                xhttp.onreadystatechange = {
                    if(xhttp.readyState == 4.toShort()) rs(xhttp)
                }
                xhttp.open(method.toString(), url)
                xhttp.send()
            }
        }
    }

    fun get(url: String) = request(RequestMethod.GET, url)
    fun post(url: String) = request(RequestMethod.POST, url)

    fun requestString(method: RequestMethod, url: String) = request(method, url).then { it.responseText }
    fun getString(url: String) = requestString(RequestMethod.GET, url)
    fun postString(url: String) = requestString(RequestMethod.POST, url)

    fun requestXml(method: RequestMethod, url: String) = request(method, url).then { it.responseXML }
    fun getXml(url: String) = requestXml(RequestMethod.GET, url)
    fun postXml(url: String) = requestXml(RequestMethod.POST, url)

    fun requestJson(method: RequestMethod, url: String): Promise<dynamic> = requestString(method, url).then { JSON.parse(it) }
    fun getJson(url: String) = requestJson(RequestMethod.GET, url)
    fun postJson(url: String) = requestJson(RequestMethod.POST, url)

    enum class RequestMethod {
        GET, POST;
        override fun toString(): String = name
    }

}