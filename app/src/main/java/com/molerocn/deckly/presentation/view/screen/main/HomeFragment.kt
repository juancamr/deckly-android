package com.molerocn.deckly.presentation.view.screen.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.VIEW_MODEL_STORE_OWNER_KEY
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.molerocn.deckly.R
import com.molerocn.deckly.databinding.FragmentHomeBinding
import com.molerocn.deckly.presentation.UiMessage
import com.molerocn.deckly.presentation.adapter.DeckAdapter
import com.molerocn.deckly.presentation.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    // chat chat
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var adapter: DeckAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()
        Log.i("home", "creando home fragment")

        homeViewModel.onCreate()
        adapter = DeckAdapter { selectedDeck ->
            val action = HomeFragmentDirections.actionHomeFragmentToDeckDetailFragment(selectedDeck.id.toLong())
            navController.navigate(action)
        }
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        binding.addDeck.setOnClickListener {
            showPrompt("Nuevo mazo") { result ->
                if (result != null) {
                    homeViewModel.createDeck(result)
                }
            }
        }
        binding.addCard.setOnClickListener {
            navController.navigate(R.id.action_homeFragment_to_addCardFragment)
        }

        homeViewModel.deckItems.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
            if (list.isEmpty()) {
                binding.emptyLabel.visibility = View.VISIBLE
                binding.recyclerView.visibility = View.GONE
                binding.addCard.isEnabled = false
            } else {
                binding.emptyLabel.visibility = View.GONE
                binding.recyclerView.visibility = View.VISIBLE
                binding.addCard.isEnabled = true
            }
        }

        homeViewModel.isLoading.observe(viewLifecycleOwner) {
            binding.loader.visibility = if (it) View.VISIBLE else View.GONE
        }

        homeViewModel.uiMessage.observe(viewLifecycleOwner) {
            if (it.shouldShow) {
                Toast.makeText(requireContext(), it.message, it.duration).show()
                homeViewModel.uiMessage.postValue(UiMessage(false))
            }
        }
    }

    fun showPrompt(
        title: String,
        onResult: (String?) -> Unit
    ) {
        val input = EditText(context).apply {
            hint = "Ingresa el nombre del mazo"
        }

        MaterialAlertDialogBuilder(requireContext())
            .setTitle(title)
            .setView(input)
            .setPositiveButton("Aceptar") { _, _ ->
                onResult(input.text.toString())
            }
            .setNegativeButton("Cancelar") { dialog, _ ->
                dialog.dismiss()
                onResult(null)
            }
            .show()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

