package com.reecekidd.streakr.Main

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.reecekidd.streakr.HomeActivity.HomeActivity
import com.reecekidd.streakr.R
import com.reecekidd.streakr.Register.RegisterActivity

class MainActivity : AppCompatActivity() {

    val tag = "MainActivity"

    lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this.applicationContext
        jsonWebTokenManagerTask().execute()
    }

    internal inner class jsonWebTokenManagerTask: AsyncTask<Void, Void, String>() {
        override fun doInBackground(vararg params: Void?): String? {
            val sharedPreferences = getSharedPreferences(
                    getString(R.string.shared_preferences_api_key), Context.MODE_PRIVATE)
            val jsonWebToken = sharedPreferences.getString(getString(R.string.json_web_token), null)
            if(jsonWebToken != null && jsonWebToken.isNotEmpty()){
                Log.d(tag, "jsonWebToken is not empty")
                // THIS IS WHERE I NEED TO CALL AND VERIFY IF JSON WEB TOKEN IS CORRECT
                val homeActivityIntent = Intent(context, HomeActivity::class.java)
                context.startActivity(homeActivityIntent)
                return null
            }

            Log.d(tag, "startingRegisterActivity")
            val registerActivityIntent = Intent(context, RegisterActivity::class.java)
            context.startActivity(registerActivityIntent)
            return "Success"
        }
    }

}
