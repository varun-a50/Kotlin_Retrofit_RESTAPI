package com.example.retrofit

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query


interface JSONPlaceholder  {
    @GET("posts")
    fun getPOST(): Call<List<POST>>

    @GET("comments")
    fun getComments(@Query("postId") postId: Int ): Call<List<Comments>>

    @retrofit2.http.POST("posts")
    fun createPost(@Body post: POST): Call<POST>

    @PUT("posts/{id}")
    fun putPost(@Path("id") Id: Int, @Body post: POST) : Call<POST>

    @PATCH("posts/{id}")
    fun patchPost(@Path("id") Id: Int, @Body post: POST) : Call<POST>

    @DELETE("posts/{id}")
    fun deletePost(@Path("id") Id: Int): Call<Void>
}