package com.itis.template

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_description.*

class DescriptionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)
        val title = intent?.extras?.getString(KEY_TITLE) ?: DEFAULT_NAME
        val year = intent?.extras?.getString(KEY_YEAR) ?: DEFAULT_NAME
        val country = intent?.extras?.getString(KEY_COUNTRY) ?: DEFAULT_NAME
        val img = intent?.extras?.getInt(KEY_IMG) ?: R.drawable.homealone
        tv_desc_title.text = title
        tv_desc_year.text = year
        tv_desc_country.text = country
        iv_desc.setImageResource(img)
    }

    companion object {

        private const val DEFAULT_NAME = "DEFAULT NAME"
        private const val KEY_TITLE = "title"
        private const val KEY_YEAR = "year"
        private const val KEY_COUNTRY = "country"
        private const val KEY_IMG = "img"

        fun createIntent(activity: Activity, title: String, year: String, country: String, img: Int) =
                Intent(activity, DescriptionActivity::class.java).apply {
                    putExtra(KEY_TITLE, title)
                    putExtra(KEY_YEAR, year)
                    putExtra(KEY_COUNTRY, country)
                    putExtra(KEY_IMG, img)
                }
    }

}
