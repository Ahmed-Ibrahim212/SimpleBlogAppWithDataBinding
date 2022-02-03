package com.olamachia.simpleblogappwithdatabinding.views.post_lists

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.olamachia.simpleblogappwithdatabinding.databinding.FragmentCommentBinding
import com.olamachia.simpleblogappwithdatabinding.databinding.ItemListPostBinding
import com.olamachia.simpleblogappwithdatabinding.model.domain.Post
import kotlin.collections.ArrayList

class BlogPostAdapter(private val context: Context, private var list: ArrayList<Post>)
    : RecyclerView.Adapter<BlogPostAdapter.PostViewHolder>() {

    class PostViewHolder(binding: ItemListPostBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bindItem(post: Post){
            bind

        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
       var binding = ItemListPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
//        holder.bindView(list[position])
    }

    override fun getItemCount(): Int = list.size
}