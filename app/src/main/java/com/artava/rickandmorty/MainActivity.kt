package com.artava.rickandmorty

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.artava.rickandmorty.fragment.EpisodeFragment
import com.artava.rickandmorty.fragment.ListCharacterFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.rick_fragment, ListCharacterFragment())
            .commit()
        findViewById<BottomNavigationView>(R.id.bottom_navigation).setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.page_1 -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.rick_fragment, ListCharacterFragment())
                        .commit()
                    true
                }
                R.id.page_2 -> {
                    // Respond to navigation item 2 click
                    println("Str2")
                    /*  supportFragmentManager.beginTransaction().replace(R.id.rick_fragment, LocationsFragment())
                          .commit()
  */
                    true
                }
                R.id.page_3 -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.rick_fragment, EpisodeFragment())
                        .commit()
                    true
                }
                else -> false
            }

        }
    }
}