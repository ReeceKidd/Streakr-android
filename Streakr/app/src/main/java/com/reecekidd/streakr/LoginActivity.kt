package com.reecekidd.streakr

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.util.Log

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
            makeLoginCall(emailText, passwordText)
        }

    }

    private fun makeLoginCall(emailText: String, passwordText: String) {
        val tag = "LoginAPICall"
        Log.d(tag, "Attempting to login user")
        Log.d(tag, "emailText: $emailText")
        Log.d(tag, "passwordText: $passwordText")

        val url = "http://10.0.2.2:4040/user/login"

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
                val body = response?.body()?.string()
                Log.d(tag, "Body : $body")
            }

            override fun onFailure(call: Call, e: IOException) {
                Log.d(tag, "Failed to execute request ${e.message}")
            }
        })


    }

}
