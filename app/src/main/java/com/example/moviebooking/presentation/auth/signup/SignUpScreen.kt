package com.example.moviebooking.presentation.auth.signup

import android.os.Bundle
import android.util.Patterns
import com.example.baseproject.domain.utils.EncryptedSharedPreferences
import com.example.baseproject.domain.utils.LogUtils
import com.example.baseproject.domain.utils.message
import com.example.baseproject.domain.utils.navigatorViewModel
import com.example.baseproject.domain.utils.negativeAction
import com.example.baseproject.domain.utils.positiveAction
import com.example.baseproject.domain.utils.safeClick
import com.example.baseproject.domain.utils.simpleAlert
import com.example.baseproject.domain.utils.title
import com.example.baseproject.domain.utils.toastShort
import com.example.baseproject.domain.utils.visible
import com.example.baseproject.presentation.BaseFragment
import com.example.moviebooking.MainNavigator
import com.example.moviebooking.R
import com.example.moviebooking.databinding.FragmentSignUpScreenBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

class SignUpScreen : BaseFragment<FragmentSignUpScreenBinding, SignUpRouter, MainNavigator>(
    R.layout.fragment_sign_up_screen
) {
    override val navigator: MainNavigator by navigatorViewModel()

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth
    }

    override fun initView(savedInstanceState: Bundle?, binding: FragmentSignUpScreenBinding) {
        setup()
    }

    private fun setup() {
        with(binding) {
            backBtn.safeClick { router?.goToAuthScreen() }

            signupBtn.safeClick {
                signUp(
                    binding.nameInput.text.toString(),
                    binding.emailInput.text.toString(),
                    binding.passwordInput.text.toString()
                )
            }

            receiveNewsCheckBox.setOnCheckedChangeListener { _, isChecked ->
                //todo
            }
        }
    }

    private fun signUp(
        name: String,
        email: String,
        password: String
    ) {
        if (validateAccount(email, password)) {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity) { task ->
                    if (task.isSuccessful) {
                        saveUserCache(name, email, password)
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
                    } else {
                        activity.toastShort("Sign up failed. Please try again!")
                    }
                }
        }
    }

    private fun validateAccount(
        email: String,
        password: String
    ): Boolean {
        if (email.isEmpty() || password.isEmpty()) {
            activity.toastShort("Email and password cannot be empty")
            return false
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            activity.toastShort("Invalid email")
            binding.emailFormatError.visible()
            return false
        }

        val passwordPattern =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$".toRegex()
        if (!passwordPattern.matches(password)) {
            binding.passwordValidatorError.visible()
            return false
        }

        if (binding.passwordInput.text.toString() != binding.confirmPasswordInput.text.toString()) {
            binding.confirmPasswordError.visible()
            return false
        }

        return true
    }

    private fun saveUserCache(
        name: String,
        email: String,
        password: String
    ) {
        val encryptedSharedPreferences = context?.let { EncryptedSharedPreferences(it) }
        encryptedSharedPreferences?.setData("user_name", name)
        encryptedSharedPreferences?.setData("user_email", email)
        encryptedSharedPreferences?.setData("user_password", password)

        Firebase.firestore
            .collection("users")
            .document(auth.currentUser?.uid.toString())
            .set(
                hashMapOf(
                    "name" to name,
                    "email" to email,
                    "password" to password
                )
            )
            .addOnSuccessListener {
                LogUtils.log("CloudUserInfo", "DocumentSnapshot added with ID: ${auth.currentUser?.uid.toString()}")
            }
            .addOnFailureListener { e ->
                LogUtils.log("CloudUserInfo", "Error adding document: $e")
            }
    }
}