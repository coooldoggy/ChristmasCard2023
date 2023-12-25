@file:OptIn(ExperimentalFoundationApi::class, ExperimentalFoundationApi::class)

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.onClick
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.*
import androidx.compose.ui.unit.dp
import common.BasicTextField
import org.jetbrains.skia.Data
import org.jetbrains.skia.skottie.Animation
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
fun SayHi(){
    val santa = AnimationBuilder()
    val skikoCanvas = skikoManager.getSkikoCanvas().drawSomething()
    Column(modifier = Modifier.fillMaxSize().background(Color.White)) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Canvas(modifier = Modifier.fillMaxSize()) {
//                santa.render(skikoCanvas)
            }
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            InputName(userInputName = "", onClickSend = {
                
            })
        }
    }
}


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