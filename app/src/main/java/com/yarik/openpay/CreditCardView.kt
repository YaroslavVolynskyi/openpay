package com.yarik.openpay

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView

class CreditCardImageView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
        : ImageView(context, attrs, defStyleAttr) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure((widthMeasureSpec * 0.8).toInt(), heightMeasureSpec)
    }
}