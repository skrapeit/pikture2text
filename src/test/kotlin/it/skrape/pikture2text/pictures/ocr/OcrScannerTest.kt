package it.skrape.pikture2text.pictures.ocr

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.contains
import strikt.assertions.isEqualTo
import java.io.File

internal class OcrScannerTest {

    @Test
    internal fun `can scan text from file`() {
        val scannedText = OcrScanner(File("src/test/resources/examples/hallo.png"), false).scan()
        println(scannedText)
        expectThat(scannedText).isEqualTo("Hallo")
    }

    @Test
    internal fun `can scan directly from file`() {
        val scannedText = File("src/test/resources/examples/hallo.png").toText()
        expectThat(scannedText).isEqualTo("Hallo")
    }

    @Test
    internal fun `can scan easy captcha`() {
        val scannedText = File("src/test/resources/examples/handwritten-numbers.png").toTextWithOptimization()
        expectThat(scannedText).isEqualTo("O5 221839")
    }

    @Test
    internal fun `can scan image with a lot of text and images`() {
        val scannedText = File("src/test/resources/examples/computers.png").toText()
        expectThat(scannedText).contains("A computer is a machine that can be instructed to carry out sequences of arithmetic or logical operations automatically via computer programming.")
    }
}
