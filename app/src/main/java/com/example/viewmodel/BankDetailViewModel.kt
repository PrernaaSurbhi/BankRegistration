package com.example.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class BankDetailViewModel: ViewModel(){
    var panCardDetail = MutableLiveData<String?>()

    fun panDeatil() {
        val observable: Observable<String> = Observable.just("Aydy123")
                 observable.subscribeOn(Schedulers.io())
        observable.subscribeOn(AndroidSchedulers.mainThread())
                .subscribe{
                    data->
                    panCardDetail.postValue(data)
                }
    }

}