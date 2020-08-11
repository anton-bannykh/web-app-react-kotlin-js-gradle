import kotlinx.browser.*
import kotlinx.html.*
import kotlinx.html.div
import kotlinx.html.dom.*
import kotlinx.html.js.onClickFunction
import org.w3c.dom.*
import kotlin.math.*
import kotlin.random.Random

fun main() {
    val body: HTMLElement = document.body!!
    body.append.div("root") {
        button {
                +"Init"
                id = "run-button"
                onClickFunction = {
                    document.getElementById("run-button")?.remove()
                    launchAndForget {
                        runReact()
                    }
                }
        }
    }
}