package com.example.testingproject.data.api

import com.example.testingproject.models.UserResponse
import com.example.testingproject.models.UsersWithCityResponse
import retrofit2.Response
import retrofit2.http.GET

interface APIService {

    @GET("/v1/99066355-8f5e-4c9d-b400-d5bdf26911b6")
    suspend fun getUsers(): Response<UserResponse>

    @GET("/v1/bbac0c66-3f2f-41b2-b3a6-acbae212513e")
    suspend fun getUsersWithCity(): Response<UsersWithCityResponse>


}