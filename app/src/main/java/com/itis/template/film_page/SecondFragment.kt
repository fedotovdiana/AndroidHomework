package com.itis.template.film_page


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import com.itis.template.R
import com.itis.template.touch_helpers.ItemTouchCallback
import kotlinx.android.synthetic.main.fragment_second.*

class SecondFragment : Fragment(), AddDialogFragment.DialogListener {

    lateinit var adapter: FilmAdapter
    var count = Data.films.size + 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = FilmAdapter(Data.films) { film ->
            deleteItem(film)
        }
        rv_films.adapter = adapter
        btn_add.setOnClickListener {
            val fragmentTransaction = activity?.supportFragmentManager?.beginTransaction()
            val dialogFragment =
                    AddDialogFragment()
            fragmentTransaction?.let { it1 -> dialogFragment.show(it1, "dialog") }
            dialogFragment.setTargetFragment(this, 1)
        }
        val callback = ItemTouchCallback(adapter)
        val touchHelper = ItemTouchHelper(callback)
        touchHelper.attachToRecyclerView(rv_films)
    }

    private fun deleteItem(film: Film) {
        val newlist = Data.films.toMutableList()
        newlist.remove(film)
        adapter.update(newlist)
        Data.films.remove(film)
    }

    override fun onFinishEditDialog(title: String, country: String, position: Int?) {
        val newList = Data.films.toMutableList()
        if (position == null) {
            newList.add(adapter.itemCount,
                    Film(count++, title, country)
            )
        } else if (position > adapter.itemCount) {
            newList.add(Film(count++, title, country))
        } else if (position < adapter.itemCount) {
            newList.add(position - 1,
                    Film(count++, title, country)
            )
        }
        adapter.update(newList)
    }

    companion object {
        fun newInstance(): SecondFragment = SecondFragment()
    }
}
