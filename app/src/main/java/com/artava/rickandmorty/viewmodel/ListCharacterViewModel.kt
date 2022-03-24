package com.artava.rickandmorty.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artava.rickandmorty.SharedRepository
import com.artava.rickandmorty.model.CharacterList
import kotlinx.coroutines.launch

class ListCharacterViewModel() : ViewModel() {
    private val repository = SharedRepository()

    private val _allCharacter = MutableLiveData<CharacterList>()
    val allCharacter : LiveData<CharacterList> = _allCharacter

    fun listAllCharacter(){
        viewModelScope.launch {
            val response = repository.getAllCharacter()
            _allCharacter.postValue(response!!)
        }
    }

    fun getCharacterByPage(page: Int){
        viewModelScope.launch {
            val response = repository.getCharacterByPage(page)
            _allCharacter.postValue(response!!)
        }
    }

    fun getCharacterByName(name: String){
        viewModelScope.launch {
            val response = repository.getCharacterByName(name)
            if (response!=null)
            _allCharacter.postValue(response!!)
        }
    }

}