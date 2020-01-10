package it.skrape.pikture2text.pictures.ocr

import it.skrape.pikture2text.pictures.editing.optimizeForOCR
import net.sourceforge.tess4j.Tesseract
import java.io.File

class OcrScanner(
        private val file: File
) {
    fun scan() = Tesseract().apply {
        setDatapath("src/main/resources/tessdata")
        setPageSegMode(1)
    }.doOCR(file).trim()
}

fun File.toText() = OcrScanner(this).scan()
fun File.toTextWithOptimization() = OcrScanner(this.optimizeForOCR()).scan()
