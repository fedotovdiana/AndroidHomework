package com.itis.template

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var adapter: FilmAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = FilmAdapter(getDataSource()) { title, year, country, img ->
            navigateToDescription(title, year, country, img)
        }

        rv_films.adapter = adapter
    }

    private fun navigateToDescription(title: String, year: String, country: String, img: Int) {
        startActivity(DescriptionActivity.createIntent(this, title, year, country, img))
    }

    private fun getDataSource(): List<Film> = arrayListOf(
            Film("Home Alone", "1999", "USA", R.drawable.homealone),
            Film("Titanic", "1998", "USA", R.drawable.titanic),
            Film("Home Alone", "1999", "USA", R.drawable.homealone),
            Film("Titanic", "1998", "USA", R.drawable.titanic),
            Film("Home Alone", "1999", "USA", R.drawable.homealone),
            Film("Titanic", "1998", "USA", R.drawable.titanic),
            Film("Home Alone", "1999", "USA", R.drawable.homealone),
            Film("Titanic", "1998", "USA", R.drawable.titanic),
            Film("Home Alone", "1999", "USA", R.drawable.homealone),
            Film("Titanic", "1998", "USA", R.drawable.titanic)
    )
}
