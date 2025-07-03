package com.molerocn.deckly.presentation.view.screen.main

import android.R
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.molerocn.deckly.databinding.FragmentCardAddBinding
import com.molerocn.deckly.presentation.UiMessage
import com.molerocn.deckly.presentation.viewmodel.AddCardViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue

@AndroidEntryPoint
class AddCardFragment : Fragment() {

    private var _binding: FragmentCardAddBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AddCardViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCardAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.onCreate()
        viewModel.deckItems.observe(viewLifecycleOwner) { decks ->
            val adapter = ArrayAdapter(
                requireContext(),
                R.layout.simple_dropdown_item_1line,
                decks.map { it.name }
            )
            binding.autoComplete.setAdapter(adapter)
        }
        binding.autoComplete.setOnItemClickListener { _, _, position, _ ->
            val selectedDeck = viewModel.deckItems.value!![position]
            viewModel.selectedDeck.postValue(selectedDeck)
        }
        binding.addCard.setOnClickListener {
            val front = binding.textFieldFront.text.toString()
            val back = binding.textFieldBack.text.toString()
            if (front.isEmpty() || back.isEmpty() || viewModel.selectedDeck.value == null) {
                viewModel.uiMessage.postValue(UiMessage(true, "Es necesario que complete todos los campos"))
                return@setOnClickListener
            }

            viewModel.createCard(front, back)
        }


        viewModel.addedCompleted.observe(viewLifecycleOwner) {
            if (it) {
                binding.textFieldFront.setText("")
                binding.textFieldBack.setText("")
                viewModel.addedCompleted.postValue(false)
            }
        }

        viewModel.uiMessage.observe(viewLifecycleOwner) {
            if (it.shouldShow) {
                Toast.makeText(requireContext(), it.message, it.duration).show()
                viewModel.uiMessage.postValue(UiMessage(false))
            }
        }
    }
}