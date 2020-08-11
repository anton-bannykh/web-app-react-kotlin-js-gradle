import react.*


@JsModule("react-player")
@JsNonModule
external object ReactPlayer {
    val default: RClass<ReactPlayerProps>
}

external interface ReactPlayerProps : RProps {
    var url: String
}