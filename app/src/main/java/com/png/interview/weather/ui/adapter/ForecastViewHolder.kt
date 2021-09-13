package com.png.interview.weather.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.png.interview.databinding.ListItemForecastBinding
import com.png.interview.weather.ui.model.AvailableForecastViewData

class ForecastViewHolder(private val binding: ListItemForecastBinding) : RecyclerView.ViewHolder(binding.root) {
    fun onBindData(viewData: AvailableForecastViewData) {
        binding.viewData = viewData
        binding.notifyChange()
    }
}