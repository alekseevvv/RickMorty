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
import com.artava.rickandmorty.adapter.EpisodeRecyclerViewAdapter
import com.artava.rickandmorty.R
import com.artava.rickandmorty.databinding.EpisodeFragmentBinding
import com.artava.rickandmorty.model.Episode
import com.artava.rickandmorty.viewmodel.SharedViewModel

class EpisodeFragment : Fragment() {
    val viewModel: SharedViewModel by lazy {
        ViewModelProvider(this).get(SharedViewModel::class.java)
    }
    var numPage = 1
    lateinit var recycler: RecyclerView
    lateinit var adapter: EpisodeRecyclerViewAdapter
    lateinit var binding: EpisodeFragmentBinding

    var totalList = mutableListOf<Episode>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.episode_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = EpisodeFragmentBinding.bind(view)
        adapter = EpisodeRecyclerViewAdapter(totalList)
        recycler = binding.recyclerV
        recycler.layoutManager = LinearLayoutManager(this.requireContext())
        recycler.adapter = adapter
        viewModel.getEpisodeByPage(numPage)
        viewModel.allEpisode.observe(viewLifecycleOwner) { responce ->
            if (responce == null) {
                return@observe
            }
            binding.progressBar3.isVisible = false
            responce.results?.let { adapter.updateList(it) }

        }
        recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    numPage++
                    viewModel.getEpisodeByPage(numPage)
                }
            }
        })
    }
}