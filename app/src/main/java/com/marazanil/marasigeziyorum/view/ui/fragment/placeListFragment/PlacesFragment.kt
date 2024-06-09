package com.marazanil.marasigeziyorum.view.ui.fragment.placeListFragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.marazanil.marasigeziyorum.view.ui.fragment.homeFragment.HomeFragment
import com.marazanil.marasigeziyorum.view.ui.fragment.ProfileFragment.ProfileFragment
import com.marazanil.marasigeziyorum.R
import com.marazanil.marasigeziyorum.databinding.FragmentPlacesBinding
import com.marazanil.marasigeziyorum.view.ui.adapter.PlaceAdapter
import com.marazanil.marasigeziyorum.view.ui.fragment.loginFragment.LoginFragment
import com.marazanil.marasigeziyorum.viewModel.PlaceViewModel
import com.marazanil.marasigeziyorum.viewModel.PlaceViewModelFactory

class PlacesFragment : Fragment() {
    private var _binding: FragmentPlacesBinding? = null
    private val binding get() = _binding!!

    private val placeViewModel: PlaceViewModel by viewModels { PlaceViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlacesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Observe the places LiveData and set the adapter
        placeViewModel.places.observe(viewLifecycleOwner, Observer { places ->
            val adapter = PlaceAdapter(places, requireContext())
            binding.recyclerView.adapter = adapter
        })

        placeViewModel.fetchAllPlaces()

        // Set up the popup menu
        binding.btnPopupMenu.setOnClickListener { v ->
            val popup = PopupMenu(requireContext(), v)
            popup.menuInflater.inflate(R.menu.popup_menu, popup.menu)
            popup.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.menu_item1 -> {
                        // Filter for natural places
                        placeViewModel.fetchNaturalPlaces()
                        true
                    }
                    R.id.menu_item2 -> {
                        // Filter for artificial places
                        placeViewModel.fetchWaterPlaces()
                        true
                    }
                    R.id.menu_item3 -> {
                        // Filter for lakes
                        placeViewModel.fetchParks()
                        true
                    }
                    R.id.menu_item4 -> {
                        // Filter for castles
                        placeViewModel.fetchCastles()
                        true
                    }
                    R.id.menu_item5 -> {
                        // Filter for holy places
                        placeViewModel.fetchHolyPlaces()
                        true
                    }
                    R.id.menu_item6 -> {
                        // Filter for historical places
                        placeViewModel.fetchHistoricalPlaces()
                        true
                    }
                    R.id.menu_item7 -> {
                        // Filter for museums
                        placeViewModel.fetchMuseum()
                        true
                    }
                    else -> false
                }
            }
            popup.show()
        }

        // Set up weather button click listener
        binding.btnWeather.setOnClickListener {
            openWeatherApp()
        }

        // Set up bottom navigation
        binding.bottomNavigation.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    // Home action
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.nav_profile -> {
                    // Profile action
                    replaceFragment(ProfileFragment())
                    true
                }
                R.id.nav_logout -> {
                    // Logout and navigate to login screen
                    logout()
                    true
                }
                else -> false
            }
        }
    }

    private fun openWeatherApp() {
        // Kahramanmaraş'ın hava durumu için bir URI oluşturun
        val uri = Uri.parse("geo:0,0?q=Kahramanmaraş weather")

        // Google Hava Durumu uygulamasını açmak için bir Intent oluşturun
        val intent = Intent(Intent.ACTION_VIEW, uri)
        intent.setPackage("com.google.android.apps.maps")

        // Eğer bir hava durumu uygulaması yüklü değilse, tarayıcıda aç
        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(intent)
        } else {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?q=Kahramanmaraş+hava+durumu"))
            startActivity(browserIntent)
        }
    }

    private fun logout() {
        // Logout action
        val intent = Intent(activity, LoginFragment::class.java)
        startActivity(intent)
        activity?.finish()
    }

    private fun replaceFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
