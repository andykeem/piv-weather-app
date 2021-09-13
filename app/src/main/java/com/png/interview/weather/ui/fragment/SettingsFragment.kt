package com.png.interview.weather.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.png.interview.databinding.FragmentSettingsBinding
import com.png.interview.ui.InjectedFragment
import com.png.interview.weather.ui.binder.SettingsFragmentViewBinder
import timber.log.Timber

private const val TAG = "SettingsFragment"

class SettingsFragment : InjectedFragment() {

    private lateinit var _viewBinder: SettingsFragmentViewBinder

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return FragmentSettingsBinding.inflate(inflater, container, false).apply {
            _viewBinder = SettingsFragmentViewBinder(
                getViewModel(),
                requireActivity()
            )
            viewBinder = _viewBinder
            this.lifecycleOwner = viewLifecycleOwner
        }.root
    }

    override fun onPause() {
        super.onPause()

        Timber.tag(TAG).d("save settings..")
        _viewBinder.saveSelectedUnit()
    }
}