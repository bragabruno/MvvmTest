package com.example.mvvmtest.model.cats.remote

import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET


//https://cat-fact.herokuapp.com/facts

interface CatsApi {
    @GET(END_PONT)
    fun getCatFacts(): Observable<List<CatsResponse>>

    // expected BEGIN_OBJECT but was BEGIN_ARRAY
}
