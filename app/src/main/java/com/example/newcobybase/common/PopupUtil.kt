package com.example.newcobybase.common

import androidx.databinding.ViewDataBinding
import com.example.newcobybase.R
import com.example.newcobybase.databinding.LayoutPopupBinding
import com.example.newcobybase.listener.PopUpListener
import com.example.newcobybase.util.AppEvent
import com.example.newcobybase.vo.PopUp

object PopupUtil {
    fun showLoading() {
        AppEvent.notifyShowPopUp()
    }

    fun hideAllPopup() {
        AppEvent.notifyClosePopUp()
    }

    fun showPopupError(msg: String) {
        AppEvent.notifyShowPopUp(
            PopUp(
                R.layout.layout_popup,
                callback = { binding: ViewDataBinding?, _, _ ->
                    (binding as? LayoutPopupBinding)?.apply {
                        title = "Error"
                        message = msg
                        center = "Close"
                        clickListener = PopUpListener(
                            clickCenterListener = { AppEvent.notifyClosePopUp() }
                        )
                    }
                }
            )
        )
    }

    fun showPopupConfirm(msg: String, onConfirm: () -> Unit) {
        AppEvent.notifyShowPopUp(
            PopUp(
                R.layout.layout_popup,
                callback = { binding: ViewDataBinding?, _, _ ->
                    (binding as? LayoutPopupBinding)?.apply {
                        message = msg
                        left = "Close"
                        right = "Confirm"
                        clickListener = PopUpListener(
                            clickLeftListener = { AppEvent.notifyClosePopUp() },
                            clickRightListener = {
                                AppEvent.notifyClosePopUp()
                                onConfirm.invoke()
                            }
                        )
                    }
                }
            )
        )
    }
}