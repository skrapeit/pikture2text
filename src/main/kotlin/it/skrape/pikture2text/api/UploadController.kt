package it.skrape.pikture2text.api

import it.skrape.pikture2text.pictures.ocr.toText
import kotlinx.html.*
import kotlinx.html.dom.createHTMLDocument
import kotlinx.html.dom.serialize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.FileOutputStream


@RestController
class UploadController {

    @GetMapping("/")
    fun landingPage() = renderHtml()

    @PostMapping("/upload")
    fun upload(@RequestParam("file") multipartFile: MultipartFile): String {

        val path = createTempDir().absolutePath

        val file = File("$path${multipartFile.originalFilename}").apply {
            createNewFile()
        }

        FileOutputStream(file).use {
            it.write(multipartFile.bytes)
        }

        return file.toText()
    }

    fun renderHtml(): String =
            createHTMLDocument().html {

                head {
                    title { +"Pikture 2 Text" }
                    meta(charset = "utf-8")
                }

                body {
                    h1 { +"Picture to Text Converter" }

                    form {
                        method = FormMethod.post
                        action = "/upload"
                        encType = FormEncType.multipartFormData

                        input {
                            type = InputType.file
                            name = "file"
                        }

                        input {
                            type = InputType.submit
                            value = "Submit"
                        }
                    }
                }
            }.serialize(true)
}
