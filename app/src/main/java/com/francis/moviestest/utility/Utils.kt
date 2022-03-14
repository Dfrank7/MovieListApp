package com.francis.moviestest.utility

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

fun loadPicture(context: View, path: String, imageView: ImageView){
    val img_url = "https://image.tmdb.org/t/p/w500"
    val poster_url = img_url+path
    Glide.with(context).load(poster_url).into(imageView)
}