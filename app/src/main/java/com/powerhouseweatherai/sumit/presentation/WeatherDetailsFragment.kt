package com.powerhouseweatherai.sumit.presentation

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.powerhouseweatherai.sumit.R
import com.powerhouseweatherai.sumit.connectivitychecker.ConnectivityChangeListener
import com.powerhouseweatherai.sumit.connectivitychecker.NetworkChangeReceiver
import com.powerhouseweatherai.sumit.databinding.FragmentWeatherDetailsBinding
import com.powerhouseweatherai.sumit.domain.models.WeatherDetailResponse
import com.powerhouseweatherai.sumit.presentation.adapter.WeatherDetailsAdapter
import com.powerhouseweatherai.sumit.responsehandler.APIResponse
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherDetailsFragment : Fragment(), ConnectivityChangeListener {

    private var _binding: FragmentWeatherDetailsBinding? = null
    private val binding get() = _binding!!

    private val weatherDetailsViewModel: WeatherDetailsViewModel by viewModels()
    private val networkChangeReceiver = NetworkChangeReceiver(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerReceiver()
        handleToolbar()
        bindObservers()
        bindViews()
    }

    private fun registerReceiver() {
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        requireActivity().registerReceiver(networkChangeReceiver, filter)
    }

    private fun unregisterReceiver() {
        requireActivity().unregisterReceiver(networkChangeReceiver)
    }

    private fun handleToolbar() {
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.actionToolbarHomePage.title = resources.getString(R.string.weather_forecast)
        binding.actionToolbarHomePage.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun bindViews() {
        binding.swipeRefreshHomePageMessages.setOnRefreshListener {
            fetchWeatherData(fromServer = true)
            binding.swipeRefreshHomePageMessages.isRefreshing = false
        }
        fetchWeatherData(fromServer = false)
    }


    private fun fetchWeatherData(fromServer: Boolean) {
        val listOfLatLonForSpecifiedCities = getListOfLatLonForSpecifiedCities()
        weatherDetailsViewModel.getWeatherDataList(
            list = listOfLatLonForSpecifiedCities,
            appId = "62a2e620e6f6278322c50d50143ba2ee",
            fromServer = fromServer
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
                    showLoading(false)
                    handleRvWeather(it.data)
                }

                is APIResponse.Error -> {
                    showLoading(false)
                    Toast.makeText(requireContext(), "Error ${it.message}", Toast.LENGTH_SHORT)
                        .show()
                }

                is APIResponse.Loading -> {
                    showLoading(true)
                }
            }
        }

    }

    private fun handleRvWeather(data: MutableList<WeatherDetailResponse?>?) {
        val weatherDetailsAdapter = WeatherDetailsAdapter(data)
        with(binding.rvWeatherDetails) {
            this.adapter = weatherDetailsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.shimmerFrameLayoutWeatherDetailsPage.visibility = View.VISIBLE
            binding.shimmerFrameLayoutWeatherDetailsPage.startShimmer()
            binding.rvWeatherDetails.visibility = View.GONE
        } else {
            binding.shimmerFrameLayoutWeatherDetailsPage.stopShimmer()
            binding.shimmerFrameLayoutWeatherDetailsPage.visibility = View.GONE
            binding.rvWeatherDetails.visibility = View.VISIBLE
        }
    }

    override fun onNetworkConnected() {
        Toast.makeText(requireContext(), "Network Connected", Toast.LENGTH_SHORT).show()
    }

    override fun onNetworkDisconnected() {
        Toast.makeText(requireContext(), "Network Disconnected", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        unregisterReceiver()
        _binding = null
    }

}