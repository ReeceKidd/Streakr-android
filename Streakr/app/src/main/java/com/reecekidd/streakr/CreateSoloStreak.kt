package com.reecekidd.streakr

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.beust.klaxon.Klaxon

import kotlinx.android.synthetic.main.activity_create_solo_streak.*
import okhttp3.*
import java.io.IOException

class CreateSoloStreak : AppCompatActivity() {

    val tag = "CreateSoloStreak"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_solo_streak)
        // Need to get the user ID from the json web token in shared preferences
        val sharedPreferences = getSharedPreferences(
                getString(R.string.shared_preferences_api_key), Context.MODE_PRIVATE)
        val jsonWebToken = sharedPreferences.getString(getString(R.string.json_web_token), null)

        createASoloStreakCall(jsonWebToken,"5c35116059f7ba19e4e248a9", streakName.text.toString(), streakDescription.text.toString(), this.applicationContext)
    }

    private fun createASoloStreakCall(jsonWebToken: String, userId: String, streakName: String, streakDescription: String, context: Context) {
        val url = "http://10.0.2.2:4040/solo-streak/create"

        val json = """ {
    "userId": "$userId",
    "streakName": "$streakName",
    "streakDescription": "$streakDescription"
            }
        	""".trimIndent()
        Log.d(tag, "json: $json")
        val requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json)
        Log.d(tag, requestBody.toString())

        val request = Request.Builder()
                .url(url)
                .post(requestBody)
                .addHeader("x-access-token", jsonWebToken)
                .build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response.body()?.string()
                if (response.isSuccessful) {
                    runOnUiThread {
                        val intent = Intent(context, SoloStreakCreatedActivity::class.java)
                        context.startActivity(intent)
                    }
                    return
                }
                val parsedResponse = Klaxon().parse<ErrorServerResponse>("""$body""")
                val message = parsedResponse?.message
                runOnUiThread {
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                }

            }

            override fun onFailure(call: Call, e: IOException) {
                Log.d(tag, "Failed to execute request ${e.message}")
            }
        })
    }

}


