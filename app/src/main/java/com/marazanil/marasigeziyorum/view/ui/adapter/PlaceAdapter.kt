package com.marazanil.marasigeziyorum.view.ui.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.marazanil.marasigeziyorum.data.db.entity.Place
import com.marazanil.marasigeziyorum.databinding.ItemPlaceBinding

class PlaceAdapter(private val places: List<Place>) : RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val binding = ItemPlaceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlaceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        val place = places[position]
        holder.bind(place)
    }

    override fun getItemCount() = places.size

    inner class PlaceViewHolder(private val binding: ItemPlaceBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(place: Place) {
            binding.placeName.text = place.name
            binding.placeDescription.text = place.description
            Glide.with(binding.placeImage.context)
                .load(place.imageUrl)
                .into(binding.placeImage)
        }
    }
}
