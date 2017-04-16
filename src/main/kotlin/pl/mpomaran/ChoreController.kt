package pl.mpomaran

import org.springframework.data.rest.webmvc.RepositoryRestController
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Created by linoor on 15.04.17.
 */

@CrossOrigin(origins = arrayOf("http://localhost:3000"))
@RestController
class ChoreController (val choreRepository: ChoreRepository) {
    @GetMapping("/api/chores/all")
    fun getAll() : ResponseEntity<Any> {
        val chores = choreRepository.findAll()
        val withChoreTasks = chores.map { it.choreTasks }
        return ResponseEntity.ok(withChoreTasks)
    }
}
