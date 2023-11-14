package com.powerhouseweatherai.sumit.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.powerhouseweatherai.sumit.R
import com.powerhouseweatherai.sumit.databinding.FragmentWeatherDetailsBinding
import com.powerhouseweatherai.sumit.responsehandler.APIResponse
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherDetailsFragment : Fragment() {

    private var _binding: FragmentWeatherDetailsBinding? = null
    private val binding get() = _binding!!

    private val weatherDetailsViewModel: WeatherDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindObservers()
        bindViews()
    }

    private fun bindViews() {
        fetchWeatherData()
    }


    private fun fetchWeatherData() {
        val listOfLatLonForSpecifiedCities = getListOfLatLonForSpecifiedCities()
        weatherDetailsViewModel.getWeatherDataList(
            list = listOfLatLonForSpecifiedCities,
            appId = "62a2e620e6f6278322c50d50143ba2ee"
        )
    }

    private fun getListOfLatLonForSpecifiedCities(): List<Pair<Double, Double>> {
        val listOfLatLon = listOf(
            "New York" to Pair(40.7128, -74.0060),
            "Singapore" to Pair(1.3521, 103.8198),
            "Mumbai" to Pair(19.0760, 72.8777),
            "Delhi" to Pair(28.6139, 77.2090),
            "Sydney" to Pair(-33.8688, 151.2093),
            "Melbourne" to Pair(-37.8136, 144.9631),
            "Jakarta" to Pair(-6.2088, 106.8456),
            "California" to Pair(36.7783, -119.4179),
            "Jayapura" to Pair(-2.5333, 140.7)
        )
        return listOfLatLon.map { it.second }
    }

    private fun bindObservers() {
        weatherDetailsViewModel.weatherDataList.observe(viewLifecycleOwner) {
            when (it) {
                is APIResponse.Success -> {
                    Toast.makeText(requireContext(), "Success ${it.data?.size}", Toast.LENGTH_SHORT)
                        .show()
                }

                is APIResponse.Error -> {
                    Toast.makeText(requireContext(), "Error ${it.message}", Toast.LENGTH_SHORT)
                        .show()
                }

                is APIResponse.Loading -> {
                    Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

}