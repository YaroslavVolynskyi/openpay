package com.yarik.openpay

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var cardsViewModel: CardsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        cardsViewModel = ViewModelProviders.of(this).get(CardsViewModel::class.java)
        cardsViewModel.getProfile().observe(this, Observer<Profile> { nameTextView.text = it!!.firstName })
        cardsViewModel.loadProfile()

        initCards()
    }

    fun initCards() {
        cardsViewModel.loadCards()
        cardsViewModel.getCards().observe(this, Observer<List<Card>> { cardsList ->
            val horizontalLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            val adapter = CardsAdapter()
            cardsRecyclerView.layoutManager = horizontalLayoutManager
            cardsRecyclerView.adapter = adapter
            adapter.setCards(cardsList)
        })
    }
}
