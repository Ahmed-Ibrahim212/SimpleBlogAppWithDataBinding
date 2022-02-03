package com.olamachia.simpleblogappwithdatabinding.views.post_lists

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.olamachia.simpleblogappwithdatabinding.R
import com.olamachia.simpleblogappwithdatabinding.adapter.BlogPostAdapter
import com.olamachia.simpleblogappwithdatabinding.adapter.RecyclerClickListeneer
import com.olamachia.simpleblogappwithdatabinding.cacheo.PostCacheEntity
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.olamachia.simpleblogappwithdatabinding.databinding.FragmentBlogPostBinding
import com.olamachia.simpleblogappwithdatabinding.model.domain.Post
import com.olamachia.simpleblogappwithdatabinding.util.DataState
import com.olamachia.simpleblogappwithdatabinding.util.Utils
import com.olamachia.simpleblogappwithdatabinding.viewmodels.BlogPostsViewModel

class BlogPostFragment : Fragment(), RecyclerClickListeneer {

    private val posts = ArrayList<Post>()
    private val blogPostViewModel: BlogPostsViewModel by viewModel()
    private lateinit var postsAdapter: BlogPostAdapter

    //binding the fragment with the layout xml
    private var _binding: FragmentBlogPostBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBlogPostBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var recyclerView = binding.postsRecyclerView



        subscribeObservers()

        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        postsAdapter = BlogPostAdapter(requireActivity(), posts, this)
        recyclerView.adapter = postsAdapter


        // SearchPosts
        binding.postSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { blogPostViewModel.searchPosts(it) }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { blogPostViewModel.searchPosts(it) }
                return false
            }
        })

    }



    //managing the network state through progress bar
    private fun subscribeObservers() {
        var postProgressBar = binding.postProgressBar
        blogPostViewModel.dataState.observe(this, { result ->
            when (result.status) {
                DataState.Status.SUCCESS -> {
                    Utils.showProgressBar(postProgressBar, false)
                    result.data?.let { list ->
                        Log.d("Post", "subscribeObservers: $list")
                        postsAdapter.updateData(list)

                    }
                }
                DataState.Status.ERROR -> {
                    Utils.showProgressBar(postProgressBar, false)
                    result.message?.let {
                        showError(it)
                    }
                }
                DataState.Status.LOADING -> {
                    Utils.showProgressBar(postProgressBar, true)
                }
            }
        })
    }

    private fun showError(msg: String) {
        if (msg != null) {
            binding.text.text = msg
        }
        binding.text.text = getString(R.string.error_text)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(position: Int, List: ArrayList<Post>) {
        var userId = List[position].userId
        var postId = List[position].postId
        var postTitle = List[position].title
        var postBody = List[position].body

        var postCacheEntity = PostCacheEntity(postId,userId,postTitle, postBody)


        val action = BlogPostFragmentDirections.actionBlogPostFragmentToCommentFragment(postCacheEntity)
        findNavController().navigate(action)
    }

}


