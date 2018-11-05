package com.reecekidd.streakr.User

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
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
        val itemView = getItemView(holder)
        val context = getContextOfHolder(holder)
        setElementsOfViewHolder(currentUser, itemView, context)
    }

    private fun setElementsOfViewHolder(currentUser: UserData, itemView: View, context: Context){
        setFullNameTextView(getFullNameTextView(itemView), currentUser.getFullName())
        setUserNameTextView(getUserNameTextView(itemView), currentUser.userName)
        setOnClickListenerForViewProfileButton(getViewProfileButton(itemView), context)
        setOnClickListenerForAddUserButton(getAddAsFriendButton(itemView), context)
    }

    private fun setOnClickListenerForViewProfileButton(viewProfileButton: Button, context: Context){
        viewProfileButton.setOnClickListener {

            startUserProfileActivity(context)
            // NEED TO CAPTURE EITHER THE USER DATA OR THE ID HERE TO PASS IT TO THE USER PROFILE
            // ACTVITY
        }
    }

    private fun setOnClickListenerForAddUserButton(addUserButton: Button, context: Context){
        addUserButton.setOnClickListener {
            // NEED TO MAKE THE REST API CALL HERE.
        }
    }

    private fun startUserProfileActivity(context: Context){
        val intent = Intent(context, UserProfileActivity::class.java)
        context.startActivity(intent)
    }

    private fun getContextOfHolder(holder: UserViewHolder): Context {
        return holder.itemView.context
    }

    private fun getCurrentUser(position: Int): UserData {
        return userFeed.users[position]
    }

    private fun getItemView(holder: UserViewHolder): View {
        return holder.itemView
    }

    private fun getViewProfileButton(itemView: View): Button {
        return itemView.viewProfileButton
    }

    private fun getAddAsFriendButton(itemView: View): Button {
        return itemView.addAsFriendButton
    }

    private fun getFullNameTextView(itemView: View): TextView {
        return itemView.userFullName
    }

    private fun getUserNameTextView(itemView: View): TextView {
        return itemView.userName
    }

    private fun setFullNameTextView(fullNameTextView: TextView, fullName: String){
        fullNameTextView.text = fullName
    }

    private fun setUserNameTextView(userNameTextView: TextView, userName: String){
        userNameTextView.text = userName
    }

}

