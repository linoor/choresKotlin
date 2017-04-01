package pl.mpomaran

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class ChoresApplication

fun main(args: Array<String>) {
    SpringApplication.run(ChoresApplication::class.java, *args)
}
