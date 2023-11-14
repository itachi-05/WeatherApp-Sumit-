package com.powerhouseweatherai.sumit.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.powerhouseweatherai.sumit.databinding.HomePageMessageItemBinding
import com.powerhouseweatherai.sumit.domain.models.WeatherDetailResponse

class WeatherDetailsAdapter(
    private val messageList: MutableList<WeatherDetailResponse?>?,
) : RecyclerView.Adapter<WeatherDetailsAdapter.WeatherDetailsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherDetailsViewHolder {
        val binding = HomePageMessageItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return WeatherDetailsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return messageList?.size?:0
    }


    override fun onBindViewHolder(holder: WeatherDetailsViewHolder, position: Int) {
        messageList?.get(position)?.let { holder.bind(it, position) }
    }

    inner class WeatherDetailsViewHolder(private val binding: HomePageMessageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(detailsData: WeatherDetailResponse, position: Int) {
            with(binding) {
                tvCityName.text = detailsData.name
                tvTemp.text = detailsData.main?.temp.toString()+"K"
                tvDescription.text = detailsData.weather?.get(0)?.description
                tvVisibility.text = "Visibility: " + detailsData.visibility.toString()
            }

        }
    }
}

