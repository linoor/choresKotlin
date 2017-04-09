package pl.mpomaran

import javax.persistence.*
import javax.validation.constraints.NotNull

/**
 * Created by linoor on 01.04.17.
 */

@Entity
class Person(@NotNull var name: String = "",
             @OneToMany(mappedBy = "person") var choreTasks: List<ChoreTask> = listOf(),
             @Id @GeneratedValue(strategy = GenerationType.AUTO) var id: Long = 0) {

    override fun toString(): String = name
}
