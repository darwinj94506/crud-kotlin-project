package com.example.superherolist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.superherolist.data.SuperHero
import kotlinx.coroutines.launch
import java.util.*

class SuperheroDetailViewModel(application: Application): AndroidViewModel(application){
    private val repository = Injector.provideRepository(application)
    private var superhero: SuperHero? = null

    val superHeroName = MutableLiveData<String>()
    val publisher = MutableLiveData<String>()
    val realName = MutableLiveData<String>()
    val createdSuperhero = MutableLiveData<Boolean>()

    fun start(heroId: Int) {
        viewModelScope.launch {
            if (heroId >0) {
                superhero = repository.getDetailSuperHero(heroId) ?: return@launch
                superHeroName.value = superhero!!.superHeroName
                realName.value = superhero!!.realName
                publisher.value = superhero!!.superHeroName
            }
        }
    }

    fun createSuperhero() {
        val superheroName = superHeroName.value ?: return
        val realName = realName.value ?: return
        val publisher = publisher.value ?:return
        viewModelScope.launch {
            if (superhero != null) {
                val editSuperhero = superhero!!.copy(superHeroName = superheroName, realName = realName, publisher = publisher)
                repository.editSuperHero(editSuperhero)
                createdSuperhero.value = true
            } else {
                repository.addSuperHero(SuperHero(superHeroName = superheroName, realName = realName, publisher = publisher))
                createdSuperhero.value = true
            }
        }
    }



}