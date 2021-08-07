package com.example.superherolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.superherolist.data.SuperHero
import com.example.superherolist.databinding.ItemSuperheroBinding
import com.squareup.picasso.Picasso

class HeroAdapter(var superhero:List<SuperHero>):RecyclerView.Adapter<HeroAdapter.HeroHolder> (){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroHolder {
       val binding = ItemSuperheroBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HeroHolder(binding)
    }

    override fun onBindViewHolder(holder: HeroHolder, position: Int) {
        holder.reder(superhero[position])
    }

    override fun getItemCount(): Int = superhero.size

    class HeroHolder(private val binding: ItemSuperheroBinding): RecyclerView.ViewHolder(binding.root){
        fun reder(superhero: SuperHero){
            /*
            binding.tvSuperHeroName.text = superhero.superHeroName
            binding.tvRealName.text = superhero.realName
            binding.tvPublisher.text = superhero.publisher
            Picasso.get().load(superhero.image).into(binding.ivHero)

             */
            binding.root.setOnClickListener{
                Toast.makeText(binding.root.context,"Has seleccionado: ${superhero.realName}",Toast.LENGTH_SHORT).show()
            }
        }

    }

}