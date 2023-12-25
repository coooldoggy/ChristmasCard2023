package skiko

import org.jetbrains.skia.Canvas
import org.jetbrains.skia.Paint
import org.jetbrains.skia.Rect
import org.jetbrains.skia.Surface

class SkikoManager : SkikoManagerInterface {
    override fun getSkikoCanvas(): SkikoCanvas {
        // Implement Skia-related logic for Wasm
        return WasmSkikoCanvas()
    }
}

class WasmSkikoCanvas : SkikoCanvas {
    override fun drawSomething(): Canvas {
        val surface = Surface.makeRasterN32Premul(100, 100)
        val canvas = surface.canvas
        return canvas
    }
}