package pl.mpomaran.person

import org.springframework.data.repository.CrudRepository

/**
 * Created by linoor on 01.04.17.
 */

interface PersonRepository : CrudRepository<Person, Long> {
    fun findByName(name: String): List<Person>
    fun findOneByName(name: String): Person?
}
