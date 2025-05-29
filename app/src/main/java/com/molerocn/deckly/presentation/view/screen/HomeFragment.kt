package com.molerocn.deckly.presentation.view.screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.molerocn.deckly.R
import com.molerocn.deckly.databinding.FragmentHomeBinding
import com.molerocn.deckly.presentation.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    // chat chat
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.onCreate()

        val navController = findNavController()
        homeViewModel.nombre.observe(viewLifecycleOwner) {
            binding.tvNombreUsuario.text = "Hola, $it"
        }

        homeViewModel.goStudyEvent.observe(viewLifecycleOwner) { success ->
            if (success) {
                navController.navigate(R.id.action_homeFragment_to_cardFragment)
                homeViewModel.endGoStudyCard()
            }
        }

        binding.btnStudyCard.setOnClickListener {
            homeViewModel.goStudyCard()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}