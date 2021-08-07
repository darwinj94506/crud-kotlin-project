package com.example.superherolist.data

import java.util.*

object LocalSuperHeroRepository : SuperHeroRepository {
    private val dataSource= mutableListOf<SuperHero>(
        SuperHero(superHeroName = "Spiderman", publisher = "Marvel", realName = "Peter Parker", image = "https://cursokotlin.com/wp-content/uploads/2017/07/spiderman.jpg"),
        SuperHero(superHeroName="Daredevil", publisher ="Marvel",  realName ="Matthew Michael Murdock",image =  "https://cursokotlin.com/wp-content/uploads/2017/07/daredevil.jpg"),
        SuperHero(superHeroName="Wolverine", publisher ="Marvel",  realName ="James Howlett", image = "https://cursokotlin.com/wp-content/uploads/2017/07/logan.jpeg"),
        SuperHero(superHeroName="Batman", publisher ="DC",  realName ="Bruce Wayne", image = "https://cursokotlin.com/wp-content/uploads/2017/07/batman.jpg"),
        SuperHero(superHeroName="Thor", publisher ="Marvel",  realName ="Thor Odinson", image = "https://cursokotlin.com/wp-content/uploads/2017/07/thor.jpg"),
        SuperHero(superHeroName="Flash", publisher ="DC",  realName ="Jay Garrick", image = "https://cursokotlin.com/wp-content/uploads/2017/07/flash.png"),
        SuperHero(superHeroName="Green Lantern", publisher ="DC",  realName ="Alan Scott", image = "https://cursokotlin.com/wp-content/uploads/2017/07/green-lantern.jpg"),
        SuperHero(superHeroName="Wonder Woman", publisher ="DC",  realName ="Princess Diana", image = "https://cursokotlin.com/wp-content/uploads/2017/07/wonder_woman.jpg")
    )

    override suspend fun getSuperHeros(): List<SuperHero> {
       return dataSource.toList()
    }

    override suspend fun addSuperHero(superHero: SuperHero) {
        dataSource.add(superHero)
    }

    override suspend fun editSuperHero(superHero: SuperHero) {
        dataSource.indexOfFirst { sH-> sH.id == superHero.id }
            .also { index-> dataSource[index]= superHero }
    }

    override suspend fun deleteSuperHero(superHero: SuperHero) {
        dataSource.remove(superHero)
    }

    override suspend fun getDetailSuperHero(id: Int): SuperHero {
        val index = dataSource.indexOfFirst { sH-> sH.id == id }
        return dataSource[index]
    }
}
