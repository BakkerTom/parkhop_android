package com.parkhop.parkhop

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.util.Log
import com.parkhop.parkhop.fragment.JournalFragment
import com.parkhop.parkhop.fragment.SearchFragment
import com.parkhop.parkhop.fragment.SettingsFragment
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = "Journal"

        // Load the default fragment
        loadFragment(JournalFragment())
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        val realm = Realm.getDefaultInstance()
        Log.i("Realm", realm.getPath());
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit()
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_journal -> {
                supportActionBar?.title = "Journal"
                loadFragment(JournalFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_discover -> {
                supportActionBar?.title = "Discover"
                loadFragment(SearchFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_settings -> {
                supportActionBar?.title = "Settings"
                loadFragment(SettingsFragment())
                return@OnNavigationItemSelectedListener true
            }
        }
        return@OnNavigationItemSelectedListener false
    }

}
