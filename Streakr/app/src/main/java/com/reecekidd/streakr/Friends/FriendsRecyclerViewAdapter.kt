package com.reecekidd.streakr.Friends

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.reecekidd.streakr.R
import kotlinx.android.synthetic.main.friend_row.view.*

class FriendFeed(val users: List<Friend>)

class Friend(val _id: String, val userName: String)

class FriendsRecyclerViewAdapter(val friendFeed: FriendFeed): RecyclerView.Adapter<FriendViewHolder>() {

    val LOG_TAG = FriendsRecyclerViewAdapter::class.java.simpleName

    override fun getItemCount(): Int {
        return friendFeed.users.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        val addFriendLayoutInflater = LayoutInflater.from(parent.context)
        val friendRow = addFriendLayoutInflater.inflate(R.layout.friend_row, parent, false)
        return FriendViewHolder(friendRow)
    }

    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
    val user = friendFeed.users.get(position)
        holder?.view.userNameTextView.text = user.userName
    }

}

class FriendViewHolder(val view: View): RecyclerView.ViewHolder(view) {

}