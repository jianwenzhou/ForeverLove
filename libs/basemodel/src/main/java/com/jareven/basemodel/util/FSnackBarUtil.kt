package com.jareven.basemodel.util

import android.app.Activity
import com.irozon.sneaker.Sneaker

/**
 * SnackBar基类
 */
object FSnackBarUtil {


    fun showErrorMsg(act: Activity, title: String, msg: String) {
        Sneaker.with(act)
            .setTitle(title)
            .setMessage(msg)
            .setDuration(2000)
            .sneakError()
    }

    fun showSuccessMsg(act: Activity, title: String, msg: String) {
        Sneaker.with(act)
            .setTitle(title)
            .setMessage(msg)
            .setDuration(2000)
            .sneakSuccess()
    }

    fun showWarningMsg(act: Activity, title: String, msg: String) {
        Sneaker.with(act)
            .setTitle(title)
            .setMessage(msg)
            .setDuration(2000)
            .sneakWarning()
    }

}
