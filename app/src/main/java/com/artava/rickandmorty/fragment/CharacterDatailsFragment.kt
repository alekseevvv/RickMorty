package com.artava.rickandmorty.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.artava.rickandmorty.R
import com.artava.rickandmorty.databinding.FragmentCharacterBinding
import com.artava.rickandmorty.viewmodel.SharedViewModel
import com.squareup.picasso.Picasso

class CharacterDatailsFragment : Fragment() {
    private var param1: Int? = 1

    lateinit var binding: FragmentCharacterBinding
    val viewModel: SharedViewModel by lazy {
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
        binding = FragmentCharacterBinding.bind(view)
        binding.imBack.setOnClickListener {
                parentFragmentManager.popBackStack()
        }
        viewModel.refreshCharacter(param1!!)
        viewModel.characterByIdLiveData.observe(viewLifecycleOwner) { responce ->
            if (responce == null) {
                return@observe
            }
            binding.tvName.text = responce.full_name.toString()
            binding.tvAlive.text = responce.status
            binding.tvLocation.text = responce.location?.name
            Picasso.get().load(responce.image).into(binding.imgIcon)
            Picasso.get().load(responce.image).into(binding.imgIconSmall)

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

    override fun onPause() {
        //fragmentManager?.beginTransaction()?.remove(this)
        println("remove ${this.id}")

        super.onPause()
    }
}