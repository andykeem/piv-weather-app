package com.png.interview.weather.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.png.interview.databinding.FragmentForecastBinding
import com.png.interview.ui.InjectedFragment
import com.png.interview.weather.ui.binder.ForecastFragmentViewBinder

class ForecastFragment : InjectedFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentForecastBinding.inflate(inflater, container, false)
            .apply {
                viewBinder = ForecastFragmentViewBinder(
                    getViewModel(),
                    requireActivity(),
                    sharedPreferences,
                    arguments?.getString(ARG_INPUT, "") ?: ""
                )
                lifecycleOwner = viewLifecycleOwner
            }.root
    }

    companion object {
        const val ARG_INPUT = "inputArg"
    }
}