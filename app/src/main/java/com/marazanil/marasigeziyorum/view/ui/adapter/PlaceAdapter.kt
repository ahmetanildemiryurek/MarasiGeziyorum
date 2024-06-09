package com.marazanil.marasigeziyorum.view.ui.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.marazanil.marasigeziyorum.R
import com.marazanil.marasigeziyorum.data.db.entity.Place
import com.marazanil.marasigeziyorum.databinding.ItemPlaceBinding

class PlaceAdapter(private val places: List<Place>, private val context: Context) : RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val binding = ItemPlaceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlaceViewHolder(binding, context)
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        val place = places[position]
        holder.bind(place)
    }

    override fun getItemCount() = places.size

    inner class PlaceViewHolder(private val binding: ItemPlaceBinding, private val context: Context) : RecyclerView.ViewHolder(binding.root) {
        fun bind(place: Place) {
            binding.placeNameTextView.text = place.name
            binding.placeDescriptionTextView.text = place.description.take(100) + "..." // İlk 100 karakteri göster
            Glide.with(binding.placeImageView.context)
                .load(place.imageUrl)
                .into(binding.placeImageView)

            binding.btnReadMore.setOnClickListener {
                val bundle = Bundle().apply {
                    putParcelable("place", place)
                }
                it.findNavController().navigate(R.id.action_fragmentPlaces_to_placeDetailFragment, bundle)
            }

          /*  binding.btnOpenMap.setOnClickListener {
                val gmmIntentUri = Uri.parse("geo:0,0?q=${place.name}")
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                mapIntent.setPackage("com.google.android.apps.maps")
                context.startActivity(mapIntent)
            } */
        }
    }
}
