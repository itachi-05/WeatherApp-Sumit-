package com.powerhouseweatherai.sumit.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.powerhouseweatherai.sumit.domain.models.WeatherDetailResponse
import com.powerhouseweatherai.sumit.domain.repository.WeatherRepository
import com.powerhouseweatherai.sumit.responsehandler.APIResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherDetailsViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {


    private val _weatherDataList =
        MutableLiveData<APIResponse<MutableList<WeatherDetailResponse?>>>()
    val weatherDataList: LiveData<APIResponse<MutableList<WeatherDetailResponse?>>>
        get() = _weatherDataList


    fun getWeatherDataList(list: List<Pair<Double, Double>>, appId: String, fromServer: Boolean) {

        viewModelScope.launch(Dispatchers.IO) {
            _weatherDataList.postValue(APIResponse.Loading())

            // async call for each lat lon pair
            val deferredResults = list.map { pair ->
                async {
                    weatherRepository.getWeatherData(
                        pair.first.toString(),
                        pair.second.toString(),
                        appId,
                        fromServer = fromServer
                    )
                }
            }

            val results = deferredResults.awaitAll()

            // add all the success and error results to separate lists
            val successList = mutableListOf<WeatherDetailResponse?>()
            val errorList = mutableListOf<String?>()

            for (result in results) {
                when (result) {
                    is APIResponse.Success -> successList.add(result.data)
                    is APIResponse.Error -> errorList.add(result.message)
                    is APIResponse.Loading -> Unit
                }
            }

            // Post the results to _weatherDataList even if there is a single success
            val combinedResult = if (successList.isNotEmpty()) {
                APIResponse.Success(successList)
            } else {
                APIResponse.Error(errorList.joinToString(", "))
            }
            _weatherDataList.postValue(combinedResult)
        }
    }


}