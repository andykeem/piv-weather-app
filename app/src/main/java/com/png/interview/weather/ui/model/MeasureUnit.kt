package com.png.interview.weather.ui.model

enum class MeasureUnit {
    IMPERIAL,
    METRIC;

    companion object {
        fun getUnitByOrdinal(ordinal: Int): MeasureUnit {
            return MeasureUnit.values().filter {
                it.ordinal == ordinal
            }.first()
        }
    }
}