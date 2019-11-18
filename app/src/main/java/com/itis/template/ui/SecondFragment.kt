package com.itis.template.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.itis.template.Film
import com.itis.template.FilmAdapter
import com.itis.template.R
import kotlinx.android.synthetic.main.fragment_second.*

class SecondFragment : Fragment() {

    private var adapter: FilmAdapter? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = FilmAdapter(getDataSource())
        rv_films.adapter = adapter
    }

    companion object {
        fun newInstance(): SecondFragment = SecondFragment()
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
