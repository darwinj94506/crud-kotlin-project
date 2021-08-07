package com.example.superherolist
import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val userName = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    val stateLogin = MutableLiveData<Boolean>()

    init {
        val preferences = getApplication<Application>().getSharedPreferences("APP", Context.MODE_PRIVATE)
        userName.value = preferences.getString("user", "Usuario")
        password.value = preferences.getString("pass", "abcdef")
    }

    fun authenticate() {

        val user = userName.value ?: return
        val pass = password.value ?: return

        stateLogin.value = user == "Usuario" && pass == "abcdef"

        if (stateLogin.value == true) {
            saveForm(user, pass)
        }

    }

    private fun saveForm(user: String, pass: String) {
        val preferences =
            getApplication<Application>().getSharedPreferences("APP", Context.MODE_PRIVATE)
        preferences.edit()
            .putString("user", user)
            .putString("pass", pass)
            .apply()
    }
}