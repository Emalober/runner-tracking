package com.ar.maloba.runnertracking.ui.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.ar.maloba.runnertracking.Constants
import com.ar.maloba.runnertracking.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.android.synthetic.main.settings_fragment.*
import javax.inject.Inject

@AndroidEntryPoint
class SettingsFragment : Fragment(R.layout.settings_fragment) {

    @Inject
    lateinit var sharePreferences: SharedPreferences

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadFieldsFromSharePref()

        btnApplyChanges.setOnClickListener {
            val success = applyChangesToSharePref()
            if(success) {
                Toast.makeText(requireContext(), "Saved change", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(requireContext(), "Please fill out all the fields", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun loadFieldsFromSharePref() {
        val name = sharePreferences.getString(Constants.KEY_NAME, "")
        val weigth = sharePreferences.getFloat(Constants.KEY_WEIGHT, 80f)
        etName.setText(name)
        etWeight.setText(weigth.toString())
    }

    private fun applyChangesToSharePref(): Boolean {
        val nameText = etName.text.toString()
        val weightText = etWeight.text.toString()
        if(nameText.isEmpty() || weightText.isEmpty()) {
            return false
        }

        sharePreferences.edit()
            .putString(Constants.KEY_NAME, nameText)
            .putFloat(Constants.KEY_WEIGHT, weightText.toFloat())
            .apply()
        val toolbarText = "Let´´ go $nameText"
        requireActivity().tvToolbarTitle.text = toolbarText
        return true
    }
}
