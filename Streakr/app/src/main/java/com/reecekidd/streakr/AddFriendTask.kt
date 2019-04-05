package com.reecekidd.streakr

import android.os.AsyncTask
import android.util.Log

private class AddFriendParams {
    constructor(userId: String, friendId: String)
}

private class AddFriendTask : AsyncTask<AddFriendParams,Int, String>() {
    override fun doInBackground(vararg params: AddFriendParams?): String {
        Log.d("AddFriendParams", "doInBackground")
    }

}