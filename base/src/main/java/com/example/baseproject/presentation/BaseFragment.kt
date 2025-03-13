package com.example.baseproject.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.baseproject.domain.utils.screenViewModel
import com.example.baseproject.domain.viewmodel.BaseViewModel
import com.example.baseproject.domain.viewmodel.ErrorState
import com.example.baseproject.presentation.navigation.BaseNavigator
import com.example.baseproject.presentation.navigation.BaseRouter

abstract class BaseFragment<V : ViewBinding, Router : BaseRouter, out N : BaseNavigator>(
    private val layoutId: Int = 0,
) : Fragment() {
    protected lateinit var binding: V
    abstract val navigator: N
    protected var router: Router? = null
    protected lateinit var activity: BaseActivity<*, *, *>

    open val viewModel: BaseViewModel by screenViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater, layoutId, container, false)
        activity = requireActivity() as BaseActivity<*, *, *>

        return binding.root
    }

    @Suppress("UNCHECKED_CAST")
    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        try {
            (navigator as? Router?)?.let {
                router = it
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        backPressHandle()
        errorObserver()
        permissionRegister()

        initView(savedInstanceState = savedInstanceState, binding = binding)
    }

    abstract fun initView(
        savedInstanceState: Bundle?,
        binding: V,
    )

    open fun onBackPressed(): Boolean = false

    private fun backPressHandle() {
        activity.onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            if (!onBackPressed()) {
                if (isEnabled) {
                    isEnabled = false
                    router?.onPopScreen()
                }
            }
        }
    }

    private fun errorObserver() {
        viewModel.errorState.observe(viewLifecycleOwner) { errorState ->
            if (!handleErrorState(errorState)) {
                when (errorState) {
                    is ErrorState.GenericError -> {
                        navigator.onErrorEvent(
                            message = errorState.message,
                            action = 0,
                        )
                    }

                    is ErrorState.NetworkError -> {
                        navigator.onErrorEvent(
                            message = errorState.exception.message,
                            action = 0,
                        )
                    }
                }
            }
        }
    }

    open fun handleErrorState(errorState: ErrorState): Boolean = false

    private fun permissionRegister() {
        activity.onPermissionResult = { requestCode, permissions, grantResults, deviceId ->
            onPermissionResult(requestCode, permissions, grantResults, deviceId)
        }
    }

    open fun onPermissionResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
        deviceId: Int,
    ) = Unit

    fun showLoading(
        isLoading: Boolean,
        preventClicking: Boolean = false,
    ) {
        activity.toggleLoading(isLoading, preventClicking)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.errorState.removeObservers(viewLifecycleOwner)
    }
}

/**
 * Example in use:
 * -Handle the back press: for instance in the way you want to handle the back press to confirm before exiting the app:
 * override fun onBackPressed(): Boolean {
 *         AlertDialog.Builder(requireContext())
 *             .setTitle("Exit App")
 *             .setMessage("Are you sure you want to exit?")
 *             .setPositiveButton("Yes") { _, _ ->
 *                 requireActivity().finish()
 *             }
 *             .setNegativeButton("No", null)
 *             .show()
 *         return true
 * }
 */
