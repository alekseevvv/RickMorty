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
import com.artava.rickandmorty.adapter.LocationRecyclerViewAdapter
import com.artava.rickandmorty.model.Location
import com.artava.rickandmorty.databinding.FragmentLocationsBinding

import com.artava.rickandmorty.viewmodel.SharedViewModel

class LocationsFragment : Fragment() {
    val viewModel: SharedViewModel by lazy {
        ViewModelProvider(this).get(SharedViewModel::class.java)
    }
    var numPage = 1
    var end = false
    lateinit var recycler: RecyclerView
    lateinit var adapter: LocationRecyclerViewAdapter
    lateinit var binding: FragmentLocationsBinding

    var totalList = mutableListOf<Location>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_locations, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLocationsBinding.bind(view)
        adapter = LocationRecyclerViewAdapter(totalList)
        recycler = binding.recyclerV
        recycler.layoutManager = LinearLayoutManager(this.requireContext())
        recycler.adapter = adapter
        viewModel.getLocationByPage(numPage)
        viewModel.allLocation.observe(viewLifecycleOwner) { responce ->
            binding.progressBar4.isVisible = false
            if (responce == null) {
                return@observe
            }
            responce.results.let { adapter.updateList(it) }
        }
        recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    numPage++
                    viewModel.getLocationByPage(numPage)
                }
            }
        })
    }
}