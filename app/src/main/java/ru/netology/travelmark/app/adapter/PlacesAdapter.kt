package ru.netology.travelmark.app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

import ru.netology.travelmark.R
import ru.netology.travelmark.app.dto.Place
import ru.netology.travelmark.databinding.FragmentPlaceBinding

interface OnInteractionListener {
    fun onEdit(place: Place) {}
    fun onRemove(place: Place) {}
    fun onClick(place: Place) {}

}

class PlacesAdapter(
    private val onInteractionListener: OnInteractionListener,
) : ListAdapter<Place, PlaceViewHolder>(PlaceDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val binding =
            FragmentPlaceBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return PlaceViewHolder(binding, onInteractionListener)
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        val place = getItem(position)
        holder.bind(place)

    }
}

class PlaceViewHolder(
    private val binding: FragmentPlaceBinding,
    private val onInteractionListener: OnInteractionListener,
) : RecyclerView.ViewHolder(binding.root) {


    fun bind(place: Place) {
        binding.apply {
            name.text = place.name
            description.text = place.description
            latitude.text = place.latitude.toString()
            longitude.text = place.longitude.toString()

            root.setOnClickListener {
                onInteractionListener.onClick(place)
            }
            menu.setOnClickListener {
                PopupMenu(it.context, it).apply {
                    inflate(R.menu.place_menu)
                    setOnMenuItemClickListener { item ->
                        when (item.itemId) {
                            R.id.remove -> {
                                onInteractionListener.onRemove(place)
                                true
                            }
                            R.id.edit -> {
                                onInteractionListener.onEdit(place)
                                true
                            }
                            else -> false
                        }
                    }
                }.show()
            }

        }
    }
}

class PlaceDiffCallback : DiffUtil.ItemCallback<Place>() {
    override fun areItemsTheSame(oldItem: Place, newItem: Place): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Place, newItem: Place): Boolean {
        return oldItem == newItem
    }
}
