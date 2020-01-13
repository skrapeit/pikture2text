package it.skrape.pikture2text

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class Pikture2textApplication

fun main(args: Array<String>) {
	runApplication<Pikture2textApplication>(*args)

	// val app = SpringApplication(Pikture2textApplication::class.java)
	// app.setDefaultProperties(mapOf("server.port" to "8083"))
	// app.run(*args)
}
