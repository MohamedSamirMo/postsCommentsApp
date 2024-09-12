package com.example.postsapp

data class CommentsModel(
    val body: String?=null,
    val email: String?=null,
    val id: Int?=null,
    val name: String?=null,
    val postId: Int?=null
)