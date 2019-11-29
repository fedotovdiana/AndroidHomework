package com.itis.template.advert_page


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.itis.template.R
import kotlinx.android.synthetic.main.fragment_third.*

class ThirdFragment : Fragment() {

    private lateinit var adapter: AdvertAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_third, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = AdvertAdapter(DataAdverts.adverts)
        rv_adverts.adapter = adapter
    }

    companion object {
        fun newInstance(): ThirdFragment = ThirdFragment()
    }
}
