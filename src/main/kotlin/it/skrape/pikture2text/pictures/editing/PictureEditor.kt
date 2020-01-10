package it.skrape.pikture2text.pictures.editing

import com.github.geko444.im4java.core.ConvertCmd
import com.github.geko444.im4java.core.IMOperation
import java.io.File

fun File.optimizeForOCR(): File {
    val op = IMOperation().apply {
        addImage(absolutePath)
        // resize(2000)
        whiteThreshold(25.0, true)
        blackThreshold(10.0, true)
        alpha("Opaque")
        despeckle()
        addImage(absolutePath)
    }

    ConvertCmd().run(op)

    return File(absolutePath)
}
