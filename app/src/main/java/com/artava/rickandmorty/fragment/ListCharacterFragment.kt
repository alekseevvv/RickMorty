package com.artava.rickandmorty.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.artava.rickandmorty.R
import com.artava.rickandmorty.adapter.CharacterRecyclerViewAdapter
import com.artava.rickandmorty.databinding.ListCharacterFragmentBinding
import com.artava.rickandmorty.model.Character
import com.artava.rickandmorty.viewmodel.SharedViewModel

class ListCharacterFragment : Fragment() {
    val viewModel: SharedViewModel by lazy {
        ViewModelProvider(this).get(SharedViewModel::class.java)
    }
    var totalList = mutableListOf<Character>()
    lateinit var binding: ListCharacterFragmentBinding
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

        adapter = CharacterRecyclerViewAdapter(totalList, requireContext())
        recycler = binding.recyclerV
        recycler.layoutManager = LinearLayoutManager(this.requireContext())
        recycler.adapter = adapter
        viewModel.getCharacterByPage(numPage)
        viewModel.allCharacter.observe(viewLifecycleOwner) { responce ->
            if (responce == null) {
                return@observe
            }
            binding.progressBar.isVisible = false
            responce.results?.let { adapter.updateList(it) }

        }

        adapter.onItemClick = { character ->
            fragmentManager?.beginTransaction()
                ?.replace(
                    R.id.rick_fragment,
                    CharacterDatailsFragment.newInstance(character.id)
                )
                ?.addToBackStack(null)
                ?.commit()
        }
        
        recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    if (binding.editTxtSearch.text.toString() == "") {
                        numPage++
                        viewModel.getCharacterByPage(numPage)
                    }
                }
            }
        })
        /*binding.editTxtSearch.doAfterTextChanged {
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
        }*/
    }

}