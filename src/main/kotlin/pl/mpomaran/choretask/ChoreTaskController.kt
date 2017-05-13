package pl.mpomaran.choretask

import org.springframework.context.annotation.Bean
import org.springframework.data.rest.webmvc.RepositoryRestController
import org.springframework.hateoas.Link
import org.springframework.hateoas.Resource
import org.springframework.hateoas.ResourceProcessor
import org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pl.mpomaran.person.Person
import pl.mpomaran.person.PersonRepository
import pl.mpomaran.week.WeekRepository
import pl.mpomaran.chore.ChoreRepository
import javax.websocket.server.PathParam


/**
 * Created by linoor on 09.04.17.
 */

@CrossOrigin(origins = arrayOf("http://localhost:3000"))
@RestController
class ChoreTaskController (val choreTaskRepository: ChoreTaskRepository,
                           val choreRepository: ChoreRepository,
                           val weekRepository: WeekRepository,
                           val personRepository: PersonRepository) {
    @GetMapping("/api/choreTasks/bychore")
    fun getByChore() : ResponseEntity<Any> {
        val choreTasks = choreTaskRepository.findAll()
        val grouped = choreTasks.groupBy { it.chore }
        return ResponseEntity.ok(grouped)
    }

    @GetMapping("/api/choreTasks/week")
    fun getByWeek() : ResponseEntity<Any> {
        val choreTasks = choreTaskRepository.findAll()
        val grouped = choreTasks.groupBy { it.week }
        return ResponseEntity.ok(grouped)
    }

    @PutMapping("/api/choreTasks/{id}/person")
    fun changePerson(@PathVariable("id") choreTaskId: Long,
                     @RequestParam("new_person_name") newPersonName: String) : ResponseEntity<Any> {
        val choreTask: ChoreTask? = choreTaskRepository.findOne(choreTaskId)
        if (choreTask != null) {

            when (newPersonName) {
                "None" -> {
                    choreTask.person = null
                    choreTaskRepository.save(choreTask)
                }
                else -> {
                    val person: Person? = personRepository.findOneByName(newPersonName)
                    if (person != null) {
                        choreTask.person = person
                        choreTaskRepository.save(choreTask)

                        return ResponseEntity.ok("The person has been changed")
                    }
                }
            }

        }

        return ResponseEntity.badRequest().build()
    }

}

