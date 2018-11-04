package com.reecekidd.streakr.User

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View

class UserViewHolder(view: View): RecyclerView.ViewHolder(view) {
    init {
        view.setOnClickListener {
            println("TEST")
            val intent = Intent(view.context, UserProfileActivity::class.java)
            view.context.startActivity(intent)
        }
    }
}