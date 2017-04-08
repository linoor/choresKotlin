package pl.mpomaran.utils

import pl.mpomaran.Person
import pl.mpomaran.Chore
import kotlin.repeat

/**
 * Created by linoor on 08.04.17.
 */

class Randomize(val people: List<Person>, val chores: List<Chore>) {

    fun getChoreForPerson(chores: List<Chore>): Pair<Person, Chore> {
        val randomPerson = people[(Math.random() * people.size).toInt()]
        val randomChore = chores[(Math.random() * chores.size).toInt()]

        return Pair(randomPerson, randomChore)
    }

    fun randomize() : Map<Person, Set<Chore>> {
        val choresLeft = chores.toMutableSet()
        val chosen = people.associate { it -> Pair(it, mutableSetOf<Chore>()) }

        repeat(chores.size, {
            val (person, chore) = getChoreForPerson(choresLeft.toList())
            chosen[person]!!.add(chore)
            choresLeft.remove(chore)
        })

        val isFair = chosen.values.map { it.size }.toSet().size == 1
        if (isFair) return chosen
        else return randomize()
    }

}

fun main(args: Array<String>) {
    val chores: List<Chore> = listOf(
            Chore("cleaning kitchen"),
            Chore("cleaning the floor"),
            Chore("cleaning the toilet"),
            Chore("cleaning the shower"),
            Chore("cleaning the sink in the bathroom"),
            Chore("prepare some food")
    )

    val misiek = Person("Misiek")
    val olenka = Person("Olenka")

    val randomizer = Randomize(listOf(misiek, olenka), chores)

    val result = randomizer.randomize()

    result.keys.map { person ->
        println("$person's tasks for this week:")

        result[person]?.map { println("\t$it") }
    }
}
