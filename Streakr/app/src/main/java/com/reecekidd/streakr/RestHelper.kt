package com.reecekidd.streakr

import android.annotation.SuppressLint
import android.util.Log
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException

val RestHelperTaG = "Rest Helper "
class RestHelper {

    fun fetchJSON(){
        Log.d(RestHelperTaG, "Attempting to fetchJSON")
        val url = "https://api.letsbuildthatapp.com/youtube/home_feed"
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d(RestHelperTaG, "Failure: $e")
            }
            override fun onResponse(call: Call, response: Response) {
                val body = response.body()?.string()
              Log.d(RestHelperTaG, "Response: $body")
                val gson = GsonBuilder().create()
                val userFeed = gson.fromJson(body, listOf<User>().javaClass)
                // Need to finish Rx Java lecturer and then move on to RetroFit. 
            }
        })
    }

}
