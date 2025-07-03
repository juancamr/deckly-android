package com.molerocn.deckly.presentation.view.screen.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.molerocn.deckly.R
import com.molerocn.deckly.databinding.FragmentDeckDetailBinding
import com.molerocn.deckly.databinding.FragmentHomeBinding
import com.molerocn.deckly.presentation.adapter.DeckAdapter
import com.molerocn.deckly.presentation.viewmodel.DeckDetailViewModel
import com.molerocn.deckly.presentation.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue

@AndroidEntryPoint
class DeckDetailFragment : Fragment() {

    private val deckDetailViewModel: DeckDetailViewModel by viewModels()

    private var _binding: FragmentDeckDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDeckDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val deckId = arguments?.let {
            DeckDetailFragmentArgs.fromBundle(it).deckId
        } ?: return

        deckDetailViewModel.onCreate(deckId.toInt())

        deckDetailViewModel.deck.observe(viewLifecycleOwner) { deck ->
            binding.deckName.text = deck.name
        }
    }

}