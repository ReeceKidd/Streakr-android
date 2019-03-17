package com.reecekidd.streakr

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.util.Log
import android.widget.Toast
import com.beust.klaxon.Klaxon


import kotlinx.android.synthetic.main.activity_login.*
import okhttp3.*
import java.io.IOException

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val tag = "LoginActivity"
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        registerTextView.setOnClickListener {
            val intent = Intent(this.applicationContext, RegisterActivity::class.java)
            this.applicationContext.startActivity(intent)
        }

        loginButton.setOnClickListener {
            Log.d(tag, "Login button clicked")
            val emailText = emailTextFieldLogin.text.toString()
            val passwordText = passwordTextFieldLogin.text.toString()
            Log.d(tag, "Email $emailText")
            Log.d(tag, "Password $passwordText")
            makeLoginCall(emailText, passwordText, this.applicationContext)
        }

    }

    private fun makeLoginCall(emailText: String, passwordText: String, context: Context) {
        val tag = "LoginAPICall"
        Log.d(tag, "Attempting to login user")
        Log.d(tag, "emailText: $emailText")
        Log.d(tag, "passwordText: $passwordText")

        val url = "http://10.0.2.2:4040/auth/login"

        val json = """ {
          "email": "$emailText",
             "password": "$passwordText"
            }
        	""".trimIndent()
        Log.d(tag, "json: $json")
        val requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json)
        Log.d(tag, requestBody.toString())
        val request = Request.Builder().url(url).post(requestBody).build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response.body()?.string()
                if (response.isSuccessful) {
                    val parsedResponse = Klaxon().parse<LoginServerResponse>("""$body""")
                    val jsonWebToken = parsedResponse?.jsonWebToken
                    saveJsonWebTokenInSharedPreferences(jsonWebToken!!)
                    runOnUiThread {
                        val intent = Intent(context, HomeActivity::class.java)
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

    private fun saveJsonWebTokenInSharedPreferences(jsonWebToken: String){
        val sharedPreferences = getSharedPreferences(
                getString(R.string.shared_preferences_api_key), Context.MODE_PRIVATE)
        with(sharedPreferences.edit()){
            putString(getString(R.string.json_web_token), jsonWebToken)
            apply()
        }
    }

}
