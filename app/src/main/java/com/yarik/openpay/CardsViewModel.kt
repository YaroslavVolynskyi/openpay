package com.yarik.openpay

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import io.reactivex.disposables.Disposable

class CardsViewModel() : ViewModel() {

    private val repository: Repository = Repository()

    private val profile: MutableLiveData<Profile> = MutableLiveData()
    private val cards: MutableLiveData<List<Card>> = MutableLiveData()

    fun getProfile(): MutableLiveData<Profile> {
        return profile
    }

    fun getCards(): MutableLiveData<List<Card>> {
        return cards
    }

    fun loadProfile() {
        val disposable: Disposable = repository.getProfile()
                .subscribe({ profileResponse -> profile.value = profileResponse },
                        { throwable -> Log.e("error loading profile", throwable.message) })
    }

    fun loadCards() {
        val disposable: Disposable = repository.getCards()
                .subscribe({ cardsResponse -> cards.value = cardsResponse.cards.toList() },
                        { throwable -> Log.e("error loading cards", throwable.message) })
    }
}