package com.reecekidd.streakr

import io.reactivex.Observable
import retrofit2.http.GET
import java.util.*

interface Users {
    @GET ("/users")
    fun getUsers() : Observable<UsersResponse>
}