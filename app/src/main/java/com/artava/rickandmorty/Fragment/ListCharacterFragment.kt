package com.artava.rickandmorty.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.artava.rickandmorty.ListCharacterViewModel
import com.artava.rickandmorty.MyItemRecyclerViewAdapter
import com.artava.rickandmorty.R

class ListCharacterFragment : Fragment() {
    val viewModel: ListCharacterViewModel by lazy {
        ViewModelProvider(this).get(ListCharacterViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_character_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.listAllCharacter()
        viewModel.allCharacter.observe(viewLifecycleOwner){ responce ->
            if (responce == null){
                return@observe
            }
            val recycler = view?.findViewById<RecyclerView>(R.id.recyclerV)

            recycler.adapter = responce.results?.let {
                MyItemRecyclerViewAdapter(
                    it, requireContext()
                )
            }
        }

    }

}