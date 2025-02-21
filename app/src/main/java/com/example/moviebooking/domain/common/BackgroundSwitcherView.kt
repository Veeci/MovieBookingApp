package com.example.moviebooking.domain.common

import android.animation.Animator
import android.content.Context
import android.graphics.Bitmap
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.animation.Animation
import android.widget.ImageSwitcher
import android.widget.ImageView
import androidx.core.graphics.drawable.toBitmap
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import coil.size.Size
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BackgroundSwitcherView : ImageSwitcher {
    private val normalOrder = intArrayOf(0, 1)

    private var bgImageGap: Int = 0
    private var bgImageWidth: Int = 0

    private var bgImageInLeftAnimation: Animation? = null
    private var bgImageOutLeftAnimation: Animation? = null

    private var bgImageInRightAnimation: Animation? = null
    private var bgImageOutRightAnimation: Animation? = null

    private val movementDuration = 500
    private val widthBackgroundImageGapPercent = 12

    private var currentAnimationDirection: AnimationDirection? = null

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        inflateAndInit(context)
    }

    constructor(context: Context) : super(context) {
        inflateAndInit(context)
    }

    private fun inflateAndInit(context: Context) {
        isChildrenDrawingOrderEnabled = true
        val displayMetrics = context.resources.displayMetrics
        bgImageGap = displayMetrics.widthPixels / 100 * widthBackgroundImageGapPercent
        bgImageWidth = displayMetrics.widthPixels + bgImageGap * 2

        this.setFactory {
            val myView = ImageView(context)
            myView.scaleType = ImageView.ScaleType.CENTER_CROP
            myView.layoutParams = LayoutParams(bgImageWidth, LayoutParams.MATCH_PARENT)
            myView.translationX = (-bgImageGap).toFloat()
            myView
        }

        bgImageInLeftAnimation = AnimUtils.createBgImageInAnimation(bgImageGap, 0, movementDuration)
        bgImageOutLeftAnimation = AnimUtils.createBgImageOutAnimation(0, -bgImageGap, movementDuration)
        bgImageInRightAnimation = AnimUtils.createBgImageInAnimation(-bgImageGap, 0, movementDuration)
        bgImageOutRightAnimation = AnimUtils.createBgImageOutAnimation(0, bgImageGap, movementDuration)
    }


    override fun getChildDrawingOrder(childCount: Int, i: Int): Int {
        return normalOrder[i]
    }

    @Synchronized
    private fun setImageBitmapWithAnimation(newBitmap: Bitmap, animationDirection: AnimationDirection?) {
        if (animationDirection == AnimationDirection.LEFT) {
            this.inAnimation = bgImageInLeftAnimation
            this.outAnimation = bgImageOutLeftAnimation
            this.setImageBitmap(newBitmap)

        } else if (animationDirection == AnimationDirection.RIGHT) {
            this.inAnimation = bgImageInRightAnimation
            this.outAnimation = bgImageOutRightAnimation
            this.setImageBitmap(newBitmap)
        }
    }


    fun updateCurrentBackground(imageUrl: String?) {
        this.currentAnimationDirection = AnimationDirection.RIGHT
        val image = this.nextView as ImageView
        image.setImageDrawable(null)
        showNext()

        CoroutineScope(Dispatchers.Main).launch {
            try {
                val bitmap = loadImage(imageUrl)
                setImageBitmapWithAnimation(bitmap, currentAnimationDirection)
            } catch (e: Exception) {
                println("@#@#@#@#@" + e.message)
            }
        }
    }

    private suspend fun loadImage(imageUrl: String?): Bitmap {
        return withContext(Dispatchers.IO) {
            val loader = ImageLoader(context)
            val request = ImageRequest.Builder(context)
                .data(imageUrl)
                .size(Size.ORIGINAL)
                .allowHardware(false)
                .build()

            val result = loader.execute(request)

            if (result is SuccessResult) {
                result.drawable.toBitmap()
            } else {
                throw Exception("Failed to load image")
            }
        }
    }


    private fun setImageBitmap(bitmap: Bitmap) {
        val image = this.nextView as ImageView
        image.setImageDrawable(null)

        val duration = 0
        animate().alpha(0.0f).setDuration(duration.toLong()).setListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {

            }

            override fun onAnimationEnd(animation: Animator) {
                image.setImageBitmap(bitmap)
                Handler(Looper.getMainLooper()).postDelayed({ animate().alpha(0.4f).duration = duration.toLong() }, 200)
            }

            override fun onAnimationCancel(animation: Animator) {}

            override fun onAnimationRepeat(animation: Animator) {}
        })
        showNext()
    }

    fun clearImage() {
        val image = this.nextView as ImageView
        image.setImageDrawable(null)
        showNext()
    }

    enum class AnimationDirection {
        LEFT, RIGHT
    }
}