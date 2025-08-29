package com.harbdm.urbanexplorer.presentation.ui.screens.about

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.harbdm.urbanexplorer.presentation.databinding.AboutFeaturesItemBinding
import com.harbdm.urbanexplorer.presentation.ui.screens.about.components.FeatureItem

class FeatureItemsAdapter : ListAdapter<FeatureItem, FeatureItemsAdapter.FeatureViewHolder>(FeatureDiffCallBack()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FeatureViewHolder {
        val binding = AboutFeaturesItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FeatureViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FeatureViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class FeatureViewHolder(
        private val binding: AboutFeaturesItemBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bind (item: FeatureItem){
            binding.feature = item
            binding.executePendingBindings()
        }
    }
}

class FeatureDiffCallBack : DiffUtil.ItemCallback<FeatureItem>() {
    override fun areItemsTheSame(oldItem: FeatureItem, newItem: FeatureItem): Boolean {
        //No unique ID at the moment. Provide uniqueness through tittles
        //and consider adding unique ID in future.
        return oldItem.tittle == newItem.tittle
    }

    override fun areContentsTheSame(oldItem: FeatureItem, newItem: FeatureItem): Boolean {
        return oldItem == newItem
    }
}