package it.skrape.pikture2text.ocr

import net.sourceforge.tess4j.Tesseract
import net.sourceforge.tess4j.TesseractException
import java.io.File

class OcrScanner(
        private val file: File
) {
    fun scan(): String {
        val tesseract = Tesseract()
        try {
            tesseract.setDatapath("src/main/resources/tessdata")
            tesseract.setPageSegMode(1)
            return tesseract.doOCR(file).trim()
        } catch (e: TesseractException) {
            e.printStackTrace()
        }
        return ""
    }
}

fun File.toText() = OcrScanner(this).scan()
