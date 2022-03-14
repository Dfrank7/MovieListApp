package com.francis.moviestest.base

import androidx.lifecycle.ViewModel

abstract class BaseViewModel<out T: IBaseRepository>(private val repository: T): ViewModel() {

    override fun onCleared() {
        repository.clear()
        super.onCleared()
    }
}