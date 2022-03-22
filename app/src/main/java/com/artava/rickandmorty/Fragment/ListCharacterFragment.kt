package com.artava.rickandmorty.Fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.artava.rickandmorty.ListCharacterViewModel
import com.artava.rickandmorty.R
import com.artava.rickandmorty.model.Character

class ListCharacterFragment : Fragment() {

    private lateinit var viewModel: ListCharacterViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_character_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var dataList : List<Character?>
        viewModel = ViewModelProvider(this).get(ListCharacterViewModel::class.java)
        viewModel.listAllCharacter()
        viewModel.allCharacter.observe(viewLifecycleOwner){ responce ->
            if (responce == null){
                return@observe
            }
            dataList = responce
            println(dataList[1]?.full_name)
        }
    }

}