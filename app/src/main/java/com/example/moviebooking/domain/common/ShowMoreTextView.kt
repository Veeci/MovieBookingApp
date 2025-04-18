package com.example.moviebooking.domain.common

import android.content.Context
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.util.AttributeSet
import android.view.View
import android.view.ViewTreeObserver
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.example.moviebooking.R

class ShowMoreTextView @kotlin.jvm.JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): AppCompatTextView(context, attrs, defStyleAttr) {
    private var showingLine = 1

    private var showMore = "Show more"
    private var showLess = "Show less"
    private val threeDot = "..."

    private var showMoreTextColor = Color.YELLOW
    private var showLessTextColor = Color.YELLOW

    private var mainText: String? = null

    private var isAlreadySet = false
    private var isCollapsed = true

    init {
        viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                if (showingLine >= lineCount) return
                showMoreButton()
                viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })
    }

    override fun onFinishInflate() {
        super.onFinishInflate()

        mainText = text.toString()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()

        text = mainText
    }

    private fun showMoreButton() {
        val text = text.toString()
        if (!isAlreadySet) {
            mainText = text
            isAlreadySet = true
        }
        var showingText = ""
        var start = 0
        var end: Int
        for (i in 0 until showingLine) {
            val textLayout = layout ?: return
            end = textLayout.getLineEnd(i)
            showingText += text.substring(start, end)
            start = end
        }
        var specialSpace = 0
        var newText: String
        do {
            if (showingText.length - specialSpace <= 0) break
            newText = showingText.substring(0, showingText.length - specialSpace) + "$threeDot $showMore"
            setText(newText)
            specialSpace++
        } while (lineCount > showingLine)
        isCollapsed = true
        setShowMoreColoringAndClickable()
    }

    private fun setShowMoreColoringAndClickable() {
        val spannableString = SpannableString(text)
        val startIndex = text.length - showMore.length
        if (startIndex < 0) return
        spannableString.setSpan(
            object : ClickableSpan() {
                override fun updateDrawState(paint: TextPaint) {
                    paint.isUnderlineText = false
                }

                override fun onClick(view: View) {
                    maxLines = Int.MAX_VALUE
                    text = mainText
                    isCollapsed = false
                    showLessButton()
                }
            }, startIndex, text.length, 0
        )
        spannableString.setSpan(
            ForegroundColorSpan(showMoreTextColor),
            text.length - showMore.length,
            text.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        movementMethod = LinkMovementMethod.getInstance()
        setText(spannableString, BufferType.SPANNABLE)
    }

    private fun showLessButton() {
        val text = "$text $showLess"
        val spannableString = SpannableString(text)
        spannableString.setSpan(
            object : ClickableSpan() {
                override fun updateDrawState(pain: TextPaint) {
                    pain.isUnderlineText = false
                }

                override fun onClick(view: View) {
                    maxLines = showingLine
                    showMoreButton()
                }
            }, text.length - showLess.length, text.length, 0
        )
        spannableString.setSpan(
            ForegroundColorSpan(showLessTextColor),
            text.length - (threeDot.length + showLess.length),
            text.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        movementMethod = LinkMovementMethod.getInstance()
        setText(spannableString, BufferType.SPANNABLE)
    }

    fun setShowingLine(lineNumber: Int) {
        if (lineNumber == 0) return
        showingLine = lineNumber
        maxLines = showingLine
    }

    fun addShowMoreText(text: String) {
        showMore = text
    }

    fun addShowLessText(text: String) {
        showLess = text
    }

    fun setShowMoreTextColor(color: Int) {
        showMoreTextColor = color
    }

    fun setShowLessTextColor(color: Int) {
        showLessTextColor = color
    }
}