package com.reecekidd.streakr.User

import android.content.Context
import android.content.Intent
import android.support.v7.view.menu.MenuView
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
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
        initaliseViewHolder(holder, getCurrentUser(position))
    }

    private fun initaliseViewHolder(holder: UserViewHolder, currentUser: UserData ){
        setFullNameTextView(getFullNameTextView(holder), currentUser.getFullName())
        setUserNameTextView(getUserNameTextView(holder), currentUser.userName)
        setOnClickListenerForViewProfileButton(getViewProfileButton(holder), getContextOfHolder(holder))
        holder.itemView.addAsFriendButton.setOnClickListener {
            Log.v("ADDASFRIENT", "clicked")
            // NEED TO MAKE THE REST API CALL HERE.
        }
    }

    private fun getContextOfHolder(holder: UserViewHolder): Context {
        return holder.itemView.context
    }

    private fun getCurrentUser(position: Int): UserData {
        return userFeed.users[position]
    }

    private fun getViewProfileButton(holder: UserViewHolder): Button {
        return holder.itemView.viewProfileButton
    }

    private fun setOnClickListenerForViewProfileButton(viewProfileButton: Button, context: Context){
        viewProfileButton.setOnClickListener {
            Log.v("VIEW PROFILE", "clicked")
            val intent = Intent(context, UserProfileActivity::class.java)
            context.startActivity(intent)
            // NEED TO CAPTURE EITHER THE USER DATA OR THE ID HERE TO PASS IT TO THE USER PROFILE
            // ACTVITY
        }
    }

    private fun getAddAsFriendButton(holder: UserViewHolder): Button {
        return holder.itemView.addAsFriendButton
    }

    private fun getFullNameTextView(holder: UserViewHolder): TextView {
        return holder.itemView.userFullName
    }

    private fun getUserNameTextView(holder: UserViewHolder): TextView {
        return holder.itemView.userName
    }

    private fun setFullNameTextView(fullNameTextView: TextView, fullName: String){
        fullNameTextView.text = fullName
    }

    private fun setUserNameTextView(userNameTextView: TextView, userName: String){
        userNameTextView.text = userName
    }

}

