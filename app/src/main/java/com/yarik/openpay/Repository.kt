package com.yarik.openpay

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class Repository() {

    private val apiInterface: ApiInterface = DataManager.getApiInterface()

    fun getProfile() : Single<Profile> {
        return apiInterface
                .getBasicProfile()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun getCards() :Single<CardsResponse> {
        return apiInterface
                .getCards()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}