package com.example.mvvmtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmtest.model.cats.remote.CatsResponse
import com.example.mvvmtest.viewmodel.AnimalsViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private val viewModel2: AnimalsViewModel by lazy {
        //This is not correct vmmv
        AnimalsViewModel()
    }
    private val viewModel: AnimalsViewModel by lazy {
        ViewModelProvider(this,
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    return AnimalsViewModel() as T
                }
            })[AnimalsViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //by default RxJava is single threaded
        // observer -> subscribeTo specific observable
        // observable -> PUSH -> active Observer (!!THIS is important)
        viewModel.getCatsFacts()
            .subscribeOn(Schedulers.io()) // Subscribe in a worker thread for and IO operation
            .observeOn(AndroidSchedulers.mainThread()) // update the UI in the main thread
            .subscribe(
                object: Observer<List<CatsResponse>>{
                    override fun onSubscribe(d: Disposable) {
                        Log.d(TAG, "onSubscribe: $d")
                    }

                    override fun onNext(t: List<CatsResponse>) {
                        Log.d(TAG, "onNext: $t")
                    }

                    override fun onError(e: Throwable) {
                        Log.e(TAG, "onError: ", e)
                    }

                    override fun onComplete() {
                        Log.d(TAG, "onComplete: ")
                    }
                }
            )
    }
}