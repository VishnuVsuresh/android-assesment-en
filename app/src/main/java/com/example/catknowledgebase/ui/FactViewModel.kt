package com.example.catknowledgebase.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catknowledgebase.data.local.FactRepository
import com.example.catknowledgebase.model.FactItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class FactViewModel @Inject constructor(private val repository: FactRepository) : ViewModel() {
    private val _catFact = MutableLiveData<List<FactItem>>()
    val catFact: LiveData<List<FactItem>> = _catFact


    init {
        loadFromDB()
    }

    fun loadFromDB() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getFacts().let {
                withContext(Dispatchers.Main) {
                    _catFact.value = it
                }
            }
        }
    }

    fun loadFacts() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getCatFact()?.collect {
                if (it.isSuccessful && it.body() != null) {
                    it.body()?.apply {
                        this.catUrl = "https://cataas.com/cat"
                    }

                    repository.addFact(it.body()!!)
                    withContext(Dispatchers.Main) {
                        _catFact.value = arrayListOf(it.body()!!)
                    }

                } else {
                    withContext(Dispatchers.Main) {
                        _catFact.value = arrayListOf()
                        Log.d("ERROR RESPONSE", "Cat: ${it.message()}")
                    }
                }
            }
        }
    }

    fun deleteFact(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.removeFact(id)
        }
    }

}