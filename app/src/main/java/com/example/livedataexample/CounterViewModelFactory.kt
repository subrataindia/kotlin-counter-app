package com.example.livedataexample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CounterViewModelFactory(private val startingNum: Int): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CounterViewModel::class.java)){
            return CounterViewModel(startingNum) as T
        }
        throw IllegalAccessException("Unknown view model calss")
    }
}