package pl.mpomaran

import org.springframework.data.repository.CrudRepository
import org.springframework.web.bind.annotation.CrossOrigin

/**
 * Created by linoor on 01.04.17.
 */

@CrossOrigin(origins = arrayOf("http://localhost:3000"))
interface ChoreRepository : CrudRepository<Chore, String> {
    fun findByName(name: String): List<Chore>
    fun findOneByName(name: String): Chore?
}
