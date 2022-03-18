package com.artava.rickandmorty

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        supportFragmentManager.beginTransaction().add(R.id.rick_fragment, CharacterFragment())
            .commit()
        findViewById<BottomNavigationView>(R.id.bottom_navigation).setOnItemSelectedListener {
            item ->
            when(item.itemId){
                R.id.page_1 -> {
                    // Respond to navigation item 1 click
                    println("Str1")
                    supportFragmentManager.beginTransaction().replace(R.id.rick_fragment, CharacterFragment())
                        .commit()
                    true
                }
                R.id.page_2 -> {
                    // Respond to navigation item 2 click
                    println("Str2")
                    supportFragmentManager.beginTransaction().replace(R.id.rick_fragment, LocationsFragment())
                        .commit()

                    true
                }
                R.id.page_3 -> {
                    // Respond to navigation item 2 click
                    println("Str3")
                    supportFragmentManager.beginTransaction().replace(R.id.rick_fragment, EpisodesFragment())
                        .commit()

                    true
                }
                else -> false
            }

        }
    }
}