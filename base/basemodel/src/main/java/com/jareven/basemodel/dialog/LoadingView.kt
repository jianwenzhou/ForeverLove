package com.jareven.basemodel.dialog

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.jareven.basemodel.R

/**
 * @ClassName LoadingView
 * @Author zjw
 * @Date 2021/4/2 16:45
 * 简介：loading View
 */

class LoadingView : RelativeLayout {

    private lateinit var lottieView: LottieAnimationView

    constructor(context: Context) : this(context, null) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initView()
    }

    private fun initView() {

        val view = View.inflate(context, R.layout.basemodel_loading_lottie_layout, this)

        lottieView = view.findViewById(R.id.lottie_anim_view)
        lottieView.setAnimation(R.raw.uimodel_loading_lottie)
        lottieView.repeatCount = LottieDrawable.INFINITE
    }


    public fun show() {
        lottieView.playAnimation()
        visibility = View.VISIBLE
    }

    public fun dismiss() {
        lottieView.cancelAnimation()
        visibility = View.GONE
    }

}