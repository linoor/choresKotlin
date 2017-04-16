package pl.mpomaran

import org.springframework.data.repository.CrudRepository

/**
 * Created by linoor on 01.04.17.
 */

interface ChoreTaskRepository : CrudRepository<ChoreTask, Long> {
    fun findByChore(chore: Chore): List<ChoreTask>
}