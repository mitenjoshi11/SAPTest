package com.example.testingproject.repository

import com.example.testingproject.data.api.RetrofitInstance
import com.example.testingproject.models.UserResponse
import com.example.testingproject.models.UsersWithCityResponse
import com.example.testingproject.utils.Resource

class UserRepository {
    suspend fun getUserData(): Resource<UserResponse> {
        val response = RetrofitInstance.api.getUsers()
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }

    suspend fun getUserDataWithCity(): Resource<UsersWithCityResponse> {
        val response = RetrofitInstance.api.getUsersWithCity()
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }
}