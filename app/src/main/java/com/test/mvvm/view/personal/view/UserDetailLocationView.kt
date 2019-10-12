package com.test.mvvm.view.personal.view

import android.content.Context
import android.graphics.Color
import android.graphics.LightingColorFilter
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.test.mvvm.R
import kotlinx.android.synthetic.main.view_user_detail_loc.view.*

class UserDetailLocationView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attributeSet, defStyleAttr) {

    init {
        LayoutInflater.from(this@UserDetailLocationView.context)
            .inflate(R.layout.view_user_detail_loc, this, true)
    }

    fun setData(location: String?) {

        // location pic
        image_location.colorFilter =
            LightingColorFilter(
                Color.WHITE,
                ContextCompat.getColor(this.context, R.color.colorUserDetailImageColor)
            )

        // location text
        location?.run {
            text_location.text = location
        }
    }
}
