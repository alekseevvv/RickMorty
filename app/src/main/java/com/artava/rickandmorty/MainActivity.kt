package com.artava.rickandmorty

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.artava.rickandmorty.fragment.EpisodeFragment
import com.artava.rickandmorty.fragment.ListCharacterFragment
import com.artava.rickandmorty.fragment.LocationsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    var cur = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction()
            .add(R.id.rick_fragment, ListCharacterFragment())
            .commit()

        findViewById<BottomNavigationView>(R.id.bottom_navigation).setOnItemSelectedListener { item ->

            when (item.itemId) {
                R.id.page_1 -> {
                    if (cur != 0) {
                        cur = 0
                        fragmentManager.beginTransaction()
                            .replace(R.id.rick_fragment, ListCharacterFragment())
                            .addToBackStack(null)
                            .commit()
                        true
                    } else
                        true

                }
                R.id.page_2 -> {
                    if (cur != 1) {
                        cur = 1
                        fragmentManager.beginTransaction()
                            .replace(R.id.rick_fragment, LocationsFragment())
                            .addToBackStack(null)
                            .commit()
                        true
                    } else
                        true
                }
                R.id.page_3 -> {
                    if (cur != 2) {
                        cur = 2
                        fragmentManager.beginTransaction()
                            .replace(R.id.rick_fragment, EpisodeFragment())
                            .addToBackStack(null)
                            .commit()

                        true
                    } else {
                        true
                    }
                }
                else -> false
            }
        }
    }

    override fun onBackPressed() {
        val count: Int = supportFragmentManager.backStackEntryCount
        if (cur == 0 && count == 0){
            for (i in 0 until count) {
                supportFragmentManager.popBackStack()
            }
        }
        cur = 0
        for (i in 0 until count - 1) {
            supportFragmentManager.popBackStack()
        }
        super.onBackPressed()
        findViewById<BottomNavigationView>(R.id.bottom_navigation).selectedItemId = R.id.page_1
    }
}