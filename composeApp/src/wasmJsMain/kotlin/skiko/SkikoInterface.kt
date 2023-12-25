package skiko

import org.jetbrains.skia.Canvas

interface SkikoManagerInterface {
    fun getSkikoCanvas(): SkikoCanvas
}

interface SkikoCanvas {
    fun drawSomething(): Canvas
}
