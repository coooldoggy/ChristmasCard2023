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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.key.*
import androidx.compose.ui.unit.dp
import common.BasicTextField
import file.FileUtils
import file.FileUtils.loadWebMFile
import file.JsonStrings
import file.getData

import org.jetbrains.skia.skottie.AnimationBuilder
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

@Composable
fun SayHi() {
    val santa = "https://github.com/coooldoggy/ChristmasCard2023/blob/main/images/santa.webm"
    Column(modifier = Modifier.fillMaxWidth().background(Color.White)) {
        Row(modifier = Modifier.fillMaxWidth()) {
//            Canvas(modifier = Modifier.fillMaxSize()) {
//                AnimationBuilder().buildFromString(santaJsonString)
//                    .render(skikoManager.getSkikoCanvas().drawSomething())
//            }
            Surface(modifier = Modifier.size(100.dp)) {
                loadWebMFile(santa)   
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