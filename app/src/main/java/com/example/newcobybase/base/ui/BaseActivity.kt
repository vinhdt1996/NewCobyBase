package com.example.newcobybase.base.ui

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import com.example.newcobybase.R
import com.example.newcobybase.util.AppEvent
import com.example.newcobybase.util.PopupEventListener
import com.example.newcobybase.vo.PopUp
import com.example.newcobybase.widget.BottomPopupDialog
import com.example.newcobybase.widget.PopupDialog
import com.google.android.material.appbar.AppBarLayout
import java.util.concurrent.CopyOnWriteArraySet

abstract class BaseActivity<Binding : ViewDataBinding> : AppCompatActivity(), PopupEventListener {

    lateinit var binding: Binding

    abstract val layoutId: Int

    private var listPopupDialogFragment: Set<DialogFragment> = CopyOnWriteArraySet()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppEvent.registerPopupEventListener(this)
        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.lifecycleOwner = this

        initViews()
        initObservers()
    }

    override fun onShowPopup(popup: PopUp?) {
        onClosePopup()
        val popupDialogFragment = if (popup?.isBottomSheet == true)
            BottomPopupDialog.newInstance(popup) else PopupDialog.newInstance(popup)
        popupDialogFragment.show(supportFragmentManager, PopupDialog().tag)
        listPopupDialogFragment = listPopupDialogFragment.plus(popupDialogFragment)
    }

    override fun onClosePopup() {
        for (item in listPopupDialogFragment) {
            item.dismissAllowingStateLoss()
            listPopupDialogFragment = listPopupDialogFragment.minus(item)
        }
    }

    fun removeToolbar() {
        (binding.root as? ViewGroup)?.findViewById<AppBarLayout>(R.id.appBarLayout)?.apply {
            (binding.root as? ViewGroup)?.removeView(this)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        AppEvent.unregisterPopupEventListener(this)
    }

    open fun initViews() {}

    open fun initObservers() {}

}