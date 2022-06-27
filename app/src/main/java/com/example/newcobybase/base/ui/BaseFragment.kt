package com.example.newcobybase.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import com.example.newcobybase.common.EventObserver
import com.example.newcobybase.common.PopupUtil
import com.example.newcobybase.common.addToolbar

abstract class BaseFragment<Binding : ViewDataBinding> : Fragment() {

    companion object {
        const val NO_TOOLBAR_ID = -1
    }

    lateinit var binding: Binding

    abstract val layoutId: Int

    open val toolbarLayoutId: Int = NO_TOOLBAR_ID

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this

        initToolbar()
        initViews()
        initObservers()
        initEventListeners()
    }

    private fun initToolbar(toolbarLayoutId: Int = this.toolbarLayoutId) {
        if (toolbarLayoutId == NO_TOOLBAR_ID) {
            (activity as? BaseActivity<*>)?.removeToolbar()
            return
        }
        (activity as? BaseActivity<*>)?.addToolbar(
            toolbarLayoutId = toolbarLayoutId,
            viewGroup = (activity as? BaseActivity<*>)?.binding?.root as? ViewGroup,
            toolbarCallback = { curActivity, toolbar ->
                toolbarFunc(curActivity, toolbar)
            }
        )
    }

    open fun toolbarFunc(curActivity: AppCompatActivity?, toolbar: Toolbar?) {}

    open fun initViews() {}

    open fun initObservers() {}

    open fun initEventListeners() {}

    open fun onClick(v: View?) {}

    protected fun showLoading(isShow: Boolean) {
        if (isShow) {
            PopupUtil.showLoading()
            return
        }
        PopupUtil.hideAllPopup()
    }

    protected fun registerAllExceptionEvent(
        viewModel: BaseViewModel,
        viewLifecycleOwner: LifecycleOwner
    ) {
        registerObserverExceptionEvent(viewModel, viewLifecycleOwner)
        registerObserverNetworkExceptionEvent(viewModel, viewLifecycleOwner)
        registerObserverMessageEvent(viewModel, viewLifecycleOwner)
    }

    protected fun registerObserverLoadingEvent(
        viewModel: BaseViewModel,
        viewLifecycleOwner: LifecycleOwner
    ) {
        viewModel.isLoading.observe(viewLifecycleOwner, EventObserver { isShow ->
            showLoading(isShow)
        })
    }

    protected fun registerObserverExceptionEvent(
        viewModel: BaseViewModel,
        viewLifecycleOwner: LifecycleOwner
    ) {
        viewModel.baseNetworkException.observe(viewLifecycleOwner, EventObserver {
            PopupUtil.showPopupError(it.responseMessage ?: "Unknown error")
        })
    }

    protected fun registerObserverNetworkExceptionEvent(
        viewModel: BaseViewModel,
        viewLifecycleOwner: LifecycleOwner
    ) {
        viewModel.networkException.observe(viewLifecycleOwner, EventObserver {
            PopupUtil.showPopupError(it.message ?: "Unknown error")
        })
    }

    protected fun registerObserverMessageEvent(
        viewModel: BaseViewModel,
        viewLifecycleOwner: LifecycleOwner
    ) {
        viewModel.errorMessageResourceId.observe(viewLifecycleOwner, EventObserver { message ->
            PopupUtil.showPopupError(message.toString())

        })
    }
}