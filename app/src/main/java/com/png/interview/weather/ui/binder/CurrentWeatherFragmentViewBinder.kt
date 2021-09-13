package com.png.interview.weather.ui.binder

import android.app.Activity
import android.content.SharedPreferences
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import com.png.interview.MainActivity
import com.png.interview.R
import com.png.interview.weather.ui.model.AvailableLocationViewData
import com.png.interview.weather.ui.model.MeasureUnit
import com.png.interview.weather.ui.viewmodel.CurrentWeatherViewModel
import com.png.interview.weather.ui.viewmodel.SettingsViewModel
import timber.log.Timber

private const val TAG = "CurrentWeatherFragmentV"

class CurrentWeatherFragmentViewBinder(
    private val viewModel: CurrentWeatherViewModel,
    private val activity: Activity,
    private val sharedPreferences: SharedPreferences,
    private val settingsAction: () -> Unit,
    private val forecastAction: (String) -> Unit
) {

    val availableWeatherViewData = viewModel.availableCurrentWeatherLiveData
    val isEmpty = viewModel.isEmptyVisible
    val isError = viewModel.isError

    var input: String = viewModel.query

    val availableLocationViewData = viewModel.availableLocationLiveData
    val locationAdapter = ArrayAdapter<String>(activity, R.layout.dropdown_item_1line)
    private val unit by lazy { getSavedUnit() }

    fun refreshClicked() {
        viewModel.submitCurrentWeatherSearch(input, unit)
    }

    fun seeForecastClicked() {
        forecastAction(input)
        viewModel.seeForecastClicked = true
    }

    fun settingsClicked() {
        settingsAction()
    }

    fun goClicked(view: View) {
        if (input.isEmpty()) {
            Toast.makeText(activity, "Please Enter Query", Toast.LENGTH_LONG).show()
        } else if (input.length < INPUT_MIN_SIZE) {
            Toast.makeText(activity, "Please Enter More than 3 Characters", Toast.LENGTH_LONG).show()
        } else {
            viewModel.submitCurrentWeatherSearch(input, unit)
        }
        (activity as MainActivity).hideSoftKeyboard(view)
    }

    fun onTextChange(text: CharSequence) {
        Timber.tag(TAG).d("text: $text")
        if (text.length >= INPUT_MIN_SIZE) {
            viewModel.submitLocationSearch(text.toString())
        }
        viewModel.seeForecastClicked = false
    }

    fun clearText(view: AutoCompleteTextView) {
        if (!view.isSelected) {
            view.setText("")
        }
    }

    private fun getSavedUnit(): MeasureUnit {
        val unitOrdinal = sharedPreferences.getInt(SettingsViewModel.PREF_UNIT_KEY, 0)
        return MeasureUnit.getUnitByOrdinal(unitOrdinal)
    }

    companion object {
        const val INPUT_MIN_SIZE = 3
        const val AUTOCOMPLETE_ITEM_LIMIT = 5

        interface OnItemClickListener {
            fun onItemClick(v: View)
        }

        @JvmStatic
        @BindingAdapter("adapter", "items", "viewBinder")
        fun updateLocationItems(view: AutoCompleteTextView, adapter: ArrayAdapter<String>,
                                items: List<AvailableLocationViewData>?,
                                viewBinder: CurrentWeatherFragmentViewBinder) {
            if (viewBinder.viewModel.seeForecastClicked) return
            if (items.isNullOrEmpty()) return
            view.setAdapter(adapter)
            adapter.clear()
            val collection = items.map(AvailableLocationViewData::name)
                .subList(0, AUTOCOMPLETE_ITEM_LIMIT)
            adapter.addAll(collection)
            view.setSelected(false)
        }

        @JvmStatic
        @BindingAdapter("onItemClick")
        fun handleItemClick(v: AutoCompleteTextView, listener: OnItemClickListener) {
            v.setOnItemClickListener { parent, view, position, id ->
                listener.onItemClick(v)
                v.setSelected(true)
            }
        }

        @JvmStatic
        @BindingAdapter("onDismiss")
        fun onDismiss(view: AutoCompleteTextView, viewBinder: CurrentWeatherFragmentViewBinder) {
            view.setOnDismissListener {
                viewBinder.clearText(view)
            }
        }
    }
}