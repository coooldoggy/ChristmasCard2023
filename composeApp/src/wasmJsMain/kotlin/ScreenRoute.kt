@Parcelize
sealed class ScreenRoute: Parcelable {
    data object CardMain : ScreenRoute()
    data object CardPage: ScreenRoute()
}