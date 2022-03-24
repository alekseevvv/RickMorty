package com.artava.rickandmorty.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.artava.rickandmorty.viewmodel.ListCharacterViewModel
import com.artava.rickandmorty.CharacterRecyclerViewAdapter
import com.artava.rickandmorty.R
import com.artava.rickandmorty.databinding.ListCharacterFragmentBinding
import com.artava.rickandmorty.model.Character

class ListCharacterFragment : Fragment() {
    val viewModel: ListCharacterViewModel by lazy {
        ViewModelProvider(this).get(ListCharacterViewModel::class.java)
    }
    lateinit var binding: ListCharacterFragmentBinding
    var totalList = mutableListOf<Character>()
    var numPage = 1
    lateinit var recycler: RecyclerView
    lateinit var adapter: CharacterRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_character_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ListCharacterFragmentBinding.bind(view)
        loadNewCharacters()

        recycler = binding.recyclerV
        recycler.layoutManager = LinearLayoutManager(this.requireContext())

        adapter = totalList.let {
            CharacterRecyclerViewAdapter(
                it, requireContext(),
            )
        }
        recycler.adapter = adapter

        recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    numPage++
                    if(binding.editTxtSearch.text.toString() == "")
                    loadNewCharacters()
                }
            }
        })
        
        binding.editTxtSearch.doAfterTextChanged {
            viewModel.getCharacterByName(binding.editTxtSearch.text.toString())
            viewModel.allCharacter.observe(viewLifecycleOwner) { responce ->
                if (responce == null) {
                    return@observe
                } else {
                    adapter = responce.results.let {
                        CharacterRecyclerViewAdapter(
                            it!!, requireContext(),
                        )
                    }
                    recycler.adapter = adapter
                    adapter.onItemClick = { character ->
                        fragmentManager?.beginTransaction()
                            ?.add(
                                R.id.rick_fragment,
                                CharacterDatailsFragment.newInstance(character.id)
                            )
                            ?.addToBackStack(null)
                            ?.commit()
                    }
                }
            }
        }
        
    }

    fun loadNewCharacters(){
        viewModel.getCharacterByPage(numPage)
        viewModel.allCharacter.observe(viewLifecycleOwner) { responce ->
            if (responce == null) {
                return@observe
            }
            println("Page $numPage : ${responce.results}")

            totalList.addAll(responce.results!!)
            adapter.notifyDataSetChanged()
            adapter.onItemClick = { character ->
                fragmentManager?.beginTransaction()
                    ?.add(
                        R.id.rick_fragment,
                        CharacterDatailsFragment.newInstance(character.id)
                    )
                    ?.addToBackStack(null)
                    ?.commit()
            }

        }
    }

}