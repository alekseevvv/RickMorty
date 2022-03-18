package com.artava.rickandmorty

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.artava.rickandmorty.databinding.FragmentCharacterBinding
import com.squareup.picasso.Picasso

class CharacterDatailsFragment : Fragment() {
    lateinit var binding: FragmentCharacterBinding
    val viewModel: SharedViewModel by lazy {
        ViewModelProvider(this).get(SharedViewModel::class.java)
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
        binding = FragmentCharacterBinding.bind(view)
        viewModel.refreshCharacter(2)
        viewModel.characterByIdLiveData.observe(viewLifecycleOwner){ responce ->
            if (responce == null){
                return@observe
            }
            binding.tvName.text = responce.full_name.toString()
            binding.tvAlive.text = responce.status
            binding.tvLocation.text = responce.location?.name
            Picasso.get().load(responce.image).into(binding.imgIcon);
        }
    }
}