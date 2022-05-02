package com.example.catknowledgebase.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.catknowledgebase.R
import com.example.catknowledgebase.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: FactViewModel by viewModels()
    private lateinit var factsAdapter:FactsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.catFact.observe(
            this
        ) { catFact ->
            factsAdapter.addFact(catFact)
            if(catFact.size==1){
                binding.rvCats.layoutManager?.scrollToPosition(0)
            }
        }
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpAdapter()
        binding.toolbar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.add_item-> viewModel.loadFacts()
            }
            return@setOnMenuItemClickListener true
        }
    }

    private fun setUpAdapter() {
        factsAdapter= FactsAdapter(arrayListOf())
        factsAdapter.onItemLongPress={
            viewModel.deleteFact(it)
        }
        with(binding.rvCats){
            layoutManager=LinearLayoutManager(this@MainActivity,RecyclerView.VERTICAL,false)
            adapter=factsAdapter

        }
    }

}