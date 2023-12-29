import kotlinx.browser.document
import org.w3c.dom.HTMLVideoElement

actual class MediaLoader  actual constructor() {
    
    actual fun loadWebM(filePath: String): Any {
        val videoElement = document.createElement("video") as HTMLVideoElement
        videoElement.src = filePath
        videoElement.controls = true
        document.body?.appendChild(videoElement)
        return videoElement
    }
}