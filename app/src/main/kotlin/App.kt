import kotlinx.html.js.onClickFunction
import react.*
import react.dom.*
import kotlin.js.*

external interface AppState : RState {
    var currentVideo: Video?
    var unwatchedVideos: List<Video>
    var watchedVideos: List<Video>
    var videoLoader: (((List<Video>) -> Unit) -> Unit)?
}

class App : RComponent<RProps, AppState>() {
    override fun AppState.init() {
        unwatchedVideos = listOf()
        watchedVideos = listOf()
        videoLoader = null
    }

    override fun RBuilder.render() {
        h1 {
            +"KotlinConf Explorer"
        }

        val loader = state.videoLoader
        if (loader == null) {
            button {
                +"Load js"
                attrs {
                    onClickFunction = {
                        launchAndForget {
                            setState {
                                videoLoader = ::loadVideo
                            }
                        }
                    }
                }
            }
        } else {
            button {
                +"Fetch video"
                attrs {
                    onClickFunction = {
                        loader { videos ->
                            setState {
                                unwatchedVideos = videos
                            }
                        }
                    }
                }
            }
        }

        runIfLoaded {
            div {
                h3 {
                    +"Videos to watch"
                }
                videoList {
                    videos = state.unwatchedVideos
                    selectedVideo = state.currentVideo
                    onSelectVideo = { video ->
                        setState {
                            currentVideo = video
                        }
                    }
                }

                h3 {
                    +"Videos watched"
                }

                videoList {
                    videos = state.watchedVideos
                    selectedVideo = state.currentVideo
                    onSelectVideo = { video ->
                        setState {
                            currentVideo = video
                        }
                    }
                }
            }

            state.currentVideo?.let { currentVideo ->
                videoPlayer {
                    video = currentVideo
                    unwatchedVideo = currentVideo in state.unwatchedVideos
                    onWatchedButtonPressed = {
                        if (video in state.unwatchedVideos) {
                            setState {
                                unwatchedVideos -= video
                                watchedVideos += video
                            }
                        } else {
                            setState {
                                watchedVideos -= video
                                unwatchedVideos += video
                            }
                        }
                    }
                }
            }
        }
    }
}