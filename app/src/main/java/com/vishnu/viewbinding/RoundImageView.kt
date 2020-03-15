package com.vishnu.viewbinding

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.util.DisplayMetrics
import androidx.appcompat.widget.AppCompatImageView

class RoundImageView : AppCompatImageView {

    private var path = Path()
    private var rect = RectF()
    private var cornerRadius = 0f

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(attrs)
    }

    constructor(context: Context) : super(context) {
        init(null)
    }

    private fun init(attrs: AttributeSet?) {
        readAttrs(attrs)
    }

    var radius: Int = 0
        set(value) {
            field = value
            cornerRadius = dpToPx(context, field)
            invalidate()
        }
        get() {
            return cornerRadius.toInt()
        }


    override fun dispatchDraw(canvas: Canvas) {
        makeRound(canvas)
        super.dispatchDraw(canvas)
    }


    override fun onDraw(canvas: Canvas) {
        makeRound(canvas)
        super.onDraw(canvas)
    }

    private fun makeRound(canvas: Canvas) {
        path.reset()

        rect.top = 0f
        rect.left = 0f
        rect.right = width.toFloat()
        rect.bottom = height.toFloat()
        path.addRoundRect(rect, cornerRadius, cornerRadius, Path.Direction.CW)
        canvas.clipPath(path)
    }

    fun readAttrs(attrs: AttributeSet?) {
        if (attrs != null) {
            val typedArray =
                context.theme.obtainStyledAttributes(attrs, R.styleable.RoundImageView, 0, 0)

            cornerRadius =
                typedArray.getDimension(
                    R.styleable.RoundImageView_imageViewRadius,
                    dpToPx(context, 0)
                )

            typedArray.recycle()
        }
    }

    fun dpToPx(context: Context, dp: Int): Float {
        return dp * (context.resources.displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT).toFloat()
    }


}