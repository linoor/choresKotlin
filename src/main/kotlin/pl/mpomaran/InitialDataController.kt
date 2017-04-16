package pl.mpomaran

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * Created by linoor on 15.04.17.
 */

@CrossOrigin(origins = arrayOf("http://localhost:3000"))
@RestController
class InitialDataController (val choreRepository: ChoreRepository,
                             val choreTaskRepository: ChoreTaskRepository) {
    @GetMapping("/api/choreTasks/")
    fun findByName(@RequestParam("name") name: String) : ResponseEntity<Any> {
        val chore = choreTaskRepository.findByChoreName(name)
        return ResponseEntity.ok(chore)
    }
}
