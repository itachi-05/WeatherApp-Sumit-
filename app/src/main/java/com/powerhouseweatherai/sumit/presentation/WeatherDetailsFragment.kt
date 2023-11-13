package com.powerhouseweatherai.sumit.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.powerhouseweatherai.sumit.R
import com.powerhouseweatherai.sumit.databinding.FragmentWeatherDetailsBinding
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
        weatherDetailsViewModel.getWeatherData("35", "139", "7fe67bf08c80ded756e598d6f8fedaea")
    }

    private fun bindObservers() {

    }

}