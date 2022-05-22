package com.example.lillypoc.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel:ViewModel() {
    val compositeDisposable by lazy { CompositeDisposable() }

    protected val progressBarMutableLiveData by lazy { MutableLiveData<Boolean>() }
    val progressBarLiveData: LiveData<Boolean> by lazy { progressBarMutableLiveData }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
        compositeDisposable.dispose()
    }
}