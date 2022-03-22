package com.demo.characterlistretrofit.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.demo.characterlistretrofit.data.CharacterModel
import com.demo.characterlistretrofit.retrofit.RetroServiceInterface


class Repository(private val charService: RetroServiceInterface) {

    private val characterLiveData = MutableLiveData<List<CharacterModel>>()
    val characters: LiveData<List<CharacterModel>>
        get() = characterLiveData

    suspend fun getCharacters() {
        val result = charService.getData()
        if (result.isSuccessful) {
            val items = result.body()
            if (items!=null){
                for(i in 1 until 826)
                    characterLiveData.postValue(items!!)
            }
        }
    }
}