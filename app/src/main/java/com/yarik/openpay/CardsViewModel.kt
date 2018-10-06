package com.yarik.openpay

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import io.reactivex.disposables.Disposable

class CardsViewModel() : ViewModel() {

    private val profile: MutableLiveData<Profile> = MutableLiveData()
    private val repository: Repository = Repository()

    fun getProfile(): MutableLiveData<Profile> {
        return profile
    }

    fun loadProfile() {
        val disposable: Disposable = repository.getProfile()
                .subscribe({ profileResponse -> profile.value = profileResponse },
                        { throwable -> Log.e("error", throwable.message) })
    }
}