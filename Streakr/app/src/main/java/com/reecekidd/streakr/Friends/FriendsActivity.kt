package com.reecekidd.streakr.Friends

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.AsyncTask
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.util.Log
import android.widget.Toast
import com.beust.klaxon.Klaxon
import com.google.gson.GsonBuilder
import com.reecekidd.streakr.AddFriend.AddFriendActivity
import com.reecekidd.streakr.AddFriend.AddFriendFeed
import com.reecekidd.streakr.AddFriend.AddFriendRecyclerViewAdapter
import com.reecekidd.streakr.ErrorServerResponse
import com.reecekidd.streakr.R
import kotlinx.android.synthetic.main.activity_add_friend.*

import kotlinx.android.synthetic.main.activity_friends.*
import okhttp3.*
import java.io.IOException

class FriendsActivity : AppCompatActivity() {

    val LOG_TAG = FriendsActivity::class.simpleName

    lateinit var context: Context
    lateinit var jsonWebToken: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends)
        setSupportActionBar(toolbar)

        val sharedPreferences = getSharedPreferences(
                getString(R.string.shared_preferences_api_key), Context.MODE_PRIVATE)
        val sharedPreferencesJsonWebToken = sharedPreferences.getString(getString(R.string.json_web_token), null)
        if (sharedPreferencesJsonWebToken != null && sharedPreferencesJsonWebToken.isNotEmpty()) {
            jsonWebToken = sharedPreferencesJsonWebToken
        }

        fab_add_friend.setOnClickListener { view ->
            val intent = Intent(this.applicationContext, AddFriendActivity::class.java)
            this.applicationContext.startActivity(intent)
        }
    }

    fun getFriendsWithSearchQuery() {

        val baseUrl = "http://10.0.2.2:4040/friends"
        val request = Request.Builder()
                .url(baseUrl)
                .get()
                .addHeader("x-access-token", jsonWebToken)
                .build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val body = response?.body()?.string()
                    val gson = GsonBuilder().create()
                    val friendFeed = gson.fromJson(body, FriendFeed::class.java)

                    runOnUiThread {
                        addFriendRecyclerView.adapter = FriendsRecyclerViewAdapter(friendFeed)
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
