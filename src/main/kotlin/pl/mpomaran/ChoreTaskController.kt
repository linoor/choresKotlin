package pl.mpomaran

import org.springframework.context.annotation.Bean
import org.springframework.hateoas.Link
import org.springframework.hateoas.Resource
import org.springframework.hateoas.ResourceProcessor
import org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.jvm.java


/**
 * Created by linoor on 09.04.17.
 */

@RestController
class ChoreTaskController (val choreTaskRepository: ChoreTaskRepository) {
    @GetMapping("/api/choreTasks/byweek")
    fun getByWeek() : ResponseEntity<Any> {
        val choreTasks = choreTaskRepository.findAll()
        return ResponseEntity.ok(choreTasks)
    }

    @Bean
    fun choreTaskProcessor(): ResourceProcessor<Resource<ChoreTask>> {
        return ResourceProcessor { resource ->
            val link = Link("http://localhost:8080/api/choreTasks", "byweek")
            resource.add(link)
            resource
        }
    }
}

