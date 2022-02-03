package com.olamachia.simpleblogappwithdatabinding.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.olamachia.simpleblogappwithdatabinding.databinding.ItemListCommentsBinding
import com.olamachia.simpleblogappwithdatabinding.model.domain.Comment
import com.olamachia.simpleblogappwithdatabinding.views.post_details.CommentFragment
import java.util.*

class CommentAdapter(private val context: Context, private val list: List<Comment>):
    RecyclerView.Adapter<CommentAdapter.CommentViewHolder> (){

    inner class CommentViewHolder(binding: ItemListCommentsBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(comment: Comment){

            user.text = comment.name.capitalize(Locale.ROOT)
            users.text = comment.body
            first.text = comment.email
        }
        var user: TextView = binding.userName
        var users: TextView = binding.commentBody
        var first: TextView = binding.userEmail
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):CommentViewHolder {
        var binding = ItemListCommentsBinding.inflate(LayoutInflater.from(context), parent, false)
        return CommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
       holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}