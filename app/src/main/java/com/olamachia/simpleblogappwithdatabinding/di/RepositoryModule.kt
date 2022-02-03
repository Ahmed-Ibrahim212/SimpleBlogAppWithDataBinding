package com.olamachia.simpleblogappwithdatabinding.di

import com.olamachia.simpleblogappwithdatabinding.cacheo.CommentCacheMapper
import com.olamachia.simpleblogappwithdatabinding.cacheo.PostCacheMapper
import com.olamachia.simpleblogappwithdatabinding.model.MainRepository
import com.olamachia.simpleblogappwithdatabinding.model.remote.CommentNetworkMapper
import com.olamachia.simpleblogappwithdatabinding.model.remote.PostNetworkMapper
import org.koin.dsl.module


val repositoryModule = module {

    factory { PostCacheMapper() }
    factory { PostNetworkMapper() }
    factory { CommentNetworkMapper() }
    factory { CommentCacheMapper() }

    single { MainRepository(
        cacheDao = get(),
        postService = get(),
        postCacheMapper = get(),
        postNetworkMapper = get(),
        commentCacheMapper = get(),
        commentNetworkMapper = get())
    }
}
