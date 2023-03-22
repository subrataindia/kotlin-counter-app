package com.example.livedataexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.livedataexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding
    lateinit var counterViewModel: CounterViewModel
    lateinit var counterViewModelFactory: CounterViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // View Model Factory
        counterViewModelFactory = CounterViewModelFactory(50)

        // View Model
        counterViewModel = ViewModelProvider(this, counterViewModelFactory).get(CounterViewModel::class.java)

//        counterViewModel.counterLiveData.observe(this, Observer {
//            //findViewById<TextView>(R.id.counter).text = (it).toString()
//            activityMainBinding.counter.text = (it).toString()
//        })


//        findViewById<Button>(R.id.btnDecrease).setOnClickListener(){
//            counterViewModel.decCounter()
//        }

//        activityMainBinding.btnDecrease.setOnClickListener(){
//            counterViewModel.decCounter()
//        }

//        findViewById<Button>(R.id.btnIncrease).setOnClickListener(){
//            counterViewModel.incCounter()
//        }

//        activityMainBinding.btnIncrease.setOnClickListener(){
//            counterViewModel.incCounter()
//        }

        // Instead of all observers and listeners use below lines if you are using data variable in XML file
        activityMainBinding.counterViewModel = counterViewModel
        activityMainBinding.lifecycleOwner = this
    }
}