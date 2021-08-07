package com.example.superherolist
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.superherolist.data.SuperHero
import kotlinx.coroutines.launch

class SuperheroViewModel(application: Application):  AndroidViewModel(application){
    private val repository = Injector.provideRepository(application)
    private val mSuperHero = MutableLiveData<List<SuperHero>>()
    val superHero: LiveData<List<SuperHero>>
        get() = mSuperHero

    private val mEventOpenDetail = SingleLineEvent<SuperHero>()
    val eventOpenDetail: LiveData<SuperHero>
        get() = mEventOpenDetail

    init {
        viewModelScope.launch {
            mSuperHero.value = repository.getSuperHeros()
        }
    }

    fun openDetailSuperhero(superhero: SuperHero) {
        mEventOpenDetail.value = superhero
    }

    fun deleteSuperhero(superhero: SuperHero) {
        viewModelScope.launch {
            repository.deleteSuperHero(superhero)
            mSuperHero.value = repository.getSuperHeros()
        }
    }

    fun addSuperhero(superhero: SuperHero) {
        viewModelScope.launch {
            repository.addSuperHero(superhero)
            mSuperHero.value = repository.getSuperHeros()
        }
    }

    fun editSuperhero(superhero: SuperHero) {
        viewModelScope.launch {
            repository.editSuperHero(superhero)
            mSuperHero.value = repository.getSuperHeros()
        }
    }


    fun refresh() {
        viewModelScope.launch {
            val superHeros = repository.getSuperHeros()
            mSuperHero.value = superHeros
        }
    }

}