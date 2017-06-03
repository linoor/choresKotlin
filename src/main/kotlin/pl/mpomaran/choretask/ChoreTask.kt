package pl.mpomaran.choretask

import com.fasterxml.jackson.annotation.JsonManagedReference
import pl.mpomaran.person.Person
import pl.mpomaran.week.Week
import pl.mpomaran.chore.Chore
import javax.persistence.*
import org.hibernate.annotations.Type
import org.joda.time.DateTime
import java.util.*

/**
 * Created by linoor on 01.04.17.
 */

@Entity
class ChoreTask(
        @ManyToOne
        @JoinColumn(name="choreId", referencedColumnName = "ID")
        @JsonManagedReference
        var chore: Chore? = null,

        @ManyToOne
        @JoinColumn(name="personId", referencedColumnName = "ID")
        @JsonManagedReference
        var person: Person? = null,

        @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
        var doneDate: DateTime? = null,

        @ManyToOne
        @JsonManagedReference
        var week: Week? = null,

        @Id @GeneratedValue(strategy = GenerationType.AUTO) var id: Long = 0) {
}
