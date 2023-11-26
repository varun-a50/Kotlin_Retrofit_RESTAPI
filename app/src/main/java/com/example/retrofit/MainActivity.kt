package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.RemoteCallbackList
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var recyclerView: RecyclerView;
    lateinit  var retrofit: Retrofit;
    lateinit var postAdapter: PostAdapter
    lateinit var jsonPlaceholder: JSONPlaceholder
    lateinit var commentsAdapter: CommentsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.Recycler);
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager

        retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        jsonPlaceholder = retrofit.create(JSONPlaceholder::class.java)


//       getPOST()
    //    getComments()
      //  createPost();
        //putPost();
        deletePost();



    }

    private fun deletePost() {
        var call: Call<Void> = jsonPlaceholder.deletePost(2)
        call.enqueue(object  : Callback<Void>{
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
               if (!response.isSuccessful){

               }
                var code: String = response.code().toString()
                Log.d(code, "Delete: ")
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun putPost() {
        var post: POST
        post = POST(5,2,"Post Method","new")
        var call: Call<POST> = jsonPlaceholder.patchPost(2,post)
        call.enqueue(object : Callback<POST>{
            override fun onResponse(call: Call<POST>, response: Response<POST>) {
                if (!response.isSuccessful){

                }
                var code: String = response.code().toString()
                Log.d(code, "Search: ")
                var postList: MutableList<POST> = ArrayList()
                postList.add(response.body()!!)
                postAdapter = PostAdapter(applicationContext,postList)
                recyclerView.adapter = postAdapter
            }

            override fun onFailure(call: Call<POST>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun createPost() {
        var post: POST
        post = POST(5,1,"Post Method","Done")

        var call: Call<POST> = jsonPlaceholder.createPost(post)
        call.enqueue(object : Callback<POST>{
            override fun onResponse(call: Call<POST>, response: Response<POST>) {
                if (!response.isSuccessful){

                }
                var code: String = response.code().toString()
                Log.d(code, "Search: ")


                var postList: MutableList<POST> = ArrayList()
                postList.add(response.body()!!)

                postAdapter = PostAdapter(applicationContext,postList)
                recyclerView.adapter = postAdapter
                Toast.makeText(applicationContext,code,Toast.LENGTH_LONG).show()

            }

            override fun onFailure(call: Call<POST>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun getComments() {
        val call: Call<List<Comments>> = jsonPlaceholder.getComments(3)
        call.enqueue(object : Callback<List<Comments>> {
            override fun onResponse(

                call: Call<List<Comments>>,
                response: Response<List<Comments>>
            ) {
                if (!response.isSuccessful){
                    Toast.makeText(applicationContext,response.code(),Toast.LENGTH_LONG).show()
                }
                val CommentList: List<Comments> = response.body()!!
                commentsAdapter = CommentsAdapter(applicationContext,CommentList)
                recyclerView.adapter = commentsAdapter


            }

            override fun onFailure(call: Call<List<Comments>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun getPOST() {
        val call: Call<List<POST>> = jsonPlaceholder.getPOST()
        call.enqueue(object : Callback<List<POST>> {
            override fun onResponse(call: Call<List<POST>>, response: Response<List<POST>>) {
                if (response.isSuccessful) {
                    val items: List<POST>? = response.body()
                    // Handle successful response here
                    items?.let {
                        if (!response.isSuccessful){
                            Toast.makeText(applicationContext,response.code(),Toast.LENGTH_LONG).show()
                            return
                        }
                        val postList: List<POST> = response.body()!!
                        postAdapter = PostAdapter(applicationContext,postList)
                        recyclerView.adapter = postAdapter
                    }
                } else {
                    // Handle error response here

                }
            }

            override fun onFailure(call: Call<List<POST>>, t: Throwable) {
                // Handle network failure or other errors here

            }
        })

    }
}