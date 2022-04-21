package com.artava.rickandmorty.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.artava.rickandmorty.R
import com.artava.rickandmorty.adapter.EpisodeRecyclerViewAdapter
import com.artava.rickandmorty.databinding.FragmentCharacterBinding
import com.artava.rickandmorty.db.CharacterDB
import com.artava.rickandmorty.model.Character
import com.artava.rickandmorty.model.Episode
import com.artava.rickandmorty.viewmodel.SharedViewModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharacterDatailsFragment : Fragment() {
    var totalList = mutableListOf<Episode>()

    private var param1: Int? = 1
    lateinit var recycler: RecyclerView
    lateinit var adapter: EpisodeRecyclerViewAdapter
    lateinit var binding: FragmentCharacterBinding
    var listNum = mutableListOf<Int>()
    var listEp = listOf<String>()
    lateinit var thisCharacter: Character

    lateinit var chDB : CharacterDB

    val viewModel: SharedViewModel by lazy {
        ViewModelProvider(this).get(SharedViewModel::class.java)
    }
    val viewModelEp: SharedViewModel by lazy {
        ViewModelProvider(this).get(SharedViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt("chId")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_character, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dao = CharacterDB.getDataseCharacter(this.requireContext()).characterDao()

        binding = FragmentCharacterBinding.bind(view)
        adapter = EpisodeRecyclerViewAdapter(totalList)
        recycler = binding.epList.recyclerV
        recycler.layoutManager = LinearLayoutManager(this.requireContext())
        recycler.adapter = adapter

        binding.imBack.setOnClickListener {
                parentFragmentManager.popBackStack()
        }

        binding.btnStar.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                viewModel.insertCharacter(thisCharacter, dao)
            }
        }

        viewModel.refreshCharacter(param1!!)
        viewModel.characterByIdLiveData.observe(viewLifecycleOwner) { responce ->
            if (responce == null) {
                return@observe
            }
            thisCharacter = responce
            listEp = responce.episode!!
            for (i in 1..listEp.size){
                val last = listEp[i-1].substringAfterLast('/')
                listNum.add(last.toInt())
            }

            binding.tvName.text = responce.full_name.toString()
            binding.tvAlive.text = responce.status
            if (responce.status == "unknown") {
                binding.tvAlive.setTextColor(ContextCompat.getColor(requireContext(), R.color.text_gray));
            }
            if (responce.status == "Dead") {
                binding.tvAlive.setTextColor(ContextCompat.getColor(requireContext(), R.color.text_red));
            }
            if (responce.status == "Alive") {
                binding.tvAlive.setTextColor(ContextCompat.getColor(requireContext(), R.color.text_green));
            }
            binding.tvLocation.text = responce.location?.name
            Picasso.get().load(responce.image).into(binding.imgIcon)
            Picasso.get().load(responce.image).into(binding.imgIconSmall)

            addListEpisode()
        }
    }

    fun addListEpisode(){
        viewModelEp.getEpisodeByName(listNum)
        viewModelEp.episode.observe(viewLifecycleOwner) { responce ->
            if (responce == null) {
                return@observe
            }
            binding.epList.progressBar3.isVisible = false
            responce.let { adapter.updateList(it) }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: Int?) =
            CharacterDatailsFragment().apply {
                arguments = Bundle().apply {
                    if (param1 != null) {
                        putInt("chId", param1)
                    }
                }
            }
    }
}