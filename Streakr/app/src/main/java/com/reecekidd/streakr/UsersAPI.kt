package com.reecekidd.streakr
import retrofit2.Call
import retrofit2.http.GET

interface UsersAPI {
    @GET ("/users")
    fun getUsers() : Call<User>
}