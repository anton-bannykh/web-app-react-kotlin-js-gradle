import react.dom.*
import kotlinx.browser.document

fun runReact() {
    if (false) App()
    render(document.getElementById("root")) {
        child(App::class) {}
    }
}