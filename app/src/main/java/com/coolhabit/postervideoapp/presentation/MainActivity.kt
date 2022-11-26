package com.coolhabit.postervideoapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.coolhabit.postervideoapp.R
import com.coolhabit.postervideoapp.databinding.ActivityMainBinding
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainActivityViewModel by lazy {
        viewModelFactory.create(
            MainActivityViewModel::class.java
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
