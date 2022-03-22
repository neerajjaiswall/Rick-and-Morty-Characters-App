package com.demo.characterlistretrofit.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.*
import androidx.lifecycle.ViewModel
import com.demo.characterlistretrofit.data.CharacterModel
import com.demo.characterlistretrofit.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel(private val repository: Repository): ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getCharacters()
        }
    }
    val characters : LiveData<List<CharacterModel>>
        get() = repository.characters
}