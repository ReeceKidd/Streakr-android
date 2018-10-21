package com.reecekidd.streakr

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.FieldPosition

class MainActivity : AppCompatActivity() {
    lateinit var usersList: RecyclerView
    private var BASE_URL = "10.0.2.2"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        usersList.adapter
        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val usersAPI = retrofit.create(Users::class.java)
        usersAPI.getUsers()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    var usersResponse = it
                },{

                })

    }

//    inner class UsersListAdapter : RecyclerView.Adapter<UsersListAdapter.UserViewHolder>() {
//
//        private val users: List<User> = mutableListOf()
//
//        override fun getItemCount(): Int {
//            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//        }
//
//        override fun onBindViewHolder(holder: UserViewHolder, position: Int) {}
//        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
//            return UserViewHolder(layoutInflater.inflate(R.layout.user_item))
//        }
//
//        inner class UserViewHolder(val itemView: View): RecyclerView.ViewHolder()
//
//    }


}
