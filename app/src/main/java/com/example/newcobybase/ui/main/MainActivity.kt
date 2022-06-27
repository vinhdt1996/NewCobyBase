package com.example.newcobybase.ui.main

import com.example.newcobybase.R
import com.example.newcobybase.base.ui.BaseActivity
import com.example.newcobybase.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val layoutId: Int
        get() = R.layout.activity_main
}