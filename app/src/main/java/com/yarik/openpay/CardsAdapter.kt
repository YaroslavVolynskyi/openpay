package com.yarik.openpay

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_itemview.view.*
import android.graphics.Point
import android.view.WindowManager

class CardsAdapter : RecyclerView.Adapter<CardsAdapter.CardsViewHolder>() {

    private var cards: List<Card>? = null

    override fun onBindViewHolder(viewHolder: CardsViewHolder, position: Int) {
        viewHolder.bind(cards!![position])
    }

    override fun onCreateViewHolder(view: ViewGroup, viewType: Int): CardsViewHolder {
        val view = LayoutInflater.from(view.context).inflate(R.layout.card_itemview, view, false)

//        val wm = view.context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
//        val point = Point()
//        wm.defaultDisplay.getSize(point)
//        val layoutParams = ViewGroup.LayoutParams(point.x, point.y)
//        view.layoutParams = ViewGroup.LayoutParams((view.width * 0.5).toInt(), view.height)

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
            Picasso.get().load(card.imageUrl).into(itemView.cardImageView)
        }
    }
}