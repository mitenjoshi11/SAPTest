package com.example.testingproject.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testingproject.models.UserResponse
import com.example.testingproject.models.UsersWithCityResponse
import com.example.testingproject.repository.UserRepository
import com.example.testingproject.utils.Resource
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {

    private var _userData = MutableLiveData<Resource<UserResponse>>()
    val userData: LiveData<Resource<UserResponse>> = _userData

    private var _userCityData = MutableLiveData<Resource<UsersWithCityResponse>>()
    val userCityData: LiveData<Resource<UsersWithCityResponse>> = _userCityData


    fun getUserData() {
        _userData.value = Resource.Loading()
        viewModelScope.launch {
            val response = repository.getUserData()
            _userData.value = response
        }
    }

     fun getUserWithCityData() {
        _userCityData.value = Resource.Loading()
        viewModelScope.launch {
            val response = repository.getUserDataWithCity()
            _userCityData.value = response
        }
    }
}