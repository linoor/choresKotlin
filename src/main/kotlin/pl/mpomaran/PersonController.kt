package pl.mpomaran

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

/**
 * Created by linoor on 01.04.17.
 */

@RestController
class PersonController (val repository: PersonRepository) {
    @GetMapping("/")
    fun findAll() = repository.findAll()

    @GetMapping("/{name}")
    fun findByName(@PathVariable name: String) = repository.findByName(name)
}
