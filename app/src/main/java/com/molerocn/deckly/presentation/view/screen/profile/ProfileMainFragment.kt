package com.molerocn.deckly.presentation.view.screen

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import com.molerocn.deckly.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileMainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile_main, container, false)
    }

}
