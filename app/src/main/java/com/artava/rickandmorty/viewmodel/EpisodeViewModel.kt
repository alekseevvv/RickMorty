package com.artava.rickandmorty.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artava.rickandmorty.SharedRepository
import com.artava.rickandmorty.model.CharacterList
import com.artava.rickandmorty.model.Episode
import com.artava.rickandmorty.model.EpisodeList
import kotlinx.coroutines.launch

class EpisodeViewModel : ViewModel() {
    private val repository = SharedRepository()

    private val _allEpisode = MutableLiveData<EpisodeList>()
    val allEpisode : LiveData<EpisodeList> = _allEpisode

    fun getEpisodeByPage(page: Int){
        viewModelScope.launch {
            val response = repository.getEpisodeByPage(page)
                _allEpisode.postValue(response!!)
        }
    }
}