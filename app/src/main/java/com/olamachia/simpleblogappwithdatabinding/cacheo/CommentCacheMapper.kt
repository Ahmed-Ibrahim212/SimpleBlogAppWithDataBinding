package com.olamachia.simpleblogappwithdatabinding.cacheo

import com.olamachia.simpleblogappwithdatabinding.model.domain.Comment
import com.olamachia.simpleblogappwithdatabinding.util.EntityMapper

class CommentCacheMapper: EntityMapper<CommentCacheEntity, Comment> {

    override fun mapFromEntity(entity: CommentCacheEntity): Comment {
        return Comment(
            postId = entity.postId,
            id = entity.id,
            name = entity.name,
            email = entity.email,
            body = entity.body
        )
    }

    override fun mapToEntity(domainModel: Comment): CommentCacheEntity {
        return CommentCacheEntity(
            postId = domainModel.postId,
            id = domainModel.id,
            name = domainModel.name,
            email = domainModel.email,
            body = domainModel.body
        )
    }

    fun mapFromEntityList(entities : List<CommentCacheEntity>) : List<Comment> {
        return entities.map { mapFromEntity(it) }
    }
}