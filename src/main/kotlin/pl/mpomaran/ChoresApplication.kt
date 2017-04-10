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
        val prepareFood = Chore("prepare food")
        choreRepository.save(cleaningKitchen)
        choreRepository.save(prepareFood)

        var start = Calendar.getInstance()
        start.set(2017, 3, 13)
        var end = Calendar.getInstance()
        start.set(2017, 3, 20)

        val exampleTask: ChoreTask = ChoreTask(cleaningKitchen, misiek, null, start, end)
        val secondTask: ChoreTask = ChoreTask(prepareFood, misiek, null, start, end)
        choreTaskRepository.save(exampleTask)
        choreTaskRepository.save(secondTask)


        start = Calendar.getInstance()
        start.set(2017, 3, 21)
        end = Calendar.getInstance()
        start.set(2017, 3, 26)
        val otherTask : ChoreTask = ChoreTask(prepareFood, olenka, null, start, end)
        choreTaskRepository.save(otherTask)
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(ChoresApplication::class.java, *args)
}
