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
        weatherDetailsViewModel.getWeatherData("44.34", "10.99", "efa70ac20519053b1f357c9f1e9ebe5c")
    }

    private fun bindObservers() {
        weatherDetailsViewModel.weatherData.observe(viewLifecycleOwner) {
            when (it) {
                is APIResponse.Success -> {
                    Toast.makeText(requireContext(), it.data?.name, Toast.LENGTH_SHORT).show()
                }

                is APIResponse.Error -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }

                is APIResponse.Loading -> {
                    Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

}