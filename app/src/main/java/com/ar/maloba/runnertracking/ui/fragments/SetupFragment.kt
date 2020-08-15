package com.ar.maloba.runnertracking.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ar.maloba.runnertracking.R
import kotlinx.android.synthetic.main.setup_fragment.*

class SetupFragment : Fragment(R.layout.setup_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvContinue.setOnClickListener {
            findNavController().navigate(R.id.action_setupFragment_to_runFragment)
        }
    }
}
