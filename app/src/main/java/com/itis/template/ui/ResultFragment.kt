package com.itis.template.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.itis.template.R
import kotlinx.android.synthetic.main.fragment_result.*

/**
 * A simple [Fragment] subclass.
 */
class ResultFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        tv_res_name.text = arguments?.getString("NAME")
        tv_res_surname.text = arguments?.getString("SURNAME")
    }

    companion object {
        private const val NAME = "Ivan"
        private const val SURNAME = "Ivanov"

        fun newInstance(name: String, surname: String): ProfileFragment = ProfileFragment().apply {
            arguments = Bundle().apply {
                putString(NAME, name)
                putString(SURNAME, surname)
            }
        }
    }

}
