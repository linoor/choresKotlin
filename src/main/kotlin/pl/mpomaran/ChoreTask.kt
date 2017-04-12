package pl.mpomaran

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*
import java.util.*

/**
 * Created by linoor on 01.04.17.
 */

@Entity
class ChoreTask(
        @ManyToOne
        @JoinColumn(name="choreId", referencedColumnName = "ID")
        @JsonBackReference
        var chore: Chore? = null,

        @ManyToOne
        @JoinColumn(name="personId", referencedColumnName = "ID")
        @JsonBackReference
        var person: Person? = null,

        @Temporal(TemporalType.DATE) var dateDone: Calendar? = null,

        @ManyToOne var week: Week? = null,

        @Id @GeneratedValue(strategy = GenerationType.AUTO) var id: Long = 0) {

        @JsonProperty("chore_name")
        var choreName: String = chore?.name ?: "no chore set"
}
