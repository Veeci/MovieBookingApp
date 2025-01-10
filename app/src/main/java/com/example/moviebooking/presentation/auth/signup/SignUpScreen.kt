package com.example.moviebooking.presentation.auth.signup

import android.os.Bundle
import com.example.baseproject.domain.utils.message
import com.example.baseproject.domain.utils.navigatorViewModel
import com.example.baseproject.domain.utils.negativeAction
import com.example.baseproject.domain.utils.positiveAction
import com.example.baseproject.domain.utils.safeClick
import com.example.baseproject.domain.utils.simpleAlert
import com.example.baseproject.domain.utils.title
import com.example.baseproject.presentation.BaseFragment
import com.example.moviebooking.MainNavigator
import com.example.moviebooking.R
import com.example.moviebooking.databinding.FragmentSignUpScreenBinding

class SignUpScreen : BaseFragment<FragmentSignUpScreenBinding, SignUpRouter, MainNavigator>(
    R.layout.fragment_sign_up_screen
) {
    override val navigator: MainNavigator by navigatorViewModel()

    override fun initView(savedInstanceState: Bundle?, binding: FragmentSignUpScreenBinding) {
        setup()
    }

    private fun setup() {
        with(binding) {
            backBtn.safeClick { router?.onPopScreen() }

            signupBtn.safeClick {
                if(signUp(
                    name = binding.nameInput.text.toString(),
                    email = binding.emailInput.text.toString(),
                    password = binding.passwordInput.text.toString()
                )) {
                    activity.simpleAlert {
                        title("Sign Up Successfully")
                        message("You are successfully signed up. Log in to have some fun!")
                        positiveAction(name = "Ok") {
                            router?.goToAuthScreen()
                        }
                        negativeAction(name = "Cancel") {
                            dismiss()
                        }
                    }
                }
            }
        }
    }

    private fun signUp(
        name: String,
        email: String,
        password: String
    ): Boolean {

        return true
    }

    private fun validateAccount(email: String, password: String): Boolean {

        return true
    }
}