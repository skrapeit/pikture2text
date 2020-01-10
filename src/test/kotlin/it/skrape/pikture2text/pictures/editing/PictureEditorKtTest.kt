package it.skrape.pikture2text.pictures.editing

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

import java.io.File

internal class PictureEditorKtTest {

    @Disabled
    @Test
    fun optimizeForOCR() {
        File("src/test/resources/examples/hallo2.png").optimizeForOCR()
    }
}
