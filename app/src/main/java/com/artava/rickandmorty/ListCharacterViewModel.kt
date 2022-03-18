package com.artava.rickandmorty

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artava.rickandmorty.model.Character
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class ListCharacterViewModel() : ViewModel() {
    private val repository = SharedRepository()

    private val _allCharacter = MutableLiveData<List<Character?>>()
    val allCharacter : LiveData<List<Character?>> = _allCharacter

    fun listAllCharacter(){
        println("Хуяк1")
        viewModelScope.launch {
            val response = repository.getAllCharacter()
            println("Хуяк2")
            println(response?.first()?.full_name)
            _allCharacter.postValue(response!!)
        }
    }

}