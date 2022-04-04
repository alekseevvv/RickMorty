package com.artava.rickandmorty.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artava.rickandmorty.repository.SharedRepository
import com.artava.rickandmorty.model.Character
import com.artava.rickandmorty.model.CharacterList
import com.artava.rickandmorty.model.EpisodeList
import com.artava.rickandmorty.model.LocationList
import kotlinx.coroutines.launch

class SharedViewModel: ViewModel() {
    private val repository = SharedRepository()

    private val _characterByIdLiveData = MutableLiveData<Character?>()
    val characterByIdLiveData : LiveData<Character?> = _characterByIdLiveData

    private var _allEpisode = MutableLiveData<EpisodeList?>()
    var allEpisode : LiveData<EpisodeList?> = _allEpisode

    fun getEpisodeByPage(page: Int){
        viewModelScope.launch {
            val response = repository.getEpisodeByPage(page)
            _allEpisode.postValue(response)
        }
    }

    fun refreshCharacter(characterId: Int){
        viewModelScope.launch {
            val response = repository.getCharacterById(characterId)
            _characterByIdLiveData.postValue(response)
        }
    }

    private val _allCharacter = MutableLiveData<CharacterList?>()
    val allCharacter : LiveData<CharacterList?> = _allCharacter

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
                _allCharacter.postValue(response)
        }
    }

    private var _allLocation = MutableLiveData<LocationList?>()
    var allLocation : LiveData<LocationList?> = _allLocation

    fun getLocationByPage(page: Int){
        viewModelScope.launch {
            val response = repository.getLocationByPage(page)
            _allLocation.postValue(response)
        }
    }
}