package com.olamachia.simpleblogappwithdatabinding.cacheo

import com.olamachia.simpleblogappwithdatabinding.model.domain.Post
import com.olamachia.simpleblogappwithdatabinding.util.EntityMapper

class PostCacheMapper: EntityMapper<PostCacheEntity, Post> {
    override fun mapFromEntity(entity: PostCacheEntity): Post {
        return Post(
            userId = entity.userId,
            postId = entity.id,
            title = entity.title,
            body = entity.body
        )
    }

    override fun mapToEntity(domainModel: Post): PostCacheEntity {
        return PostCacheEntity(
            userId = domainModel.userId,
            id = domainModel.postId,
            title = domainModel.title,
            body = domainModel.body
        )
    }

    fun mapFromEntityList(entities : List<PostCacheEntity>) : List<Post> {
        return entities.map { mapFromEntity(it) }
    }
}