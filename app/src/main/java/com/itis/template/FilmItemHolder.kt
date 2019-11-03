package com.itis.template

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.activity_description.*
import kotlinx.android.synthetic.main.item_film.*

class FilmItemHolder(
        override val containerView: View,
        private val clickLambda: (String, String, String, Int) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(film: Film) {
        tv_title.text = film.title
        tv_year.text = film.year
        tv_country.text = film.country
        iv_preview.setImageResource(film.img)
        itemView.setOnClickListener {
            clickLambda(film.title, film.year, film.country, film.img)
        }
    }

    companion object {

        fun create(parent: ViewGroup, clickLambda: (String, String, String, Int) -> Unit) =
                FilmItemHolder(
                        LayoutInflater.from(parent.context).inflate(R.layout.item_film, parent, false),
                        clickLambda
                )
    }
}
