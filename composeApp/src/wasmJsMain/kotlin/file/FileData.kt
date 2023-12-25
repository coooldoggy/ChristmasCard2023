package file
import kotlinx.serialization.Serializable

@Serializable
data class FileData(
    val anInteger: Int?,
    val jsonString: String?
)

external fun getData(filePath: String): String
