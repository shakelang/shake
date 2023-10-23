import kotlin.js.Promise
import kotlin.test.Test

class Test {

    @Test
    fun test() {

        Promise.resolve("testaaaaaaaaaaaaaa").then { println(it) }

    }

}