package com.itis.template

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.itis.template.ui.FriendsFragment
import com.itis.template.ui.MusicFragment
import com.itis.template.ui.PhotosFragment
import com.itis.template.ui.ProfileFragment
import kotlinx.android.synthetic.main.activity_nav.*
import kotlinx.android.synthetic.main.app_bar_nav.*

class NavActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav)

//        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

//        val navView: NavigationView = findViewById(R.id.nav_view)
        nav_view.setNavigationItemSelectedListener(this)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
		setFragment(ProfileFragment)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.nav, menu)
        return true
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.nav_profile -> {
                supportActionBar?.title = "Profile"
                setFragment(ProfileFragment)
            }
            R.id.nav_music -> {
                supportActionBar?.title = "Music"
                 setFragment(MusicFragment)
            }
            R.id.nav_friends -> {
                supportActionBar?.title = "Friends"
                 setFragment(FriendsFragment)
            }
            R.id.nav_photos -> {
                supportActionBar?.title = "Photos"
                setFragment(PhotosFragment)
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
	
	private fun setFragment(fragment: Fragment) {
		supportFragmentManager.also {
			it.beginTransaction().apply {
				replace(R.id.container, fragment.newInstance())
				addToBackStack(null)
				commit()
			}
		}
	}

    override fun onBackPressed() {
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        when {
            drawer.isDrawerOpen(GravityCompat.START) -> drawer.closeDrawer(GravityCompat.START)
            supportFragmentManager.backStackEntryCount > 0 -> supportFragmentManager.popBackStack()
            else -> super.onBackPressed()
        }
    }
}
