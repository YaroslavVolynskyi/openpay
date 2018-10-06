package com.yarik.openpay

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var cardsViewModel: CardsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cardsViewModel = ViewModelProviders.of(this).get(CardsViewModel::class.java)
        cardsViewModel.getProfile().observe(this, Observer<Profile> { profileName.text = it!!.firstName })
        cardsViewModel.loadProfile()
    }
}
