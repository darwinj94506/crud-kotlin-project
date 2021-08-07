package com.example.superherolist.data

import java.util.*
import java.util.Random

data class SuperHero(
    val id:Int = (0..1000).random(),
    val superHeroName:String,
    val publisher:String,
    val realName:String,
    val image:String = "https://cursokotlin.com/wp-content/uploads/2017/07/wonder_woman.jpg"
)
