package file

import MediaLoader

object FileUtils {
    fun getPath(directory: String, fileName: String): String {
        return "$directory/$fileName"
    }
    
    fun loadWebMFile(path: String) {
        val mediaLoader = MediaLoader()
        mediaLoader.loadWebM(path)
    }
}
