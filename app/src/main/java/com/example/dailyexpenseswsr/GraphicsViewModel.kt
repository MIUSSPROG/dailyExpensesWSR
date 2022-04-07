package com.example.dailyexpenseswsr

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dailyexpenseswsr.data.GraphicsData
import com.example.dailyexpenseswsr.data.ItemDao
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class GraphicsViewModel @ViewModelInject constructor(private val dao: ItemDao): ViewModel() {

    private val _dataLiveData = MutableLiveData<List<GraphicsData>>()
    val dataLiveData: LiveData<List<GraphicsData>> = _dataLiveData

    fun getDataForGraphics(fromData: Long, toDate: Long){
        viewModelScope.launch {
            dao.getAllItemsInRange(fromData, toDate).collect {
                _dataLiveData.postValue(it)
            }
        }
    }

}