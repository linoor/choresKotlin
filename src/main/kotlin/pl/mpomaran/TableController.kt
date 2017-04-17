package pl.mpomaran

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * Created by linoor on 15.04.17.
 */

@CrossOrigin(origins = arrayOf("http://localhost:3000"))
@RestController
class TableController(val choreRepository: ChoreRepository,
                      val choreTaskRepository: ChoreTaskRepository,
                      val personRepository: PersonRepository,
                      val weekRepository: WeekRepository) {
    @GetMapping("/api/choreTasks/")
    fun findByName(@RequestParam("name") name: String): ResponseEntity<Any> {
        val chore = choreTaskRepository.findByChoreName(name)
        return ResponseEntity.ok(chore)
    }

    @PutMapping("/api/choreTasks/")
    fun updatePerson(@RequestParam("name") name: String,
                     @RequestParam("week_id") weekId: Long,
                     @RequestParam("chore_name") choreName: String): ResponseEntity<Any> {
        val person: Person? = personRepository.findOneByName(name)
        val week = weekRepository.findOne(weekId)
        val chore = choreRepository.findOneByName(choreName)

        if (person == null || week == null || chore == null) {
           return ResponseEntity.badRequest().build()
        }

        val choreTask: ChoreTask? = choreTaskRepository.findAll()
                .find { it.chore == chore && it.week == week }
        if (choreTask != null) {
            if (person == choreTask.person) {
                return ResponseEntity.ok("User didn't change.")
            }
            choreTask.person = person
            choreTaskRepository.save(choreTask)
            return ResponseEntity.ok("User changed.")
        }


        val newChoreTask = ChoreTask(chore, person, null, week)
        choreTaskRepository.save(newChoreTask)
        return ResponseEntity.ok("User added.")
    }
}
