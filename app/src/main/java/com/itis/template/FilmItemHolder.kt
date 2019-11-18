package com.itis.template

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_film.*


class FilmItemHolder(
        override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(film: Film) {
        tv_title.text = film.title
        tv_year.text = film.year
        tv_country.text = film.country
        iv_preview.setImageResource(film.img)
    }

    fun update(bundle: Bundle) {
        for (key in bundle.keySet()) {
            if (key != "KEY_COUNTRY") {
                tv_country.text = bundle.getString("KEY_COUNTRY")
            }
            if (key != "KEY_YEAR") {
                tv_country.text = bundle.getString("KEY_YEAR")
            }
            if (key != "KEY_IMG") {
                tv_country.text = bundle.getString("KEY_IMG")
            }
        }
    }

    companion object {

        fun create(parent: ViewGroup) =
                FilmItemHolder(
                        LayoutInflater.from(parent.context).inflate(R.layout.item_film, parent, false)
                )
    }
}
