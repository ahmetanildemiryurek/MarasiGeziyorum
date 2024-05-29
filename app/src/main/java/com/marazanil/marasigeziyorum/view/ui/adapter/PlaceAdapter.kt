package com.marazanil.marasigeziyorum.view.ui.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.marazanil.marasigeziyorum.databinding.ItemPlaceBinding
import com.marazanil.marasigeziyorum.data.db.entity.Place

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
            binding.place = place
            binding.handler = this

            Glide.with(binding.placeImage.context)
                .load(place.imageUrl)
                .into(binding.placeImage)
        }

        fun onNavigateClick(place: Place) {
            val gmmIntentUri = Uri.parse("geo:0,0?q=${place.name}")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            context.startActivity(mapIntent)
        }
    }
}
