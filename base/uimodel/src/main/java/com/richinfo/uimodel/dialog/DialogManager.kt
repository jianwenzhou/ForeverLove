package com.richinfo.uimodel.dialog

import androidx.fragment.app.FragmentActivity

/**
 * @ClassName DialogManager
 * @Author zjw
 * @Date 2021/3/20 22:31
 * 简介：Dialog管理器
 */

object DialogManager {

    private var dialog: LoadingViewDialog = LoadingViewDialog()

    fun showLoadingDialog(activity: FragmentActivity) {
        dialog.let {
            if (!it.isAdded) {
                it.show(activity.supportFragmentManager, "")
            }
        }
    }

    fun dismissLoadingDialog() {
        dialog.let {
            if (!it.isHidden) {
                it.dismiss()
            }
        }
    }


}