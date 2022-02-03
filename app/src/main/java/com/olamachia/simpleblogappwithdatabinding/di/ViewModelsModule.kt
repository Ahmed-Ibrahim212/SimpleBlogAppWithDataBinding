package com.olamachia.simpleblogappwithdatabinding.di

import com.olamachia.simpleblogappwithdatabinding.viewmodels.BlogPostsViewModel
import com.olamachia.simpleblogappwithdatabinding.viewmodels.PostDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelsModule = module {
    viewModel { BlogPostsViewModel(get()) }
    viewModel { PostDetailViewModel(get()) }
}
