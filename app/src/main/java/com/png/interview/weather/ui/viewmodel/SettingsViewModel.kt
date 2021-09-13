package com.png.interview.weather.ui.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.png.interview.weather.ui.model.MeasureUnit
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    val sharedPreferences: SharedPreferences
) : ViewModel() {

    fun saveSelectedUnit(unit: MeasureUnit) {
        sharedPreferences.edit()
            .putInt(PREF_UNIT_KEY, unit.ordinal)
            .apply()
    }

    fun getSavedUnit(): MeasureUnit {
        val ordinal = sharedPreferences.getInt(PREF_UNIT_KEY, 0)
        return MeasureUnit.getUnitByOrdinal(ordinal)
    }

    companion object {
        const val PREF_UNIT_KEY = "UNIT_KEY"
    }
}