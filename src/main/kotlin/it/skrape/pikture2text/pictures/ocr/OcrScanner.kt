package it.skrape.pikture2text.pictures.ocr

import it.skrape.pikture2text.pictures.editing.optimizeForOCR
import net.sourceforge.tess4j.Tesseract
import org.springframework.beans.factory.annotation.Value
import java.io.File

class OcrScanner(
        private val file: File,
        private val formatted: Boolean
) {

    @Value("\${tessdata.dir:src/main/resources/tessdata}")
    private val tessdata: String? = null

    fun scan(): String {

        return Tesseract().apply {
            setDatapath(tessdata)
            setLanguage("deu+deu_frak+eng")
            setPageSegMode(1)
            setHocr(formatted)
        }.doOCR(file).trim()
    }
}

fun File.toText() = OcrScanner(this, true).scan()
fun File.toTextWithOptimization() = OcrScanner(this.optimizeForOCR(), true).scan()
