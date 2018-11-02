package com.reecekidd.streakr

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        usersRecyclerView.layoutManager = LinearLayoutManager(this)

        fetchJSON()
    }

    fun fetchJSON(){
        Log.v("Main Activity", "Attempting to fetch JSON")

        val url = "http://10.0.2.2:4040/users"

        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()
        // Continue working through the tutorial.
        val response = client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {
              val body = response?.body()?.string()
                val gson = GsonBuilder().create()
                val userFeed = gson.fromJson(body, UserFeed::class.java)
                print("Test")
            }
            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute request ${e.message}")
            }
        })

    }

}
