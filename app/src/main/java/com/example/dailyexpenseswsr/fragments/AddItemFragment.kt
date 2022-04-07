package com.example.dailyexpenseswsr.fragments

import android.content.ClipData
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dailyexpenseswsr.data.Item
import com.example.dailyexpenseswsr.databinding.FragmentAddItemToBuyBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddItemFragment(val dateUnix: Long, val date: String): BottomSheetDialogFragment() {
    private lateinit var binding: FragmentAddItemToBuyBinding
    private val viewModel: AddItemViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddItemToBuyBinding.inflate(inflater, container, false)

        var selectedCat = ""
        binding.apply {
            val items = listOf(
                "Кафе",
                "Продукты",
                "Транспорт",
                "Онлайн-покупки",
                "Канцелярия",
                "Кинотеатры"
            )
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, items)
            etCategories.setAdapter(adapter)
            etCategories.setOnItemClickListener { _, _, position, _ ->
                selectedCat = items[position]
            }

            btnSave.setOnClickListener {
                val plan = etPlans.text.toString()
                val sum = etSumToBuy.text.toString().toFloat()
                val item = Item(name = plan, price = sum, date = dateUnix, category = selectedCat)
                viewModel.insertItem(item)
            }
        }
        viewModel.insertItemLiveData.observe(viewLifecycleOwner){
            if (it != 0L){
                Toast.makeText(requireContext(), "Данные сохранены!", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(requireContext(), "Ошибка!", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

}