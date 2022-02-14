package com.example.mvvmtest.viewmodel

import androidx.lifecycle.ViewModel
import com.example.mvvmtest.model.cats.remote.CatsResponse
import com.example.mvvmtest.model.common.Network
import io.reactivex.rxjava3.core.Observable

class AnimalsViewModel: ViewModel() {

    fun getCatsFacts(): Observable<List<CatsResponse>> {
        return Network.catsApi.getCatFacts()
    }
}