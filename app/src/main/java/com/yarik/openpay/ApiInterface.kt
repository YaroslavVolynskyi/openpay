package com.yarik.openpay

import io.reactivex.Single
import retrofit2.http.GET

interface ApiInterface {


    @GET("/openpay-mobile-test/profile.json")
    fun getBasicProfile(): Single<Profile>

    @GET("/openpay-mobile-test/cards.json")
    fun getCards(): Single<Array<Card>>
}