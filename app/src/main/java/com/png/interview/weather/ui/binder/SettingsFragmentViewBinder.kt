package com.png.interview.weather.ui.binder

import android.app.Activity
import android.view.View
import com.png.interview.R
import com.png.interview.weather.ui.model.MeasureUnit
import com.png.interview.weather.ui.viewmodel.SettingsViewModel

class SettingsFragmentViewBinder(
    val viewModel: SettingsViewModel,
    val activity: Activity,
) {

    var unit: MeasureUnit = viewModel.getSavedUnit()

    fun onRadioButtonClicked(view: View) {
        when (view.id) {
            R.id.unit_imperial -> unit = MeasureUnit.IMPERIAL
            R.id.unit_metric -> unit = MeasureUnit.METRIC
        }
    }

    fun saveSelectedUnit() {
        viewModel.saveSelectedUnit(unit)
    }
}