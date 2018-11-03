package com.reecekidd.streakr.User

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.reecekidd.streakr.R
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
        val currentUser = getCurrentUser(position)
        setFullNameOnViewHolder(holder, currentUser.getFullName())
        setUserNameOnViewHolder(holder, currentUser.userName)
    }

    private fun getCurrentUser(position: Int): UserData {
        return userFeed.users[position]
    }

    private fun setFullNameOnViewHolder(holder: UserViewHolder, fullName: String){
        holder.itemView.userFullName.text = fullName
    }

    private fun setUserNameOnViewHolder(holder: UserViewHolder, userName: String){
        holder.itemView.userName.text = userName
    }

}

