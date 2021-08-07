package com.example.superherolist
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SuperHeroActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_superhero)
        supportFragmentManager.beginTransaction()
            .add(R.id.container, SuperheroFragment.newInstance(), "SuperheroFragment")
            .commit()
    }
}

