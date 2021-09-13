package com.png.interview.weather.ui.binder

import android.app.Activity
import android.content.SharedPreferences
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.png.interview.weather.ui.adapter.ForecastAdapter
import com.png.interview.weather.ui.model.AvailableForecastViewData
import com.png.interview.weather.ui.model.MeasureUnit
import com.png.interview.weather.ui.viewmodel.ForecastViewModel
import com.png.interview.weather.ui.viewmodel.SettingsViewModel

class ForecastFragmentViewBinder(
    val viewModel: ForecastViewModel,
    val activity: Activity,
    val sharedPreferences: SharedPreferences,
    val input: String
) {

    val availableForecastViewData = viewModel.availableForecastLiveData
    val isEmpty = viewModel.isEmptyVisible
    val isError = viewModel.isError

    val forecastAdapter = ForecastAdapter()
    private val unit by lazy { getSavedUnit() }

    init {
        viewModel.submitForecastSearch(input, FORECAST_DAYS, unit)
    }

    private fun getSavedUnit(): MeasureUnit {
        val unitOrdinal = sharedPreferences.getInt(SettingsViewModel.PREF_UNIT_KEY, 0)
        return MeasureUnit.getUnitByOrdinal(unitOrdinal)
    }

    companion object {
        const val FORECAST_DAYS = 3

        @JvmStatic
        @BindingAdapter("adapter", "items")
        fun updateForecastItems(view: RecyclerView, adapter: ForecastAdapter,
                                    items: List<AvailableForecastViewData>?) {
            if (items.isNullOrEmpty()) return
            view.adapter = adapter
            adapter.updateItems(items)
        }
    }
}