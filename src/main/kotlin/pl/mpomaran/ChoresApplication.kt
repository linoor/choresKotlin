package pl.mpomaran

import org.h2.server.web.WebServlet
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import java.util.*
import org.springframework.boot.web.servlet.ServletRegistrationBean
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import pl.mpomaran.chore.Chore
import pl.mpomaran.chore.ChoreRepository
import pl.mpomaran.choretask.ChoreTask
import pl.mpomaran.choretask.ChoreTaskRepository
import pl.mpomaran.person.Person
import pl.mpomaran.person.PersonRepository
import pl.mpomaran.week.Week
import pl.mpomaran.week.WeekRepository


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
    fun corsConfigurer(): WebMvcConfigurer {
        return object : WebMvcConfigurerAdapter() {
            override fun addCorsMappings(registry: CorsRegistry?) {
                registry!!.addMapping("/api/**")
            }
        }
    }

    @Bean
    open fun init(personRepository: PersonRepository,
                  choreRepository: ChoreRepository,
                  choreTaskRepository: ChoreTaskRepository,
                  weekRepository: WeekRepository) = CommandLineRunner {
        val misiek = Person("Misiek")
        val olenka = Person("Oleńka")
        personRepository.save(misiek)
        personRepository.save(olenka)

        val cleaningKitchen = Chore("cleaning kitchen")
        val prepareFood = Chore("prepare food")
        val `clean the floor` = Chore("clean the floor")
        val `clean the toilets` = Chore("clean the toilets")
        val `clean the shower` = Chore("clean the shower")
        val `clean the sink in the bathroom` = Chore("clean the sink in the bathroom")
        choreRepository.save(cleaningKitchen)
        choreRepository.save(prepareFood)
        choreRepository.save(`clean the floor`)
        choreRepository.save(`clean the toilets`)
        choreRepository.save(`clean the shower`)
        choreRepository.save(`clean the sink in the bathroom`)

        var start = Calendar.getInstance()
        start.set(2017, 3, 13)
        var end = Calendar.getInstance()
        start.set(2017, 3, 20)

        val week1 = Week(start, end)
        weekRepository.save(week1)

        val doneDate = Calendar.getInstance()
        doneDate.set(2017, 4, 10)
        val exampleTask: ChoreTask = ChoreTask(cleaningKitchen, misiek, doneDate, week1)
        val secondTask: ChoreTask = ChoreTask(prepareFood, misiek, null, week1)
        choreTaskRepository.save(exampleTask)
        choreTaskRepository.save(secondTask)
        week1.choreTasks.add(exampleTask)
        week1.choreTasks.add(secondTask)
        weekRepository.save(week1)

        start = Calendar.getInstance()
        start.set(2017, 3, 21)
        end = Calendar.getInstance()
        start.set(2017, 3, 26)

        val week2 = Week(start, end)
        weekRepository.save(week2)

        val otherTask: ChoreTask = ChoreTask(prepareFood, olenka, null, week2)
        choreTaskRepository.save(otherTask)
        week2.choreTasks.add(otherTask)
        weekRepository.save(week2)
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(ChoresApplication::class.java, *args)
}
