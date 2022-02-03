package com.olamachia.simpleblogappwithdatabinding.model.remote

import com.google.gson.annotations.SerializedName
import com.olamachia.simpleblogappwithdatabinding.model.domain.Post
import com.olamachia.simpleblogappwithdatabinding.util.EntityMapper


class PostNetworkMapper : EntityMapper<PostRemoteEntity, Post> {

    override fun mapFromEntity(entity: PostRemoteEntity): Post {
        return Post(
            userId = entity.userId,
            postId = entity.id,
            title = entity.title,
            body = entity.body
        )
    }


    override fun mapToEntity(domainModel: Post): PostRemoteEntity {
        return PostRemoteEntity(
            userId = domainModel.userId,
            id = domainModel.postId,
            title = domainModel.title,
            body = domainModel.body
        )
    }

    fun mapFromEntityList(entities : List<PostRemoteEntity>) : List<Post> {
        return entities.map { mapFromEntity(it) }
    }
}