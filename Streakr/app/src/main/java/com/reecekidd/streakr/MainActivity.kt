package com.reecekidd.streakr

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.user_item.*
import org.w3c.dom.Text
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private var TAG = "TRIALCODE"
    private var BASE_URL = "http://10.0.2.2:4040"
    private lateinit var usersAdapter: UsersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        usersAdapter = UsersAdapter()
        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val usersAPI = retrofit.create(Users::class.java)
        usersAPI.getUsers()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({usersAdapter.setUsers(it.data)}, {
                    Toast.makeText(applicationContext, it.message, Toast.LENGTH_SHORT).show()
                }).dispose()
        Log.v(TAG, usersAPI.toString())

    }

    inner class UsersAdapter : RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {

        private val users: MutableList<User> = mutableListOf()

        override fun getItemCount(): Int {
            return users.size
        }

        override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
            holder.bindModel(users[position])
        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
            return UserViewHolder(layoutInflater.inflate(R.layout.user_item, parent, false))
        }

        fun setUsers(data: List<User>){
            users.addAll(data)
            notifyDataSetChanged()
        }

        inner class UserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

            val userName: TextView = itemView.findViewById(R.id.userName)
            val userStreaks: TextView = itemView.findViewById(R.id.userStreaks)
            val userAvatar: ImageView = itemView.findViewById(R.id.userAvatar)
            fun bindModel(user: User){
                userName.text = "${user.firstName} ${user.lastName}"
                userStreaks.text = user.streaks.toString()
                Picasso.get().load(user.profilePicture).into(userAvatar)
            }
        }

    }


}
