package com.marazanil.marasigeziyorum.view.ui.fragment.placeListFragment

import android.os.Bundle
import android.view.*
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.marazanil.marasigeziyorum.R
import com.marazanil.marasigeziyorum.databinding.FragmentPlacesBinding
import com.marazanil.marasigeziyorum.view.ui.adapter.PlaceAdapter
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
                        // Filter for restaurants
                        placeViewModel.fetchCastles()
                        true
                    }
                    R.id.menu_item5 -> {
                        // Filter for restaurants
                        placeViewModel.fetchHolyPlaces()
                        true
                    }
                    R.id.menu_item6 -> {
                        // Filter for restaurants
                        placeViewModel.fetchHistoricalPlaces()
                        true
                    }
                    R.id.menu_item7 -> {
                        // Filter for restaurants
                        placeViewModel.fetchMuseum()
                        true
                    }
                    else -> false
                }
            }
            popup.show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
