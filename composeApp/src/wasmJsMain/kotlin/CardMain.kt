import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun CardMain() {
    Box(modifier = Modifier.fillMaxSize().background(Color.White)) {
        SayHi()
    }
}

@Composable
fun SayHi(){
    val lottieSanta by rememberLottieComposition(
        LottieCompositionSpec.Asset("santa.json")
    )
    Column(modifier = Modifer.fillMaxSize().background(Color.White)) {
        LottieAnimation(
            modifier = Modifier.fillMaxWidth(),
            composition = lottieSanta
        )
    }
}


@Composable
fun InputName(userInputName: String, onClickSend: (String) -> Unit) {
    var inputName by remember { mutableStateOf(userInputName) }
    BasicTextField(
        modifier = Modifier.fillMaxWith().height(60.dp).background(MaterialTheme.colors.background)
            .onKeyEvent { event ->
                if (event.type == KeyEventType.KeyDown && event.key == Key.Enter) {
                    onClickSend.invoke(inputText)
                    true
                } else {
                    false
                }
            },
        value = inputText,
        valueChangeListener = {
            inputText = it
        },
        rightContent = {
            Row(
                modifier = Modifier
                    .onClick {
                        onClickSend.invoke(inputText)
                    }
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                val backgroundColor = if (inputText.isEmpty()) Color.LightGray else Color.Yellow
                Box(
                    modifier = Modifier.clip(RoundedCornerShape(10.dp))
                        .background(color = backgroundColor).size(width = 50.dp, height = 35.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    Text("전송")
                }
            }
        },
    )
}