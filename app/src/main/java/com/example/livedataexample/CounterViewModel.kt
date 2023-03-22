package com.example.livedataexample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel(startingNum: Int) : ViewModel() {
    private val counterMutableLiveData= MutableLiveData(0)

    init{
        counterMutableLiveData.value = startingNum
    }

    val counterLiveData: LiveData<Int>
    get() = counterMutableLiveData

    fun incCounter(){
        counterMutableLiveData.value = counterLiveData.value?.plus(1)
    }

    fun decCounter(){
        counterMutableLiveData.value = counterLiveData.value?.minus(1)
    }
}