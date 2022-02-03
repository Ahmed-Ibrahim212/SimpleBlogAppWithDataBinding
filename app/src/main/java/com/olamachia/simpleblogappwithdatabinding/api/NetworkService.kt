package com.olamachia.simpleblogappwithdatabinding.api

import com.olamachia.simpleblogappwithdatabinding.model.remote.CommentNetworkEntity
import com.olamachia.simpleblogappwithdatabinding.model.remote.PostRemoteEntity
import retrofit2.http.GET
import retrofit2.http.Path


interface PostService {
    @GET("posts/")
    suspend fun fetchPosts(): List<PostRemoteEntity>

    @GET("posts/{id}")
    fun fetchPost(@Path("id") postId: Int): PostRemoteEntity

    @GET("posts/{id}/comments")
    suspend fun getComments(@Path("id")id: Int): List<CommentNetworkEntity>
}