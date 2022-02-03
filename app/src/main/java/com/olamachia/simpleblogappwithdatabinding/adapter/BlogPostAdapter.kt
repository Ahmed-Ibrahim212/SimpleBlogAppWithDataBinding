package com.olamachia.simpleblogappwithdatabinding.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.olamachia.simpleblogappwithdatabinding.databinding.ItemListPostBinding
import com.olamachia.simpleblogappwithdatabinding.model.domain.Post
import java.util.*
import kotlin.collections.ArrayList

class BlogPostAdapter(private val context: Context, private var list: ArrayList<Post>,var  listener: RecyclerClickListeneer)
    : RecyclerView.Adapter<BlogPostAdapter.PostViewHolder>() {

   inner class PostViewHolder(var binding: ItemListPostBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(post: Post){
            user.text = post.postId.toString()
            post.title.capitalize(Locale.ROOT)
            myName.text = post.title
        }
        var user = binding.userName
        var myName= binding.postTitle

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
       val binding = ItemListPostBinding.inflate(LayoutInflater.from(context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(list[position])
        holder.binding.blogNavigation.setOnClickListener {
            listener.onClick(holder.adapterPosition,list)
        }

    }

    override fun getItemCount(): Int = list.size

    fun updateData(newList: List<Post>) {
            list.clear()
            val sortedList = newList.sortedBy { post -> post.postId }
            list.addAll(sortedList)
            notifyDataSetChanged()
    }


}
    interface RecyclerClickListeneer{
     fun onClick(position: Int, List: ArrayList<Post>){

     }
 }