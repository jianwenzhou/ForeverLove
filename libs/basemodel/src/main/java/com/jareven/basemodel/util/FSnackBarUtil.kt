package com.jareven.basemodel.util

import android.app.Activity
import com.irozon.sneaker.Sneaker

/**
 * SnackBar基类
 */
object FSnackBarUtil {

    /**
     * 展示ErrorSnack
     */
    fun showErrorMsg(act: Activity, title: String, msg: String) {
        Sneaker.with(act)
            .setTitle(title)
            .setMessage(msg)
            .setDuration(2000)
            .sneakError()
    }

    /**
     * 展示wSuccessSnack
     */
    fun showSuccessMsg(act: Activity, title: String, msg: String) {
        Sneaker.with(act)
            .setTitle(title)
            .setMessage(msg)
            .setDuration(2000)
            .sneakSuccess()
    }

    /**
     * 展示WarningSnack
     */
    fun showWarningMsg(act: Activity, title: String, msg: String) {
        Sneaker.with(act)
            .setTitle(title)
            .setMessage(msg)
            .setDuration(2000)
            .sneakWarning()
    }

}
