package com.harbdm.urbanexplorer.presentation.ui.screens.about

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.harbdm.urbanexplorer.presentation.databinding.AboutFeaturesItemBinding
import com.harbdm.urbanexplorer.presentation.ui.screens.about.components.FeatureItem

class FeatureItemsAdapter :
    ListAdapter<FeatureItem, FeatureItemsAdapter.FeatureViewHolder>(FeatureDiffCallBack()) {

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

    override fun onBindViewHolder(
        holder: FeatureViewHolder,
        position: Int,
        payloads: MutableList<Any?>
    ) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            val changes = payloads
                .flatMap { it as? List<*> ?: emptySet<Any>() }
                .mapNotNull { it as? String }
                .toSet()

            holder.bindPartial(getItem(position),changes)
        }
    }


    class FeatureViewHolder(
        private val binding: AboutFeaturesItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: FeatureItem) {
            binding.feature = item
            binding.executePendingBindings()
        }
        fun bindPartial(item: FeatureItem, changes: Set<String>) {
            if (Payload.TITLE.name in changes) binding.tittle.text = item.tittle
            if (Payload.BODY.name in changes) binding.body.text = item.body
            if (Payload.ICON_RES.name in changes) binding.iconRes.setImageResource(item.iconRes)
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

    override fun getChangePayload(oldItem: FeatureItem, newItem: FeatureItem): Any? {
        val changed = mutableListOf<String>()
        if (oldItem.tittle != newItem.tittle) changed += Payload.TITLE.name
        if (oldItem.body != newItem.body) changed += Payload.BODY.name
        if (oldItem.iconRes != newItem.iconRes) changed += Payload.ICON_RES.name
        return if (changed.isEmpty()) null else changed
    }
}


private enum class Payload { TITLE, BODY, ICON_RES }