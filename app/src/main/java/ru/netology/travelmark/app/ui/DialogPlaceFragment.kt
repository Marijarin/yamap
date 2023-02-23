package ru.netology.travelmark.app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import ru.netology.travelmark.app.dto.Place
import ru.netology.travelmark.app.util.AndroidUtils
import ru.netology.travelmark.app.viewmodel.MapViewModel
import ru.netology.travelmark.databinding.FragmentDialogPlaceBinding

class DialogPlaceFragment : DialogFragment() {

    companion object {
        private const val ID_KEY = "ID_KEY"
        private const val LAT_KEY = "LAT_KEY"
        private const val LONG_KEY = "LONG_KEY"
        fun newInstance(lat: Double, long: Double, id: Long? = null) = DialogPlaceFragment().apply {
            arguments = bundleOf(LAT_KEY to lat, LONG_KEY to long, ID_KEY to id)
        }
    }

    private val mapViewModel: MapViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentDialogPlaceBinding.inflate(inflater, container, false)



        binding.add.setOnClickListener {
            AndroidUtils.hideKeyboard(requireView())
            if (binding.latitude.text.isNotBlank() && binding.longitude.text.isNotBlank()) {
                mapViewModel.save(
                    Place(
                        id = requireArguments().getLong("ID_KEY"),
                        name = binding.name.text.toString(),
                        description = binding.description.text.toString(),
                        latitude = requireArguments().getDouble("LAT_KEY"),
                        longitude = requireArguments().getDouble("LONG_KEY")
                    )
                )
            }
        }


        return binding.root
    }

}