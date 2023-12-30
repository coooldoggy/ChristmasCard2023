import androidx.compose.runtime.Composable

//public expect class MediaLoader {
//    fun loadWebM(filePath: String): Any
//
//    fun provideMediaLoader(): MediaLoader
//}


expect class MediaLoader {
    @Composable
    fun loadWebM(filePath: String): Any

    companion object {
        @Composable
        fun provideMediaLoader(): MediaLoader
    }
}