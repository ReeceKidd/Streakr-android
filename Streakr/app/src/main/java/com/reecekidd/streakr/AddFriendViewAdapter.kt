package com.reecekidd.streakr

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.friend_row.view.*

class AddFriendFeed(val users: List<AddFriendFeedUser>)

class AddFriendFeedUser(val _id: String, val userName: String)

class  AddFriendRecyclerViewAdapter(val addFriendFeed: AddFriendFeed): RecyclerView.Adapter<FriendViewHolder>() {

    override fun getItemCount(): Int {
        return addFriendFeed.users.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        val addFriendLayoutInflater = LayoutInflater.from(parent.context)
        val friendRow = addFriendLayoutInflater.inflate(R.layout.friend_row, parent, false)
        return FriendViewHolder(friendRow)
    }

    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
    val user = addFriendFeed.users.get(position)
        holder?.view.userNameTextView.text = user.userName
    }
}

class FriendViewHolder(val view: View): RecyclerView.ViewHolder(view) {

}