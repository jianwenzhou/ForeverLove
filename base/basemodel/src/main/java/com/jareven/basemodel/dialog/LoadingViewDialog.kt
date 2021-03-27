package com.jareven.basemodel.dialog

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.blankj.utilcode.util.ConvertUtils
import com.jareven.basemodel.R

/**
 * @ClassName LoadingViewDialog
 * @Author zjw
 * @Date 2021/3/20 22:19
 * 简介：LoadingView
 */
open class LoadingViewDialog : DialogFragment() {

    companion object {
        fun getInstance(): LoadingViewDialog {
            return LoadingViewDialog()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_FRAME, R.style.LoadingViewDialogStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.basemodel_dialog_loading_layout, container, true)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }


    override fun onStart() {
        super.onStart()
        val window = dialog?.window
        val params = window?.attributes
        params?.gravity = Gravity.CENTER
        params?.width = ConvertUtils.dp2px(120f)
        params?.height = ConvertUtils.dp2px(120f)
        window?.attributes = params

    }


}