package com.example.superherolist

import android.content.Context
import com.example.superherolist.data.LocalSuperHeroRepository
import com.example.superherolist.data.SuperHeroRepository

object Injector {

    fun provideRepository(context: Context): SuperHeroRepository {
        return LocalSuperHeroRepository
    }
}
