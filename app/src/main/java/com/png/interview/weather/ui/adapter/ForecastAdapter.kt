package com.png.interview.weather.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.png.interview.databinding.ListItemForecastBinding
import com.png.interview.weather.ui.binder.ForecastFragmentViewBinder
import com.png.interview.weather.ui.model.AvailableForecastViewData

class ForecastAdapter : RecyclerView.Adapter<ForecastViewHolder>() {
    private val list = mutableListOf<AvailableForecastViewData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemForecastBinding.inflate(inflater)
        return ForecastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        holder.onBindData(list[position])
    }

    override fun getItemCount() = list.size

    fun updateItems(items: List<AvailableForecastViewData>?) {
        list.clear()
        list.addAll(items ?: emptyList())
        notifyItemRangeChanged(0, list.size)
    }
}