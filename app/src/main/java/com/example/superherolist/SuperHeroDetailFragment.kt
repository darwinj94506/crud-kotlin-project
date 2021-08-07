package com.example.superherolist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.superherolist.databinding.FragmentDetailSuperheroBinding
import java.util.*

class SuperHeroDetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailSuperheroBinding
    private val viewModel: SuperheroDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentDetailSuperheroBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.createdSuperhero.observe(viewLifecycleOwner) {
            parentFragmentManager.popBackStack()
        }

        val superheroId = arguments?.getInt("superhero_id") ?: -1
        viewModel.start(superheroId)
        return binding.root
    }

    companion object {
        fun newInstance(superheroId: Int? = null): SuperHeroDetailFragment {
            val args = bundleOf("superhero_id" to superheroId)

            val fragment = SuperHeroDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }

}