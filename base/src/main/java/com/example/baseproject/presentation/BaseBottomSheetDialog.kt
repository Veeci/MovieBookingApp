package com.example.baseproject.presentation

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import androidx.viewbinding.ViewBinding
import com.example.baseproject.R
import com.example.baseproject.presentation.navigation.BaseNavigator
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheetDialog<V : ViewBinding, N : BaseNavigator>(
    private val layoutId: Int = 0,
) : BottomSheetDialogFragment() {
    protected lateinit var binding: V
    abstract val navigator: N
    protected lateinit var activity: BaseActivity<*, *>

    open var style: Int? = null
    open var enterAnimation: Int? = null
    open var exitAnimation: Int? = null
    open var dismissOnTouchOutside = false
    open var isDraggable = true
    open var isFullScreen = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.setStyle(STYLE_NORMAL, style ?: R.style.CustomBottomSheetDialogTheme)

        enterAnimation?.let {
            dialog?.window?.setWindowAnimations(it)
        }

        exitAnimation?.let {
            dialog?.window?.setWindowAnimations(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater, layoutId, container, false)
        activity = requireActivity() as BaseActivity<*, *>
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.let { dialog ->
            val bottomSheet = dialog.findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)
            bottomSheet?.let { view ->
                val behavior = BottomSheetBehavior.from(view)
                behavior.isDraggable = isDraggable

                view.layoutParams.height = if (isFullScreen) ViewGroup.LayoutParams.MATCH_PARENT else ViewGroup.LayoutParams.WRAP_CONTENT
            }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState).apply {
            setCanceledOnTouchOutside(dismissOnTouchOutside)
        }
    }
}
