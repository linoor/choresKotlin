package pl.mpomaran.week

import org.joda.time.DateTime
import org.junit.Test

import org.junit.Assert.*

/**
 * Created by linoor on 03.06.17.
 */
class WeekTest {
    @Test
    fun `test date to string`() {
        val dateStart = DateTime(2017, 1, 15, 0, 0, 0, 0)
        val dateEnd = dateStart.plusDays(3)
        val week = Week(dateStart, dateEnd, mutableListOf())
        assertEquals(week.toString(), "2017-01-15T00:00:00.000+01:00 ------> 2017-01-18T00:00:00.000+01:00")
    }

}