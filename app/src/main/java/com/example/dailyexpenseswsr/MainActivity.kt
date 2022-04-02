package com.example.dailyexpenseswsr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dailyexpenseswsr.databinding.ActivityMainBinding
import com.example.dailyexpenseswsr.fragments.AddItemFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.floatingActionButton.setOnClickListener{
           val bottomSheet = AddItemFragment(1648925001, "02/04/2022")
           if (!bottomSheet.isAdded) {
               bottomSheet.show(supportFragmentManager, "")
           }
        }
    }
}