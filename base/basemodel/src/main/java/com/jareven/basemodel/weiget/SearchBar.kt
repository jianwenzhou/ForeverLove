package com.jareven.basemodel.weiget

import android.animation.*
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.text.TextUtils
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.jareven.basemodel.R
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin

/**
 * @ClassName SearchBar
 * @Author zjw
 * @Date 2020/12/14 22:43
 * 简介：
 */
open class SearchBar @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var mWidth = 0
    private var mHeight = 0
    private var mPaint: Paint? = null
    private var mRadius = 0
    private var mOffset = 0
    private var mSumOffset = 0

    //searchBar 显示的文字
    private var mTextHint: String? = null

    //设置icon = 0
    private var mBitmapResId: Int = 0
    private val defaultSize = 40
    private var mDuration = 200
    var currentState =
        State.OPEN
        private set

    enum class State {
        CLOSED,  //收缩状态
        EXPANDING,  //展开和收缩的过程
        OPEN //展开状态
    }

    private fun init() {
        mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mPaint!!.strokeCap = Paint.Cap.ROUND
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec))
        mRadius = Math.min(mWidth, mHeight) / 2
        mSumOffset = mWidth - mRadius * 2 - (paddingLeft + paddingRight)
        mOffset = if (currentState == State.CLOSED) {
            mSumOffset
        } else {
            0
        }
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val right = mWidth - paddingRight
        val left = paddingLeft
        mPaint!!.color = Color.WHITE
        //        RectF rectF = new RectF(left + mOffset, getPaddingTop(), right, mHeight);//向右收缩
        val rectF = RectF(
            left.toFloat(),
            paddingTop.toFloat(),
            (right - mOffset).toFloat(),
            mHeight.toFloat()
        ) //向左收缩
        canvas.drawRoundRect(rectF, mRadius.toFloat(), mRadius.toFloat(), mPaint!!)
        if (currentState == State.OPEN && !TextUtils.isEmpty(
                mTextHint
            )
        ) {
            //绘制文字
            // ascent  绘制普通字符的顶部  // descent底部范围。
            mPaint!!.textSize = 40f
            mPaint!!.color = ContextCompat.getColor(
                context,
                R.color.green_100
            )
            val textBottom =
                mRadius + (mPaint!!.descent() - mPaint!!.ascent()) / 2 - mPaint!!.descent()
            canvas.drawText(mTextHint!!, left + mRadius.toFloat(), textBottom, mPaint!!)
            alpha = 0.6f
        }
        if (currentState == State.CLOSED && mBitmapResId != 0) {
            val bitmap = BitmapFactory.decodeResource(resources, mBitmapResId)
            //计算绘制图片的dstRect
//            float leftF = (float) (mWidth - getPaddingRight() - (mRadius + mRadius * Math.sin(Math.PI * 135 / 180)));//搜索按钮收缩在右侧
            val leftF =
                (paddingLeft + mRadius - mRadius * cos(Math.PI * 45 / 180)).toFloat() //搜索按钮收缩在左侧
            val topF =
                (mRadius - abs(mRadius * cos(Math.PI * 135 / 180))).toFloat()

//            float rightF = (float) (mWidth - getPaddingRight() - (mRadius - mRadius * Math.cos(Math.PI * 45 / 180)));//搜索按钮收缩在右侧
            val rightF =
                (paddingLeft + mRadius + mRadius * sin(Math.PI * 135 / 180)).toFloat() //搜索按钮收缩在左侧
            val bottomF =
                (mRadius + mRadius * sin(Math.PI * 45 / 180)).toFloat()
            val dstRectF = RectF(leftF, topF, rightF, bottomF)
            canvas.drawBitmap(bitmap, null, dstRectF, mPaint)
        }
    }

    private fun measureWidth(widthMeasureSpec: Int): Int {
        val widthMode = MeasureSpec.getMode(widthMeasureSpec) //宽度测量模式
        val widthSize = MeasureSpec.getSize(widthMeasureSpec) //宽度确切数值
        mWidth = if (widthMode == MeasureSpec.EXACTLY) {
            widthSize
        } else {
            defaultSize.coerceAtMost(widthSize)
        }
        return mWidth
    }

    private fun measureHeight(heightMeasureSpec: Int): Int {
        val heightMode = MeasureSpec.getMode(heightMeasureSpec) //高度测量模式
        val heightSize = MeasureSpec.getSize(heightMeasureSpec) //高度确切数值
        mHeight = if (heightMode == MeasureSpec.EXACTLY) {
            heightSize
        } else {
            defaultSize.coerceAtMost(heightSize)
        }
        return mHeight
    }

    /**
     * searchbar展开动画
     */
    fun startAnimation() {
        val startAnimSet = AnimatorSet()
        val alphaAnimator: ValueAnimator = ObjectAnimator.ofFloat(this, "alpha", 1f, 0.6f)
        val startAnimator = ObjectAnimator.ofFloat(1f, 0f)
        startAnimator.addUpdateListener { animation ->
            val animatedValue = animation.animatedValue as Float
            mOffset = (animatedValue * mSumOffset).toInt()
            invalidate()
        }
        startAnimator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator) {
                super.onAnimationStart(animation)
                currentState = State.EXPANDING
            }

            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                currentState = State.OPEN
            }
        })
        //        startAnimator.start();
        startAnimSet.duration = mDuration.toLong()
        startAnimSet.interpolator = LinearInterpolator()
        startAnimSet.playTogether(startAnimator, alphaAnimator)
        startAnimSet.start()
    }

    /**
     * searchbar收缩动画
     */
    fun closeAnimation() {
        val closeAnimSet = AnimatorSet()
        val alphaAnimator: ValueAnimator = ObjectAnimator.ofFloat(this, "alpha", 0.6f, 1f)
        val closeAnimator = ObjectAnimator.ofFloat(0f, 1f)
        closeAnimator.addUpdateListener { animation ->
            val animatedValue = animation.animatedValue as Float
            mOffset = (animatedValue * mSumOffset).toInt()
            invalidate()
        }
        closeAnimator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator) {
                super.onAnimationStart(animation)
                currentState = State.EXPANDING
            }

            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                currentState = State.CLOSED
            }
        })
        closeAnimSet.duration = mDuration.toLong()
        closeAnimSet.interpolator = LinearInterpolator()
        closeAnimSet.playTogether(closeAnimator, alphaAnimator)
        closeAnimSet.start()
    }

    fun setDuration(duration: Int): SearchBar {
        mDuration = duration
        return this
    }

    fun setSearchIcon(@DrawableRes resId: Int): SearchBar {
        mBitmapResId = resId
        return this
    }

    fun setSearchTextHint(textHint: String?): SearchBar {
        mTextHint = textHint
        return this
    }

    init {
        init()
    }
}