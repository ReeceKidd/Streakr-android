package com.reecekidd.streakr.User

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View

class UserViewHolder(view: View): RecyclerView.ViewHolder(view) {
    init {
        view.setOnClickListener {
            println("TEST")
        }
    }
}