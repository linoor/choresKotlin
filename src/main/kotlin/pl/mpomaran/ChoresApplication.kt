package pl.mpomaran

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
open class ChoresApplication {
    @Bean
    open fun init(repository: PersonRepository) = CommandLineRunner {
        repository.save(Person("Misiek"))
        repository.save(Person("Oleńka"))
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(ChoresApplication::class.java, *args)
}
