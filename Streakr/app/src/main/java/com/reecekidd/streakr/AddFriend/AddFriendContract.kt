package com.reecekidd.streakr.AddFriend

interface View {
    fun showAddFriendError();
    fun showAddFriendSuccess();
}

interface AddFriendListener {
    fun addFriend(userId: String, friendId: String);
}