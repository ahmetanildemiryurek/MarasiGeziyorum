package com.marazanil.marasigeziyorum.view.ui.fragment.placesDetail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.marazanil.marasigeziyorum.data.db.entity.Place
import com.marazanil.marasigeziyorum.databinding.FragmentPlaceDetailBinding

class PlaceDetailFragment : Fragment() {

    private var _binding: FragmentPlaceDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlaceDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val place = arguments?.getParcelable<Place>("place")

        place?.let {
            binding.placeNameTextView.text = it.name
            binding.placeDescriptionTextView.text = it.description
            Glide.with(this).load(it.imageUrl).into(binding.placeImageView)

            binding.btnOpenMap.setOnClickListener { _ ->
                val gmmIntentUri = Uri.parse("geo:0,0?q=${it.name}")
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                mapIntent.setPackage("com.google.android.apps.maps")
                startActivity(mapIntent)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
