package com.reecekidd.streakr.CreateSoloStreak

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.beust.klaxon.Klaxon
import com.reecekidd.streakr.ErrorServerResponse
import com.reecekidd.streakr.R
import com.reecekidd.streakr.SoloStreakCreated.SoloStreakCreatedActivity

import kotlinx.android.synthetic.main.activity_create_solo_streak.*
import okhttp3.*
import java.io.IOException

class CreateSoloStreak : AppCompatActivity() {

    companion object {
        const val USER_ID_KEY = "userId"
        const val SOLO_STREAK_NAME_KEY = "soloStreakName"
        const val SOLO_STREAK_DESCRIPTION_KEY = "soloStreakDescription"
    }

   val LOG_TAG = CreateSoloStreak::class.simpleName

    lateinit var jsonWebToken: String
    lateinit var userId: String
    lateinit var soloStreakNameText: String
    lateinit var soloStreakDescriptionText: String
    lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_solo_streak)
        // Need to get the user ID from the json web token in shared preferences
        context = this.applicationContext
        val sharedPreferences = getSharedPreferences(
                getString(R.string.shared_preferences_api_key), Context.MODE_PRIVATE)

        jsonWebToken = sharedPreferences.getString(getString(R.string.json_web_token), null)
        userId = "5c35116059f7ba19e4e248a9"

        createSoloStreakNextButton.setOnClickListener {
            val streakNameSavedInstance = savedInstanceState?.getString(SOLO_STREAK_NAME_KEY)
            val streakDescriptionSavedInstance = savedInstanceState?.getString(SOLO_STREAK_DESCRIPTION_KEY)
            if(streakNameSavedInstance !== null && streakDescriptionSavedInstance !== null){
                soloStreakNameText = streakNameSavedInstance
                soloStreakDescriptionText = streakDescriptionSavedInstance
            } else {
                soloStreakNameText = soloStreakName.text.toString()
                soloStreakDescriptionText = soloStreakDescription.text.toString()
            }
            createSoloStreakTask().execute()
        }

    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
        if(soloStreakName.text.toString().isNotEmpty()) outState?.putString(SOLO_STREAK_NAME_KEY, soloStreakName.text.toString())
        if(soloStreakDescription.text.toString().isNotEmpty()) outState?.putString(SOLO_STREAK_DESCRIPTION_KEY, soloStreakDescription.text.toString())
    }

    internal inner class createSoloStreakTask: AsyncTask<Void, Void, String>() {
        override fun doInBackground(vararg params: Void?): String? {
            createASoloStreakCall(jsonWebToken, userId, soloStreakNameText, soloStreakDescriptionText, context)
            return "Success"
        }

    }

    private fun createASoloStreakCall(jsonWebToken: String, userId: String, streakName: String, streakDescription: String, context: Context) {
        val url = "http://10.0.2.2:4040/solo-streak/create"

        val json = """ {
    "userId": "$userId",
    "streakName": "$streakName",
    "streakDescription": "$streakDescription"
            }
        	""".trimIndent()
        Log.d(LOG_TAG, "json: $json")
        val requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json)
        Log.d(LOG_TAG, requestBody.toString())

        val request = Request.Builder()
                .url(url)
                .post(requestBody)
                .addHeader("x-access-token", jsonWebToken)
                .build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    runOnUiThread {
                        val intent = Intent(context, SoloStreakCreatedActivity::class.java)
                        intent.putExtra(SOLO_STREAK_NAME_KEY, streakName)
                        intent.putExtra(SOLO_STREAK_DESCRIPTION_KEY, streakDescription)
                        context.startActivity(intent)
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


