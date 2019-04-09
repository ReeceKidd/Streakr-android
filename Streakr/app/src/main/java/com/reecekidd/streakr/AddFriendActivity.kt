package com.reecekidd.streakr

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.nfc.Tag
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.beust.klaxon.Klaxon
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_add_friend.*
import okhttp3.*
import java.io.IOException
import java.util.logging.Logger

class AddFriendActivity : AppCompatActivity() {


    val LOG_TAG = AddFriendActivity::class.simpleName

    lateinit var context: Context
    lateinit var searchQuery: String
    var jsonWebToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJtaW5pbXVtVXNlckRhdGEiOnsiX2lkIjoiNWMzODM3ZGZiNjNmYzM0MWU0NjNjN2ViIiwidXNlck5hbWUiOiJ0ZXN0ZXIxIn0sImlhdCI6MTU1NDc4MzM0OSwiZXhwIjoxNTU1Mzg4MTQ5fQ.m5aCqhSO9zGF3HE_EbfxTAEPNTqfgCcwL5ll_LbzpKg"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_friend)
        addFriendRecyclerView.layoutManager = LinearLayoutManager(this.applicationContext)
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

}

