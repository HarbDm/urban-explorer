package com.harbdm.urbanexplorer.presentation.ui.screens.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.harbdm.urbanexplorer.presentation.databinding.AboutScreenFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AboutScreenFragment: Fragment(){

    private var _binding: AboutScreenFragmentBinding? = null

    private val binding get() = _binding!!

    private val viewModel: AboutScreenViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AboutScreenFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textView.text = "we started!"


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}