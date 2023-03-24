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
import kotlinx.coroutines.*

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

        activityMainBinding.btnHeavyFun.setOnClickListener(){
            //
            CoroutineScope(Dispatchers.IO).launch{
                heavyFunction()
            }
        }

    }

    private suspend fun heavyFunction(){
        for(i in 1 .. 20){
            delay(1000)
            Log.d("HEAVY","Value of I = $i. Heavy Function in Thread ${Thread.currentThread().name}")
            // If you will not use withContext Then it will through below error
            // Only the original thread that created a view hierarchy can touch its views.
            withContext(Dispatchers.Main) {
                activityMainBinding.tvHeavyFun.setText("Value of I = $i. Heavy Function in Thread ${Thread.currentThread().name}")
            }
            // Now if you look at the logs:
            // - loging the message shows that Heavy Function is running on DefaultDispatcher-worker-1 thread
            // - But the text view message shows that Heavy Function is running on main thread
            // It is because of withContext. During updation of text view it switches to main thread
        }
    }
}