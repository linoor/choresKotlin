package pl.mpomaran.week

import com.fasterxml.jackson.annotation.JsonBackReference
import org.hibernate.annotations.Type
import org.joda.time.DateTime
import pl.mpomaran.choretask.ChoreTask
import java.text.SimpleDateFormat
import javax.persistence.*

@Entity
class Week(
        @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
        var dateFrom: DateTime? = null,

        @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
        var dateTo: DateTime? = null,

        @OneToMany
        @JsonBackReference
        var choreTasks: MutableList<ChoreTask> = mutableListOf(),

        @Id @GeneratedValue(strategy = GenerationType.AUTO) var id: Long = 0
) {
        override fun toString(): String {
            val stringFormat = SimpleDateFormat("yyyy-MMM-dd")
            return "$dateFrom ------> $dateTo"
        }
}