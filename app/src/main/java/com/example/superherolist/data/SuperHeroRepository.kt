package com.example.superherolist.data

import java.util.*

interface SuperHeroRepository{

    suspend fun getSuperHeros():List<SuperHero>
    suspend fun addSuperHero(superHero: SuperHero)
    suspend fun editSuperHero(superHero: SuperHero)
    suspend fun deleteSuperHero(superHero: SuperHero)
    suspend fun getDetailSuperHero(id: Int): SuperHero
}
