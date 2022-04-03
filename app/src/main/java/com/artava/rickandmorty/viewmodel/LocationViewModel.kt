package com.artava.rickandmorty.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artava.rickandmorty.SharedRepository
import com.artava.rickandmorty.model.LocationList
import kotlinx.coroutines.launch

class LocationViewModel(): ViewModel() {
    private val repository = SharedRepository()

    private var _allLocation = MutableLiveData<LocationList?>()
    var allLocation : LiveData<LocationList?> = _allLocation

    fun getLocationByPage(page: Int){
        viewModelScope.launch {
            val response = repository.getLocationByPage(page)
            _allLocation.postValue(response)
        }
    }
}