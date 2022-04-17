package com.example.dailyexpenseswsr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.applandeo.materialcalendarview.EventDay
import com.example.dailyexpenseswsr.databinding.ActivityMainBinding
import com.example.dailyexpenseswsr.fragments.AddItemFragment
import com.example.dailyexpenseswsr.utils.HelperMethods
import com.example.dailyexpenseswsr.utils.HelperMethods.Companion.convertMillisToDate
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var selectedDateMillis: Long = Calendar.getInstance().timeInMillis

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val events = mutableListOf<EventDay>()
        val calendar = Calendar.getInstance()
        val todayDay = convertMillisToDate(calendar.timeInMillis).split('/')[0].toInt()
        val lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        val calendar1 = Calendar.getInstance()
//        calendar1.add(Calendar.DAY_OF_MONTH, -todayDay+1)
        calendar1.add(Calendar.DAY_OF_MONTH, lastDay - todayDay)
        events.add(EventDay(calendar1, R.drawable.ic_cancel))
        events.add(EventDay(calendar, R.drawable.ic_ok))
        binding.calendarView.setEvents(events)

        binding.floatingActionButton.setOnClickListener{
           val bottomSheet = AddItemFragment(selectedDateMillis/1000, HelperMethods.convertMillisToDate(selectedDateMillis))
           if (!bottomSheet.isAdded) {
               bottomSheet.show(supportFragmentManager, "")
           }
        }

        binding.calendarView.setOnDayClickListener{ eventDay ->
            selectedDateMillis = eventDay.calendar.timeInMillis
        }

        binding.btnShowGraphics.setOnClickListener {
            startActivity(Intent(this, GraphicsActivity::class.java))
        }
    }
}