package com.reecekidd.streakr

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.google.gson.GsonBuilder
import com.reecekidd.streakr.User.UserAdapter
import com.reecekidd.streakr.User.UserFeed
import kotlinx.android.synthetic.main.activity_user_feed.*
import okhttp3.*
import java.io.IOException

class UserFeedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_feed)

        usersRecyclerView.layoutManager = LinearLayoutManager(this)


       // fetchJSON()


    }

    fun fetchJSON(){
        Log.v("Main Activity", "Attempting to fetch JSON")

        val url = "http://10.0.2.2:4040/users"

        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {
              val body = response.body()?.string()
                val gson = GsonBuilder().create()
                val userFeed = gson.fromJson(body, UserFeed::class.java)
                Log.v("helicopter", body)
              runOnUiThread {
                  usersRecyclerView.adapter = UserAdapter(userFeed)
              }
            }
            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute request ${e.message}")
            }
        })



    }

}
