package com.example.mfinishprojekt.ui.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mfinishprojekt.databinding.FragmentPostsBinding

class PostsFragment : Fragment() {

    private lateinit var _binding: FragmentPostsBinding
    private lateinit var viewModel: PostsViewModel



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPostsBinding.inflate(inflater, container, false)
        return _binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = PostsListAdapter()
        adapter.itemClick {

        }

        _binding.postsListRecyclerView.adapter = adapter

        viewModel = ViewModelProvider(this)[PostsViewModel::class.java]
        viewModel.posts.observe(requireActivity()) {
            if (it != null) {
                adapter.items = it
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.saveInCache()
    }
}