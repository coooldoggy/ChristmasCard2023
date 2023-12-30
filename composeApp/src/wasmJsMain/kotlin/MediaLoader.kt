import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.browser.document
import org.w3c.dom.HTMLVideoElement
actual class MediaLoader {

    @Composable
    actual fun loadWebM(filePath: String): Any {
        return Box(
            modifier = Modifier
                .size(500.dp)
                .background(Color.White)
                .padding(8.dp)
        ) {
            //FIXME 객체 설정이 잘못 됨 
            val videoElement = document.createElement("video") as HTMLVideoElement
            videoElement.src = filePath
            videoElement.controls = true
            document.body?.appendChild(videoElement)
        }
    }

    actual companion object {
        @Composable
        actual fun provideMediaLoader(): MediaLoader {
            return MediaLoader()
        }
    }
}