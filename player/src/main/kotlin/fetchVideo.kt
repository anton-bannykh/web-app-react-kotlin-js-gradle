import kotlinx.browser.window
import kotlinx.coroutines.*

suspend fun fetchVideo(id: Int): Video =
    window.fetch("https://my-json-server.typicode.com/kotlin-hands-on/kotlinconf-json/videos/$id")
        .await()
        .json()
        .await()
        .unsafeCast<Video>()

suspend fun fetchVideos(): List<Video> = coroutineScope {
    (1..25).map { id ->
        async {
            fetchVideo(id)
        }
    }.awaitAll()
}

fun loadVideo(andThen: (List<Video>) -> Unit) {
    GlobalScope.launch {
        andThen(fetchVideos())
    }
}