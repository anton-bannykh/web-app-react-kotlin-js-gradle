import kotlinx.html.js.onClickFunction
import kotlin.browser.window
import react.*
import react.dom.*


external interface VideoListProps: RProps {
    var videos: List<Video>
    var selectedVideo: Video?
    var onSelectVideo: (Video) -> Unit
}

class VideoList: RComponent<VideoListProps, RState>() {
    override fun RBuilder.render() {
        val videos = props.videos
        for (video in videos) {
            p {
                key = video.id.toString()
                attrs {
                    onClickFunction = {
                        props.onSelectVideo(video)
                    }
                }
                if(video == props.selectedVideo) {
                    +"▶ "
                }
                +"${video.speaker}: ${video.title}"
            }
        }
    }
}

fun RBuilder.videoList(handler: VideoListProps.() -> Unit): ReactElement {
    return child(VideoList::class) {
        this.attrs(handler)
    }
}