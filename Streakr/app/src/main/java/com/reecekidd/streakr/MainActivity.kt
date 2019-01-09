package com.reecekidd.streakr

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.beust.klaxon.Klaxon

import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    val tag = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sharedPreferences = getSharedPreferences(
                getString(R.string.shared_preferences_api_key), Context.MODE_PRIVATE)
        val jsonWebToken = sharedPreferences.getString(getString(R.string.json_web_token), null)
        if(jsonWebToken.isNotEmpty()){
            val isJsonWebTokenValidRequest = getJsonWebTokenRequest(jsonWebToken)
            val client = OkHttpClient()
            client.newCall(isJsonWebTokenValidRequest).enqueue(object : Callback {
                override fun onResponse(call: Call, response: Response) {
                    val body = response.body()?.string()
                    if (response.isSuccessful) {
                        runOnUiThread {
                            val homeActivityIntent = Intent(applicationContext, HomeActivity::class.java)
                            applicationContext.startActivity(homeActivityIntent)
                        }
                        return
                    }
                    val parsedResponse = Klaxon().parse<ErrorServerResponse>("""$body""")
                    val message = parsedResponse?.message
                    runOnUiThread {
                        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
                        val loginActivityIntent = Intent(applicationContext, LoginActivity::class.java)
                        applicationContext.startActivity(loginActivityIntent)
                    }
                }

                override fun onFailure(call: Call, e: IOException) {
                    Toast.makeText(applicationContext, e.message, Toast.LENGTH_LONG).show()
                    Log.d(tag, "Failed to execute request ${e.message}")
                }
            })

        }

        val registerAcvitityIntent = Intent(this.applicationContext, RegisterActivity::class.java)
        this.applicationContext.startActivity(registerAcvitityIntent)
    }

    private fun getJsonWebTokenRequest(jsonWebToken: String): Request {
        val url = "http://10.0.2.2:4040/json-web-token/$jsonWebToken"
        Log.d(tag, "url: $url")
        val request = Request.Builder().url(url).build()
        return request
    }

}
