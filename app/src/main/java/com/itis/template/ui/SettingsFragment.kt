package com.itis.template.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.itis.template.R
import kotlinx.android.synthetic.main.fragment_settings.*

/**
 * A simple [Fragment] subclass.
 */
class SettingsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btn_to_result.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.apply {
				setCustomAnimations(R.animator.enter_from_left,
						R.animator.enter_from_right)
				replace(R.id.container,
						ResultFragment.newInstance(
								et_name.text.toString(),
								et_surname.text.toString()
						)
				)
				addToBackStack(null)
				commit()
            }
        }
    }

    companion object {
        fun newInstance(): ProfileFragment = ProfileFragment()
    }
}
