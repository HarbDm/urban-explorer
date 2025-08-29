package com.harbdm.urbanexplorer.presentation.ui.screens.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.MaterialTheme
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.harbdm.designsystem.UrbanExplorerTheme
import com.harbdm.urbanexplorer.presentation.databinding.AboutScreenFragmentBinding
import com.harbdm.urbanexplorer.presentation.ui.components.InfoBlockWithTittle
import com.harbdm.urbanexplorer.presentation.ui.screens.about.components.FeaturesItemsProvider
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AboutScreenFragment: Fragment(){


    var sendSnackbarMessage: ((String) -> Unit)? = null

    private var _binding: AboutScreenFragmentBinding? = null

    private val binding get() = _binding!!

    private val viewModel: AboutScreenViewModel by viewModels()

    private lateinit var featuresAdapter: FeatureItemsAdapter


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

        setupRecyclerView()


        sendSnackbarMessage?.invoke("We're HERE!!!")

        binding.composeInfoBlockWithTittle.setContent {
            UrbanExplorerTheme {
                InfoBlockWithTittle(
                    tittle = "Welcome to Spotter",
                    body = "What's up Boys ssssssssssssssssssssssssssssssssssssssssssssssss",
                    tittleStyle =  MaterialTheme.typography.labelLarge,
                    bodyStyle = MaterialTheme.typography.bodyLarge
                )
            }
        }

        val features = FeaturesItemsProvider.getFeatures(context)

        featuresAdapter.submitList(features)


    }

    private fun setupRecyclerView(){
        featuresAdapter = FeatureItemsAdapter()
        binding.featuresRecyclerView.apply {
            adapter = featuresAdapter
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}