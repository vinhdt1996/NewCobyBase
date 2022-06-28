package com.example.newcobybase.ui.stackOverFlow

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.newcobybase.R
import com.example.newcobybase.base.ui.BaseFragment
import com.example.newcobybase.databinding.FragmentStackOverFlowBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StackOverFlowFragment : BaseFragment<FragmentStackOverFlowBinding>() {

    override val toolbarLayoutId: Int
        get() = R.layout.layout_toolbar

    override val layoutId: Int
        get() = R.layout.fragment_stack_over_flow

    private val viewModel by viewModels<StackOverFlowViewModel>()

    override fun toolbarFunc(curActivity: AppCompatActivity?, toolbar: Toolbar?) {
        toolbar?.run {
            findViewById<AppCompatTextView>(R.id.tvTitle)?.text = "StackOverFlow"
            findViewById<AppCompatImageButton>(R.id.btnBack)?.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerAllExceptionEvent(viewModel, viewLifecycleOwner)
        registerObserverLoadingEvent(viewModel, viewLifecycleOwner)
    }

    override fun initObservers() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.listQuestion.collect { questions ->
                    if (questions.isNotEmpty()) {
                        val titles = questions.map { "${it.title}\n" }
                        binding.tvResult.text = titles.toString()
                    }
                }
            }
        }
    }
}