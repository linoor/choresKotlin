package pl.mpomaran

import com.fasterxml.jackson.annotation.JsonManagedReference
import javax.persistence.*
import javax.validation.constraints.NotNull

/**
 * Created by linoor on 01.04.17.
 */

@Entity
class Chore(@NotNull var name: String = "",
            var description: String = "",

            @OneToMany(mappedBy = "chore")
            @JsonManagedReference
            var choreTasks: List<ChoreTask> = listOf(),

            @Id @GeneratedValue(strategy = GenerationType.AUTO) var id: Long = 0
            ) {

    override fun toString(): String = name
}
