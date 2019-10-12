package com.test.mvvm.view.adapter.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.item_user_info.view.*

class UserInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val avatarImageView: CircleImageView = itemView.image_user
    val loginTextView: TextView = itemView.text_login
    val badgeTextView: TextView = itemView.text_badge
}