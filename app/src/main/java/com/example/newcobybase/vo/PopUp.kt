package com.example.newcobybase.vo

import android.app.Dialog
import android.os.Parcelable
import android.view.View
import androidx.databinding.ViewDataBinding
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class PopUp(
    var popupId: Int = 0,
    var isBottomSheet: Boolean = false,
    var isCancelable: Boolean = true,
    var isMatchParent: Boolean = true,
    var popupBinding: @RawValue ViewDataBinding? = null,
    var callback: @RawValue ((popupBinding: ViewDataBinding?, view: View?, dialog: Dialog?) -> Unit)? = null
) : Parcelable