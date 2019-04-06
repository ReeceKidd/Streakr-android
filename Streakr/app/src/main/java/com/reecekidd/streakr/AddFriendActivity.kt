package com.reecekidd.streakr

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;

class AddFriendActivity : AppCompatActivity() {

    lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_friend)
    }

    internal inner class getFriends: AsyncTask<Void, Void, String>() {

        override fun doInBackground(vararg params: Void?): String {
       return "success"
        }
        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
        }
    }

}
