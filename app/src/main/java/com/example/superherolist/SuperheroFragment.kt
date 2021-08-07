package com.example.superherolist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.superherolist.data.SuperHero
import com.example.superherolist.databinding.FragmentSuperheroBinding
import com.example.superherolist.databinding.ItemSuperheroBinding

class SuperheroFragment: Fragment(){

    private lateinit var binding : FragmentSuperheroBinding
    private val viewModel: SuperheroViewModel by viewModels()
    private lateinit var adapter : AdapterDemo


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSuperheroBinding.inflate(inflater, container, false)
        adapter = AdapterDemo(viewModel)
        binding.recyclerView.adapter = adapter

        viewModel.superHero.observe(viewLifecycleOwner){
            adapter.submitItems(it?: emptyList())
        }

        binding.createTaskButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, SuperHeroDetailFragment.newInstance(), "SuperHeroDetail")
                .addToBackStack("SuperHeroDetail")
                .commit()
        }

        viewModel.eventOpenDetail.observe(viewLifecycleOwner) {

            it ?: return@observe

            parentFragmentManager.beginTransaction()
                .replace(R.id.container, SuperHeroDetailFragment.newInstance(it.id), "SuperHeroDetail")
                .addToBackStack("SuperHeroDetail")
                .commit()
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.refresh()
    }

    companion object {

        fun newInstance(): SuperheroFragment {
            val args = Bundle()

            val fragment = SuperheroFragment()
            fragment.arguments = args
            return fragment
        }

    }

    class AdapterDemo(private val viewModel: SuperheroViewModel) : RecyclerView.Adapter<ViewHolder>() {

        private val mItem = mutableListOf<SuperHero>()

        fun submitItems(item: List<SuperHero>) {
            mItem.clear()
            mItem.addAll(item)
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemSuperheroBinding.inflate(inflater, parent, false)
            return ViewHolder(binding)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.binding.superhero = mItem[position]
            holder.binding.viewModel = viewModel
            holder.binding.executePendingBindings()

        }

        override fun getItemCount() = mItem.size

    }

    class ViewHolder (val binding: ItemSuperheroBinding) : RecyclerView.ViewHolder(binding.root)

}