package com.olamachia.simpleblogappwithdatabinding.views.post_details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.olamachia.simpleblogappwithdatabinding.adapter.BlogPostAdapter
import com.olamachia.simpleblogappwithdatabinding.adapter.CommentAdapter
import com.olamachia.simpleblogappwithdatabinding.databinding.FragmentCommentBinding
import com.olamachia.simpleblogappwithdatabinding.model.domain.Comment
import com.olamachia.simpleblogappwithdatabinding.util.DataState
import com.olamachia.simpleblogappwithdatabinding.util.Utils
import com.olamachia.simpleblogappwithdatabinding.viewmodels.PostDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CommentFragment : Fragment() {
    private var comments = listOf<Comment>()
    private var _binding: FragmentCommentBinding? = null
    private val binding get() = _binding!!
    lateinit var view: CoordinatorLayout
    private val postDetailViewModel: PostDetailViewModel by viewModel()
    private lateinit var commentsAdapter: CommentAdapter
    private val args: CommentFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCommentBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var number = args.postCacheEntity

        val recyclerView = binding.commentsRecyclerView


        binding.tvPostBody.text  = number.body
        binding.tvPostTitle.text = number.title


//
//        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
//        postsAdapter = BlogPostAdapter(requireActivity(), posts, this)
//        recyclerView.adapter = postsAdapter

//
        val progressBar = binding.commentsProgressBar
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        commentsAdapter = CommentAdapter(requireActivity(),comments)
        recyclerView.adapter = commentsAdapter


        postDetailViewModel.getComments(number.id)
        postDetailViewModel.commentData.observe(this, Observer { dataState ->
            Log.d("commentData","${postDetailViewModel.commentData.value}")
            when(dataState.status) {
                DataState.Status.SUCCESS -> {
                    Utils.showProgressBar(progressBar, false)
                    dataState.data?.let { list ->
                        commentsAdapter = CommentAdapter(requireContext(), list)
                        Toast.makeText(requireContext(),list.toString(), Toast.LENGTH_SHORT).show()
                        recyclerView.adapter = commentsAdapter
                    }
                }
                DataState.Status.ERROR -> {
                    Utils.showProgressBar(progressBar, false)
                    dataState.message?.let {
                        showError(it)
                    }
                }
                DataState.Status.LOADING -> {
                    Utils.showProgressBar(progressBar, true)
                }
            }
        })

//        fabAddComment.setOnClickListener {
//           AddCommentDialogFragment(postId).show(supportFragmentManager, "ADD COMMENT")
    //           }
//
    }
    private fun showError(msg: String) {
        Snackbar.make(view, msg, Snackbar.LENGTH_INDEFINITE).setAction("DISMISS") {
        }.show()
    }


    companion object {
        const val POST_ID = "postId"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}





