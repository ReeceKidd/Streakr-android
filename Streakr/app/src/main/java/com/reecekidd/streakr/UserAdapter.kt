package com.reecekidd.streakr

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.user_row.view.*

class UserAdapter(val userFeed: UserFeed) : RecyclerView.Adapter<UserViewHolder>() {

    override fun getItemCount(): Int {
        return userFeed.users.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.user_row, parent, false)
        return UserViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.itemView.userFullName.text = userFeed.users[position].firstName
        holder.itemView.userName.text = userFeed.users[position].userName
    }
}

class UserViewHolder(view: View): RecyclerView.ViewHolder(view) {

}