package com.artava.rickandmorty

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artava.rickandmorty.model.Character
import com.artava.rickandmorty.model.CharacterList
import kotlinx.coroutines.CoroutineScope
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

}