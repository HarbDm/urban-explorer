package com.harbdm.urbanexplorer.feature.about.presentation.ui.screens.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.MaterialTheme
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.harbdm.urbanexplorer.core.designsystem.UrbanExplorerTheme
import com.harbdm.urbanexplorer.core.ui.components.InfoBlockWithTittle
import com.harbdm.urbanexplorer.feature.about.R
import com.harbdm.urbanexplorer.feature.about.databinding.AboutScreenFragmentBinding
import com.harbdm.urbanexplorer.feature.about.presentation.ui.screens.about.components.FeaturesItemsProvider
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

/**
 * THIS IS SHOWCASE SCREEN!
 *
 * There was no actually need do add this screen view based, the only reasoning to
 * showcase xml/fragments integration in composable world.
 *
 * This screen also have a composable that we're calling from inside xml to
 * show compose integration in view world!
 *
 * Screen features:
 *  -Passing things from compose part of the app to view part.
 *  -Using things declared in compose world.
 *  -Integrating view-based screen in compose-first app.
 *  -Integrating composable component in xml screen.
 */
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
        setupClickListeners()
        setupViewModelEventsObserve()

        binding.composeInfoBlockWithTittle.setContent {
            UrbanExplorerTheme {
                InfoBlockWithTittle(
                    tittle = getString(R.string.about_tittle),
                    body = getString(R.string.about_body),
                    tittleStyle =  MaterialTheme.typography.labelLarge,
                    bodyStyle = MaterialTheme.typography.bodyLarge
                )
            }
        }

        val features = FeaturesItemsProvider.getFeatures(context)

        featuresAdapter.submitList(features)
    }

    private fun setupClickListeners(){
        binding.showSnackbarButton.setOnClickListener {
            viewModel.onEvent(AboutEvents.OnShowsnackbar)
        }

        binding.addTestSpotButton.setOnClickListener {
            viewModel.onEvent(AboutEvents.OnAddTestSpot)
        }


    }

    private fun setupViewModelEventsObserve(){
        viewModel.eventFLow
            .flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach { event->
                when (event){
                    is AboutScreenViewModel.UiEvent.ShowSnackbar -> {
                        sendSnackbarMessage?.invoke(event.message)
                    }
                    is AboutScreenViewModel.UiEvent.TestSpotAddedShowSnackbar -> {
                        sendSnackbarMessage?.invoke(event.message)
                    }
                }
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
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