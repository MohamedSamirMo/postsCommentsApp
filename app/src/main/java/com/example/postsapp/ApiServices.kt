package com.example.postsapp

import android.database.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
@GET("posts")
fun getPosts(): Single<List<PostModelX>>
@GET("comments")
fun getComments(@Query("postId") postId: Int) :Single<List<CommentsModel>>
}