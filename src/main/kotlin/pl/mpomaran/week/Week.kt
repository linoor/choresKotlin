package pl.mpomaran.week

import com.fasterxml.jackson.annotation.JsonBackReference
import pl.mpomaran.choretask.ChoreTask
import java.text.SimpleDateFormat
import java.util.*
import javax.persistence.*

/**
 * Created by linoor on 12.04.17.
 */

@Entity
class Week(
        @Temporal(TemporalType.DATE) var dateFrom: Calendar? = null,
        @Temporal(TemporalType.DATE) var dateTo: Calendar? = null,

        @OneToMany
        @JsonBackReference
        var choreTasks: MutableList<ChoreTask> = mutableListOf(),

        @Id @GeneratedValue(strategy = GenerationType.AUTO) var id: Long = 0
) {
        override fun toString(): String {
            val stringFormat = SimpleDateFormat("yyyy-MMM-dd")
            val dateToStr = { cal: Calendar -> stringFormat.format(cal.time) }
            return "${dateToStr(dateFrom!!)} ------> ${dateToStr(dateTo!!)}"
        }
}