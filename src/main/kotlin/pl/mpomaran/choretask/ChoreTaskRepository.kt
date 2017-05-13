package pl.mpomaran.choretask

import org.springframework.data.repository.CrudRepository
import pl.mpomaran.person.Person
import pl.mpomaran.chore.Chore

/**
 * Created by linoor on 01.04.17.
 */

interface ChoreTaskRepository : CrudRepository<ChoreTask, Long> {
    fun findByChoreName(name: String): List<ChoreTask>
    fun findByChoreAndPerson(chore: Chore, person: Person): ChoreTask?
}