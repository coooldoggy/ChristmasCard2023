@file:OptIn(ExperimentalFoundationApi::class, ExperimentalFoundationApi::class)

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.*
import androidx.compose.ui.unit.dp
import common.BasicTextField
import file.getPath
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

import skiko.SkikoManager

private val skikoManager by lazy {
    SkikoManager()
}

@Composable
fun CardMain() {
    Box(modifier = Modifier.fillMaxSize().background(Color.White)) {
        SayHi()
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun SayHi() {
    val santa = getPath(directory = "resources", fileName = "santa.webm")
    val paint = painterResource("santa.webm")
    println("!!!!!!!!!! $paint")
    Column(modifier = Modifier.fillMaxWidth().background(Color.White).padding(16.dp)) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Box(modifier = Modifier.size(500.dp).background(Color.Red)){
                val mediaLoader = MediaLoader.provideMediaLoader()
                mediaLoader.loadWebM("https://github.com/coooldoggy/ChristmasCard2023/blob/main/images/giftbox.mp4")
            }
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            InputName(userInputName = "", onClickSend = {

            })
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun InputName(userInputName: String, onClickSend: (String) -> Unit) {
    var inputName by remember { mutableStateOf(userInputName) }
    BasicTextField(
        modifier = Modifier.fillMaxWidth().height(60.dp)
            .onKeyEvent { event ->
                if (event.type == KeyEventType.KeyDown && event.key == Key.Enter) {
                    onClickSend.invoke(inputName)
                    true
                } else {
                    false
                }
            },
        value = inputName,
        valueChangeListener = {
            inputName = it
        },
        rightContent = {
            Row(
                modifier = Modifier
                    .onClick {
                        onClickSend.invoke(inputName)
                    }
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                val backgroundColor = if (inputName.isEmpty()) Color.LightGray else Color.Yellow
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