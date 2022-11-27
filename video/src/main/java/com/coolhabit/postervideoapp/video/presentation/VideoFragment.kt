package com.coolhabit.postervideoapp.video.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.coolhabit.postervideoapp.baseUI.adapter.ItemDecoration
import com.coolhabit.postervideoapp.baseUI.extensions.FIRST
import com.coolhabit.postervideoapp.baseUI.extensions.NONE
import com.coolhabit.postervideoapp.baseUI.extensions.textColor
import com.coolhabit.postervideoapp.baseUI.presentation.BaseFragment
import com.coolhabit.postervideoapp.baseUI.presentation.BaseViewModel
import com.coolhabit.postervideoapp.video.R
import com.coolhabit.postervideoapp.video.databinding.FragmentVideoBinding
import com.coolhabit.postervideoapp.video.databinding.ViewEdittextItemBinding
import com.coolhabit.postervideoapp.video.presentation.adapter.VideoPosterAdapter
import com.coolhabit.postervideoapp.video.presentation.utils.CustomTouchListener
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import javax.inject.Inject

class VideoFragment : BaseFragment(R.layout.fragment_video), Player.Listener {

    private val viewModel by viewModels<VideoViewModel>()
    private lateinit var binding: FragmentVideoBinding

    @Inject
    lateinit var posterAdapter: VideoPosterAdapter

    var player: ExoPlayer? = null
    private var playWhenReady = true
    private var currentItem = 0
    private var playbackPosition = 0L

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

        binding.toolbar.setNavigationOnClickListener {
            Toast.makeText(
                requireContext(),
                requireContext().resources.getString(R.string.go_back),
                Toast.LENGTH_SHORT
            ).show()
        }



        binding.textBtn.setOnClickListener {
            createdEditTextHandle()
        }

        posterAdapter.onClick = { id, url ->
            viewModel.refreshSelected(id)
            initializePlayer(url)
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

    override fun onPause() {
        super.onPause()
        releasePlayer()
    }

    override fun onStop() {
        super.onStop()
        releasePlayer()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        releasePlayer()
    }

    private fun initializePlayer(url: String) {
        player = ExoPlayer.Builder(requireContext())
            .build()
        player?.playWhenReady = true
        binding.videoView.player = player
        val mediaSource =
            ProgressiveMediaSource.Factory(DefaultHttpDataSource.Factory())
                .createMediaSource(MediaItem.fromUri(url))
        player?.setMediaSource(mediaSource)
        player?.seekTo(playbackPosition)
        player?.playWhenReady = playWhenReady
        player?.addListener(this)
        player?.repeatMode = Player.REPEAT_MODE_ALL
        player?.prepare()
    }

    private fun releasePlayer() {
        player?.let { exoPlayer ->
            playbackPosition = exoPlayer.currentPosition
            currentItem = exoPlayer.currentMediaItemIndex
            playWhenReady = exoPlayer.playWhenReady
            exoPlayer.release()
        }
        player = null
    }

    private fun createdEditTextHandle() {
        val inflater = LayoutInflater.from(binding.root.context)
        if (binding.videoContainer.getChildAt(FIRST) == null) {
            val newTextView = ViewEdittextItemBinding.inflate(inflater, binding.videoView, false)
            newTextView.editText.setText(requireContext().resources.getString(R.string.some_text))
            newTextView.editText.textColor(com.coolhabit.postervideoapp.baseUI.R.color.white)

            newTextView.root.setOnTouchListener(
                CustomTouchListener(
                    binding.videoView.width,
                    binding.videoView.height
                )
            )
            binding.videoContainer.addView(newTextView.root, FIRST)

            binding.root.setOnClickListener {
                newTextView.editText.clearFocus()
                val imm =
                    context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(binding.root.windowToken, NONE)
            }
        } else {
            binding.videoContainer.removeViewAt(FIRST)
        }
    }
}
