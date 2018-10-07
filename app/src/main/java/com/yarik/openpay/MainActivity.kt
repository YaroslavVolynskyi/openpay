package com.yarik.openpay

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var cardsViewModel: CardsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cardsViewModel = ViewModelProviders.of(this).get(CardsViewModel::class.java)

        settingsImageView.setOnClickListener { startActivity(SettingsActivity.getStartIntent(this)) }
        initProfile()
        initCards()
    }

    private fun initProfile() {
        cardsViewModel.loadProfile()
        cardsViewModel.getProfile().observe(this, Observer<Profile> { profile ->
            if (profile != null) {
                nameTextView.text = getString(R.string.first_x1_lastname_x2, profile.firstName, profile.lastName)
                locationTextView.text = getString(R.string.location_x1_x2, profile.location.city, profile.location.country)
                Picasso.get().load(profile.avatar.imageUrl).transform(CropCircleTransformation()).into(profileImageView)
            }
        })
    }

    private fun initCards() {
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
