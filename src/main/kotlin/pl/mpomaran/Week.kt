package pl.mpomaran

import com.fasterxml.jackson.annotation.JsonBackReference
import java.util.*
import javax.persistence.*

/**
 * Created by linoor on 12.04.17.
 */

@Entity
class Week(
        @Temporal(TemporalType.DATE) var dateFrom: Calendar = Calendar.getInstance(),
        @Temporal(TemporalType.DATE) var dateTo: Calendar = Calendar.getInstance(),

        @OneToMany
        @JsonBackReference
        var choreTasks: MutableList<ChoreTask> = mutableListOf(),

        @Id @GeneratedValue(strategy = GenerationType.AUTO) var id: Long = 0
)