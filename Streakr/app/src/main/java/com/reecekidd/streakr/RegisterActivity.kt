package com.reecekidd.streakr

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.util.Log

import kotlinx.android.synthetic.main.activity_register.*
import okhttp3.*
import java.io.IOException

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val tag = "Register Activity"

        loginTextView.setOnClickListener {
            Log.d(tag, "Login button tclicked")
            val intent = Intent(this.applicationContext, LoginActivity::class.java)
            this.applicationContext.startActivity(intent)
        }

        registerButton.setOnClickListener {
            Log.d(tag, "Registration button clicked")
            val userNameText = userNameTextFieldRegistration.text.toString()
            val emailText = emailTextFieldRegistration.text.toString()
            val passwordText = passwordTextFieldRegistration.text.toString()
            Log.d(tag, "Username: $userNameText")
            Log.d(tag, "Email $emailText")
            Log.d(tag, "Password $passwordText")
            makeRegistrationCall(userNameText, emailText, passwordText)
        }


    }

    private fun makeRegistrationCall(userNameText: String, emailText: String, passwordText: String) {
        Log.d("Registration", "Attempting to register user")

        val url = "http://10.0.2.2:4040/user/register"

        val json = """ {
 	    "userName": "$userNameText",
          "email": "$emailText",
             "password": "$passwordText"
            }
        	""".trimIndent()
        Log.d("Registration", "json: $json")
        val requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json)

       val request = Request.Builder().url(url).post(requestBody).build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response?.body()?.string()
                Log.d("Registration", "Body : $body")
            }

            override fun onFailure(call: Call, e: IOException) {
                Log.d("Registration", "Failed to execute request ${e.message}")
            }
        })


    }

}
