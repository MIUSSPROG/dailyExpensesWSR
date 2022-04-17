package com.example.dailyexpenseswsr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.dailyexpenseswsr.data.GraphicsData
import com.example.dailyexpenseswsr.databinding.ActivityGraphicsBinding
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GraphicsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGraphicsBinding
    private val viewModel: GraphicsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGraphicsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.dataLiveData.observe(this){
            showBarChart(it)
        }

        binding.btnSetDateRange.setOnClickListener {
            val dateRangePicker =
                MaterialDatePicker.Builder.dateRangePicker().setTitleText("Выберите диапазон")
                    .build()

            dateRangePicker.show(supportFragmentManager, "date_range_picker")
            dateRangePicker.addOnPositiveButtonClickListener { datePicked ->
                val firstDateUnix = datePicked.first/1000
                val secondDateUnix = datePicked.second/1000
                viewModel.getDataForGraphics(firstDateUnix, secondDateUnix)
            }
        }

    }

    private fun showBarChart(data: List<GraphicsData>){
        val entries = mutableListOf<BarEntry>()
        data.forEach { item ->
            entries.add(BarEntry((item.date/1000000).toFloat(), item.sumPrice))
        }
        val barDataset = BarDataSet(entries, "Items")
        val barData = BarData(barDataset)
        binding.apply {
            chart.data = barData
            chart.animateY(1000)
            chart.invalidate()
        }
    }
}