package com.itis.template.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.itis.template.R
import kotlinx.android.synthetic.main.fragment_profile.*

/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        (activity as AppCompatActivity).supportActionBar?.setTitle(R.string.title_profile)
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btn_to_settings.setOnClickListener {
            fragmentManager.also {
                it?.beginTransaction()?.apply {
                    setCustomAnimations(R.animator.fade_in,
                            R.animator.fade_out)
                    replace(R.id.container,
                            SettingsFragment.newInstance()
                    )
                    addToBackStack(null)
                    commit()
                }
            }
        }
    }

    companion object {
        fun newInstance(): ProfileFragment = ProfileFragment()
    }

}
