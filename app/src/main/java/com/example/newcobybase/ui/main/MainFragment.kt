package com.example.newcobybase.ui.main

import androidx.navigation.fragment.findNavController
import com.example.newcobybase.R
import com.example.newcobybase.base.ui.BaseFragment
import com.example.newcobybase.databinding.FragmentMainBinding

class MainFragment : BaseFragment<FragmentMainBinding>() {

    override val toolbarLayoutId: Int
        get() = NO_TOOLBAR_ID

    override val layoutId: Int
        get() = R.layout.fragment_main

    override fun initEventListeners() {
        binding.btnJsonPlaceHolder.setOnClickListener {
            val action = MainFragmentDirections.actionFragmentMainToFragmentJsonPlaceHolder()
            findNavController().navigate(action)
        }
        binding.btnStackOverFlow.setOnClickListener {
            val action = MainFragmentDirections.actionFragmentMainToFragmentStackOverFlow()
            findNavController().navigate(action)
        }
    }

}