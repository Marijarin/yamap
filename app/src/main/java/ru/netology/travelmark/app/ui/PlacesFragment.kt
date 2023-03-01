package ru.netology.travelmark.app.ui

import android.os.Bundle
import android.view.*
import androidx.core.os.bundleOf
import androidx.core.view.MenuProvider
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.flow.collectLatest
import ru.netology.travelmark.R
import ru.netology.travelmark.app.adapter.OnInteractionListener
import ru.netology.travelmark.app.adapter.PlacesAdapter
import ru.netology.travelmark.app.dto.Place
import ru.netology.travelmark.app.viewmodel.MapViewModel
import ru.netology.travelmark.databinding.FragmentPlacesBinding

class PlacesFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentPlacesBinding.inflate(inflater, container, false)

        val viewModel by viewModels<MapViewModel>()

        val adapter = PlacesAdapter(object : OnInteractionListener {

            override fun onClick(place: Place) {
                findNavController().navigate(
                    R.id.action_placesFragment_to_mapsFragment, bundleOf(
                        MapsFragment.LAT_KEY to place.latitude,
                        MapsFragment.LONG_KEY to place.longitude
                    )
                )
            }

            override fun onRemove(place: Place) {
                viewModel.removeById(place.id)
            }

            override fun onEdit(place: Place) {
                DialogPlaceFragment.newInstance(lat = place.latitude, long = place.longitude, id = place.id)
                    .show(childFragmentManager, null)
            }
        })

        binding.list.adapter = adapter

        viewLifecycleOwner.lifecycle.coroutineScope.launchWhenStarted {
            viewModel.data.collectLatest { places ->
                adapter.submitList(places)
                binding.empty.isVisible = places.isEmpty()
            }
        }
        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.map_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean =
                if (menuItem.itemId == R.id.list) {
                    findNavController().navigate(R.id.action_placesFragment_to_mapsFragment)
                    true
                } else {
                    false
                }

        }, viewLifecycleOwner)


        return binding.root
    }
}