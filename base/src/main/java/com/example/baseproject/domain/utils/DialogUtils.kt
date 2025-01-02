package com.example.baseproject.domain.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.View
import android.widget.Button
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.example.baseproject.R
import com.example.baseproject.utils.MediaUtil.loadImage

fun Context.simpleAlert(setup: Dialog.() -> Unit) {
    val dialog =
        Dialog(this, R.style.AlertDialogSlideAnim).apply {
            setContentView(R.layout.layout_custom_alert_dialog)
            window?.let {
                it.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                it.setGravity(Gravity.CENTER)
            }
        }
    setup(dialog)
    dialog.show()
}

fun Dialog.title(title: String) {
    this.findViewById<AppCompatTextView>(R.id.dialogTitle)?.apply {
        text = title
    }
}

fun Dialog.message(msg: String) {
    this.findViewById<AppCompatTextView>(R.id.dialogMessage)?.text = msg
}

fun Dialog.notification(msg: String) {
    this.findViewById<AppCompatTextView>(R.id.dialogTitle)?.gone()
    this.findViewById<AppCompatTextView>(R.id.dialogMessage)?.text = msg
}

fun Dialog.positiveAction(
    name: String? = null,
    click: () -> Unit,
) {
    this.findViewById<Button>(R.id.positiveOption).apply {
        name?.let { vl -> text = vl }
        setOnClickListener {
            click.invoke()
            this@positiveAction.dismiss()
        }
    }
}

fun Dialog.image(imageUrl: String? = null) {
    imageUrl?.let { url ->
        this.findViewById<AppCompatImageView>(R.id.dialogImage).apply {
            visible()
            loadImage(url)
        }
    }
}

fun Dialog.negativeAction(
    name: String? = null,
    click: () -> Unit,
) {
    this.findViewById<Button>(R.id.negativeOption).apply {
        name?.let { vl ->
            visibility = View.VISIBLE
            text = vl
        } ?: run {
            visibility = View.GONE
        }
        setOnClickListener {
            click.invoke()
            this@negativeAction.dismiss()
        }
    }
}
