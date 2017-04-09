package pl.mpomaran

import org.h2.server.web.WebServlet
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import java.util.*
import org.springframework.boot.web.servlet.ServletRegistrationBean


@SpringBootApplication
open class ChoresApplication {

    @Bean
    fun h2servletRegistration(): ServletRegistrationBean {
        val registration = ServletRegistrationBean(WebServlet())
        registration.addUrlMappings("/console/*")
        registration.addInitParameter("webAllowOthers", "true")
        return registration
    }

    @Bean
    open fun init(personRepository: PersonRepository,
                  choreRepository: ChoreRepository,
                  choreTaskRepository: ChoreTaskRepository) = CommandLineRunner {
        val misiek = Person("Misiek")
        val olenka = Person("Oleńka")
        personRepository.save(misiek)
        personRepository.save(olenka)

        val cleaningKitchen = Chore("cleaning kitchen")
        choreRepository.save(cleaningKitchen)

        val start = Calendar.getInstance()
        start.set(2017, 3, 13)
        val end = Calendar.getInstance()
        start.set(2017, 3, 20)

        val exampleTask : ChoreTask = ChoreTask(cleaningKitchen, misiek, null, start, end)
        choreTaskRepository.save(exampleTask)

    }
}

fun main(args: Array<String>) {
    SpringApplication.run(ChoresApplication::class.java, *args)
}
