package com.reecekidd.streakr.AddFriend

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.beust.klaxon.Klaxon
import com.google.gson.GsonBuilder
import com.reecekidd.streakr.ErrorServerResponse
import com.reecekidd.streakr.R
import kotlinx.android.synthetic.main.activity_add_friend.*
import okhttp3.*
import java.io.IOException

class AddFriendActivity : AppCompatActivity() {

    val LOG_TAG = AddFriendActivity::class.simpleName

    lateinit var context: Context
    var jsonWebToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJtaW5pbXVtVXNlckRhdGEiOnsiX2lkIjoiNWMzODM3ZGZiNjNmYzM0MWU0NjNjN2ViIiwidXNlck5hbWUiOiJ0ZXN0ZXIxIn0sImlhdCI6MTU1NTM5MDAzOCwiZXhwIjoxNTU1OTk0ODM4fQ.RlouRfEqsZs2d-henqyL9PH4bbliCPbuRWT918JhKrw"

    lateinit var userId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_friend)
        addFriendRecyclerView.layoutManager = LinearLayoutManager(this.applicationContext)
        userId = "5ca8c533a4fb9c17a00519b0"

        getFriends().execute()
    }

    internal inner class getFriends : AsyncTask<Void, Void, String>() {

        override fun doInBackground(vararg params: Void?): String {
            getFriendsWithSearchQuery("test")
            return "success"
        }

    }

    fun getFriendsWithSearchQuery(searchQuery: String) {
        val baseUrl = "http://10.0.2.2:4040/users"
        val urlWithSearchQuery = "$baseUrl?searchQuery=$searchQuery"
        val request = Request.Builder()
                .url(urlWithSearchQuery)
                .get()
                .addHeader("x-access-token", jsonWebToken)
                .build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val body = response?.body()?.string()
                    Log.d(LOG_TAG, body)
                    val gson = GsonBuilder().create()
                    val addFriendFeed = gson.fromJson(body, AddFriendFeed::class.java)

                    runOnUiThread {
                        addFriendRecyclerView.adapter = AddFriendRecyclerViewAdapter(addFriendFeed)
                    }
                    return
                }
                val body = response.body()?.string()
                val parsedResponse = Klaxon().parse<ErrorServerResponse>("""$body""")
                val message = parsedResponse?.message
                runOnUiThread {
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                Log.d(LOG_TAG, "Failed to execute request ${e.message}")
                runOnUiThread {
                    Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    internal inner class addFriendTask: AsyncTask<String, Void, String>() {
        override fun doInBackground(vararg params: String?): String? {
            Log.d(LOG_TAG, params[0])
           addFriendCall(params[0]!!, params[1]!!)
            return "Success"
        }
    }

    fun addFriendCall(userId: String, friendId: String) {
        val url = "http://10.0.2.2:4040/friends/add"

        val json = """ {
    "userId": "$userId",
    "friendId": "$friendId"
            }
        	""".trimIndent()
        Log.d(LOG_TAG, "json: $json")
        val requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json)
        Log.d(LOG_TAG, requestBody.toString())

        val request = Request.Builder()
                .url(url)
                .put(requestBody)
                .addHeader("x-access-token", jsonWebToken)
                .build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    Toast.makeText(context, "Added friend successfully", Toast.LENGTH_LONG).show()
                }
                val body = response.body()?.string()
                val parsedResponse = Klaxon().parse<ErrorServerResponse>("""$body""")
                val message = parsedResponse?.message
                runOnUiThread {
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                Log.d(LOG_TAG, "Failed to execute request ${e.message}")
                runOnUiThread {
                    Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

}

