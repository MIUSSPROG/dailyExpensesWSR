package com.example.dailyexpenseswsr.fragments

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.dailyexpenseswsr.data.GraphicsData
import com.example.dailyexpenseswsr.data.Item
import com.example.dailyexpenseswsr.data.ItemDao
import com.example.dailyexpenseswsr.repository.Repository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class AddItemViewModel @ViewModelInject constructor(private val dao: ItemDao): ViewModel() {

    private val _inserItemLiveData = MutableLiveData<Long>()
    var insertItemLiveData: LiveData<Long> = _inserItemLiveData
    val itemsLiveData = MutableLiveData<List<Item>>()
    val items2LiveData = dao.getItems().asLiveData()
    val graphicsLiveData = MutableLiveData<List<GraphicsData>>()

    fun insertItem(item: Item){
        viewModelScope.launch {
            val response = dao.insert(item)
            _inserItemLiveData.postValue(response)
        }
    }

    fun getAllItems(pickDate: Long){
        viewModelScope.launch {
            dao.getAllItems(pickDate).collect {
                itemsLiveData.postValue(it)
            }
        }
    }

    fun getAllItemsInRange(fromDate: Long, toDate: Long){
        viewModelScope.launch {
            dao.getAllItemsInRange(fromDate, toDate).collect {
                graphicsLiveData.postValue(it)
            }
        }
    }
}