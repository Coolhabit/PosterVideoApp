package com.coolhabit.postervideoapp.video.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.coolhabit.postervideoapp.baseUI.adapter.ItemDecoration
import com.coolhabit.postervideoapp.baseUI.presentation.BaseFragment
import com.coolhabit.postervideoapp.baseUI.presentation.BaseViewModel
import com.coolhabit.postervideoapp.video.R
import com.coolhabit.postervideoapp.video.databinding.FragmentVideoBinding
import com.coolhabit.postervideoapp.video.presentation.adapter.VideoPosterAdapter
import javax.inject.Inject

class VideoFragment : BaseFragment(R.layout.fragment_video) {

    private val viewModel by viewModels<VideoViewModel>()
    private lateinit var binding: FragmentVideoBinding

    @Inject
    lateinit var posterAdapter: VideoPosterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.initContent()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVideoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvPosters.apply {
            adapter = posterAdapter
            itemAnimator = null
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            addItemDecoration(
                ItemDecoration(
                    context,
                    com.coolhabit.postervideoapp.baseUI.R.dimen.size_6,
                    com.coolhabit.postervideoapp.baseUI.R.dimen.size_6,
                    com.coolhabit.postervideoapp.baseUI.R.dimen.size_6,
                    com.coolhabit.postervideoapp.baseUI.R.dimen.size_6,
                )
            )
        }

        posterAdapter.onClick = {
            Toast.makeText(requireContext(), "$it", Toast.LENGTH_SHORT).show()
        }
    }

    override fun withViewModel(): BaseViewModel = viewModel.apply {
        loadVideo.collectWithState { state ->
            state.isSuccessful { list ->
                posterAdapter.submitList(list)
            }
            binding.progressBar.isVisible = state.isLoading
        }
    }
}
