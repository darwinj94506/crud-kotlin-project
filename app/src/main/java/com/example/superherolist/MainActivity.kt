package com.example.superherolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.superherolist.data.SuperHero
import com.example.superherolist.databinding.ActivityMainBinding
import com.example.superherolist.databinding.ActivityRootBinding
import com.example.superherolist.databinding.FragmentMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRootBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_root)
        supportFragmentManager.beginTransaction()
            .add(R.id.container, MainFragment.newInstance(), "MainFragment")
            .commit()
    }
}

class MainFragment: Fragment() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMainBinding.inflate(inflater, container, false)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.stateLogin.observe(viewLifecycleOwner) {

            val intent = Intent(requireContext(), SuperHeroActivity::class.java)
            requireContext().startActivity(intent)

        }

        return binding.root

    }

    companion object {

        fun newInstance(): MainFragment{
            val args = Bundle()

            val fragment = MainFragment()
            fragment.arguments = args
            return fragment
        }

    }

}