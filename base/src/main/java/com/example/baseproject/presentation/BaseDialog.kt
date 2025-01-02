package com.example.baseproject.presentation

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding
import com.example.baseproject.presentation.navigation.BaseNavigator

abstract class BaseDialog<V : ViewBinding, N : BaseNavigator>(
    private val layoutId: Int = 0,
) : DialogFragment() {
    protected lateinit var binding: V
    protected lateinit var activity: BaseActivity<*, *>
    abstract val navigator: N

    open var gravity = Gravity.CENTER
    open var dismissOnTouchOutside = false
    open var enterAnimation: Int? = null
    open var exitAnimation: Int? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState).apply {
            this.requestWindowFeature(Window.FEATURE_NO_TITLE)
            this.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            this.setCanceledOnTouchOutside(dismissOnTouchOutside)
        }
    }

    override fun onStart() {
        super.onStart()

        enterAnimation?.let {
            dialog?.window?.setWindowAnimations(it)
        }

        exitAnimation?.let {
            dialog?.window?.setWindowAnimations(it)
        }

        this@BaseDialog.dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
        )
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

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        dialog?.window?.setGravity(gravity)
        initView(savedInstanceState = savedInstanceState, binding = binding)
    }

    abstract fun initView(
        savedInstanceState: Bundle?,
        binding: V,
    )
}

/**
 * Note: When subscribing to lifecycle-aware components such as LiveData, never use viewLifecycleOwner as the LifecycleOwner in a DialogFragment that uses Dialog objects.
 * Instead, use the DialogFragment itself, or, if you're using Jetpack Navigation, use the NavBackStackEntry.
 * e.g:viewModel.someLiveData.observe(this) { data -> }
 *
 */
