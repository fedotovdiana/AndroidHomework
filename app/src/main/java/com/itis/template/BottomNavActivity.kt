package com.itis.template

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.itis.template.advert_page.ThirdFragment
import com.itis.template.film_page.SecondFragment
import kotlinx.android.synthetic.main.activity_bottom_nav.*

class BottomNavActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_nav)
        nav_view.setOnNavigationItemSelectedListener(this)

        supportFragmentManager.also {
            it.beginTransaction().apply {
                replace(R.id.nav_host_fragment, FirstFragment.newInstance())
                addToBackStack(FirstFragment::class.java.name)
                commit()
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_first -> {
                val firstFragment = FirstFragment.newInstance()
                loadFragment(firstFragment)
                return true
            }
            R.id.navigation_second -> {
                val secondFragment = SecondFragment.newInstance()
                loadFragment(secondFragment)
                return true
            }
            R.id.navigation_third -> {
                val thirdFragment = ThirdFragment.newInstance()
                loadFragment(thirdFragment)
                return true
            }
        }
        return false
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.also {
            it.beginTransaction().apply {
                replace(R.id.nav_host_fragment, fragment)
                addToBackStack(fragment::class.java.name)
                commit()
            }
        }
    }
}
