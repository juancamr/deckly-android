package com.molerocn.deckly.presentation.view.screen.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.navigation.fragment.NavHostFragment
import com.molerocn.deckly.R
import com.molerocn.deckly.databinding.FragmentLayoutBinding

class LayoutFragment : Fragment() {

    private var _binding: FragmentLayoutBinding? = null
    private val binding get() = _binding!!

    private lateinit var homeNavHost: NavHostFragment
    private lateinit var browseNavHost: NavHostFragment
    private lateinit var lobbyNavHost: NavHostFragment
    private lateinit var activeFragment: Fragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.homeNavHostFragment.visibility = View.VISIBLE
        binding.browseNavHostFragment.visibility = View.VISIBLE
        binding.lobbyNavHostFragment.visibility = View.VISIBLE

        homeNavHost =
            childFragmentManager.findFragmentById(R.id.home_nav_host_fragment) as NavHostFragment
        browseNavHost =
            childFragmentManager.findFragmentById(R.id.browse_nav_host_fragment) as NavHostFragment
        lobbyNavHost =
            childFragmentManager.findFragmentById(R.id.lobby_nav_host_fragment) as NavHostFragment

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            val currentNavController = when (activeFragment) {
                homeNavHost -> homeNavHost.navController
                browseNavHost -> browseNavHost.navController
                lobbyNavHost -> lobbyNavHost.navController
                else -> null
            }

            if (currentNavController?.popBackStack() == false) {
                requireActivity().finish()
            }
        }


        childFragmentManager.beginTransaction()
            .hide(browseNavHost)
            .hide(lobbyNavHost)
            .show(homeNavHost)
            .commit()

        activeFragment = homeNavHost

        // Manejo de selección del BottomNavigation
        binding.navigationBar.setOnItemSelectedListener { item ->
            val transaction = childFragmentManager.beginTransaction()
            when (item.itemId) {
                R.id.item_1 -> {
                    transaction.hide(activeFragment).show(homeNavHost).commit()
                    activeFragment = homeNavHost
                    true
                }

                R.id.item_2 -> {
                    transaction.hide(activeFragment).show(browseNavHost).commit()
                    activeFragment = browseNavHost
                    true
                }

                R.id.item_3 -> {
                    transaction.hide(activeFragment).show(lobbyNavHost).commit()
                    activeFragment = lobbyNavHost
                    true
                }

                else -> false
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

