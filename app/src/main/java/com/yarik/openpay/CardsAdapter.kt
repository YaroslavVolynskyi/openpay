package com.yarik.openpay

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.card_itemview.view.*

class CardsAdapter : RecyclerView.Adapter<CardsAdapter.CardsViewHolder>() {

    private var cards: List<Card>? = null

    override fun onBindViewHolder(viewHolder: CardsViewHolder, position: Int) {
        viewHolder.bind(cards!![position])
    }

    override fun onCreateViewHolder(view: ViewGroup, viewType: Int): CardsViewHolder {
        val view = LayoutInflater.from(view.context).inflate(R.layout.card_itemview, view, false)
        return CardsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (cards != null) {
            cards!!.size
        } else {
            0
        }
    }

    fun setCards(cards: List<Card>?) {
        this.cards = cards
        notifyDataSetChanged()
    }

    class CardsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(card: Card) {
            itemView.cardNumber.text = "card test"
        }
    }
}