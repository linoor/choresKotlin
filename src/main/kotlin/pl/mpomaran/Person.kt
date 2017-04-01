package pl.mpomaran

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

/**
 * Created by linoor on 01.04.17.
 */

@Entity
class Person(var name: String = "", @Id @GeneratedValue(strategy = GenerationType.AUTO) var id: Long = 0)