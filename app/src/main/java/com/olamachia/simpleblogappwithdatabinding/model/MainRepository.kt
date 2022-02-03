package com.olamachia.simpleblogappwithdatabinding.model

import android.util.Log
import com.olamachia.simpleblogappwithdatabinding.api.PostService
import com.olamachia.simpleblogappwithdatabinding.cacheo.CacheDao
import com.olamachia.simpleblogappwithdatabinding.cacheo.CommentCacheMapper
import com.olamachia.simpleblogappwithdatabinding.cacheo.PostCacheMapper
import com.olamachia.simpleblogappwithdatabinding.model.domain.Comment
import com.olamachia.simpleblogappwithdatabinding.model.domain.Post
import com.olamachia.simpleblogappwithdatabinding.model.remote.CommentNetworkMapper
import com.olamachia.simpleblogappwithdatabinding.model.remote.PostNetworkMapper
import com.olamachia.simpleblogappwithdatabinding.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainRepository
constructor(
    private val cacheDao: CacheDao,
    private val postService: PostService,
    private val postCacheMapper: PostCacheMapper,
    private val postNetworkMapper: PostNetworkMapper,
    private val commentCacheMapper: CommentCacheMapper,
    private val commentNetworkMapper: CommentNetworkMapper
) {

    suspend fun getPosts() : Flow<DataState<List<Post>>> = flow {
        emit(DataState.loading())
        delay(1000)
        try {
            val remotePosts = postService.fetchPosts()
            val posts = postNetworkMapper.mapFromEntityList(remotePosts)
            for (post in posts) {
                cacheDao.addPosts(postCacheMapper.mapToEntity(post))
            }
            val cachedPosts = cacheDao.getPosts()
            getPosts()
            emit(DataState.success(postCacheMapper.mapFromEntityList(cachedPosts)))
        }
        catch (e: Exception) {
            emit(DataState.error<Nothing>("Error", e))
        }
    }

    suspend fun insertPost(post: Post) {
        try {
            cacheDao.addPosts(postCacheMapper.mapToEntity(post))
        }
        catch (e: Exception) {
            Log.d("Insert Post error", "insertPost: ${e.message}")
        }
    }

    suspend fun getComments(id:Int) : Flow<DataState<List<Comment>>> = flow {
        emit(DataState.loading())
        try {
            val remoteComments = postService.getComments(id)
            val comments = commentNetworkMapper.mapFromEntityList(remoteComments)
            for (comment in comments) {
                cacheDao.addComment(commentCacheMapper.mapToEntity(comment))
            }
            val cachedComments = cacheDao.getComments()
            getComments(id)
            emit(DataState.success(commentCacheMapper.mapFromEntityList(cachedComments)))
        }
        catch (e: Exception) {
            emit(DataState.error<Nothing>("Error", e))
        }
    }

    suspend fun getPostComments(postId: Int) : Flow<DataState<List<Comment>>> = flow {
        emit(DataState.loading())
        try {
            val cachedPostComments = cacheDao.getPostComments(postId)
            emit(DataState.success(commentCacheMapper.mapFromEntityList(cachedPostComments)))
        }
        catch (e: Exception) {
            emit(DataState.error<Nothing>("Error", e))
        }
    }

    suspend fun insertComments(comment: Comment) {
        try {
            cacheDao.addComment(commentCacheMapper.mapToEntity(comment))
        }
        catch (e: Exception) {

        }
    }
}