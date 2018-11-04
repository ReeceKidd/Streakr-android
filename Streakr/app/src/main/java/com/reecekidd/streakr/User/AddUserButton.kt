package com.reecekidd.streakr.User

import android.widget.Button
import android.widget.Toast

class AddUserButton(button: Button){
    init {
        button.setOnClickListener {

        Toast.makeText(button.context, "Success", Toast.LENGTH_SHORT )

        }
    }
}