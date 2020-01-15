package it.skrape.pikture2text.api

import it.skrape.expect
import it.skrape.matchers.`to be`
import it.skrape.matchers.toBePresent
import it.skrape.selects.html5.form
import it.skrape.selects.html5.h1
import it.skrape.selects.html5.input
import it.skrape.selects.html5.title
import it.skrape.skrape
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.web.server.LocalServerPort

@SpringBootTest(webEnvironment = RANDOM_PORT)
internal class UploadControllerIT(
        @LocalServerPort val serverPort: Int
) {

    @Test
    internal fun `can access landing page`() {
        skrape {
            url = urlBuilder { port = serverPort }

            expect {
                statusCode `to be` 200

                htmlDocument {
                    title { findFirst { text } `to be` "Pikture 2 Text" }

                    h1 { findFirst { text } } `to be` "Picture to Text Converter"

                    form { findFirst { attribute("action") `to be` "/upload" } }

                    input { withAttribute = "type" to "file"
                        findFirst {
                            toBePresent
                        }
                    }

                    input { withAttribute = "type" to "submit"
                        findFirst {
                            toBePresent
                        }
                    }
                }
            }
        }
    }
}
