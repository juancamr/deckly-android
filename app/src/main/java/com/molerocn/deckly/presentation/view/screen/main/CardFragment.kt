package com.molerocn.deckly.presentation.view.screen.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.molerocn.deckly.databinding.FragmentCardBinding
import com.molerocn.deckly.presentation.viewmodel.CardViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CardFragment : Fragment() {

    // chat chat
    private var _binding: FragmentCardBinding? = null
    private val binding get() = _binding!!

    private val cardViewModel: CardViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cardViewModel.onCreate()

        //val url = "https://www.oxfordlearnersdictionaries.com/us/media/english/uk_pron/k/kno/knowl/knowledge__gb_3.mp3"
        val url = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
