package com.example.moviebooking.presentation.main.profile.dialogs

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.baseproject.domain.utils.journeyViewModel
import com.example.baseproject.domain.utils.safeClick
import com.example.baseproject.presentation.BaseDialog
import com.example.moviebooking.MainNavigator
import com.example.moviebooking.R
import com.example.moviebooking.databinding.DialogChangePasswordBinding
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth

class ChangePasswordDialog : BaseDialog<DialogChangePasswordBinding, MainNavigator>(
    R.layout.dialog_change_password
) {
    override val navigator: MainNavigator by journeyViewModel()

    private val firebaseAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    override fun initView(savedInstanceState: Bundle?, binding: DialogChangePasswordBinding) {
        initUI()
        initClickListener(binding)
    }

    private fun initUI() {
        val currentUser = firebaseAuth.currentUser
        if (currentUser == null) {
            Toast.makeText(context, "No user logged in", Toast.LENGTH_SHORT).show()
            dismiss()
        }
    }

    private fun initClickListener(binding: DialogChangePasswordBinding) {
        binding.btnCancel.safeClick { dismiss() }

        binding.btnConfirm.safeClick {
            val oldPassword = binding.passwordConfirmET.text.toString().trim()
            val newPassword = binding.passwordET.text.toString().trim()
            val confirmPassword = binding.passwordConfirmET.text.toString().trim()

            if (validateInput(oldPassword, newPassword, confirmPassword, binding)) {
                changePassword(oldPassword, newPassword, binding)
            }
        }
    }

    private fun validateInput(
        oldPassword: String,
        newPassword: String,
        confirmPassword: String,
        binding: DialogChangePasswordBinding
    ): Boolean {
        binding.incorrectPasswordTV.visibility = View.GONE
        binding.passwordNotMatchTV.visibility = View.GONE

        if (oldPassword.isEmpty()) {
            binding.passwordConfirmET.error = "Old password cannot be empty"
            return false
        }

        if (newPassword.isEmpty()) {
            binding.passwordET.error = "New password cannot be empty"
            return false
        }

        if (newPassword.length < 6) {
            binding.passwordET.error = "Password must be at least 6 characters"
            return false
        }

        if (confirmPassword.isEmpty()) {
            binding.passwordConfirmET.error = "Confirm password cannot be empty"
            return false
        }

        if (newPassword != confirmPassword) {
            binding.passwordNotMatchTV.visibility = View.VISIBLE
            return false
        }

        return true
    }

    private fun changePassword(oldPassword: String, newPassword: String, binding: DialogChangePasswordBinding) {
        val currentUser = firebaseAuth.currentUser ?: return
        val email = currentUser.email ?: return

        showLoading(true, binding)

        val credential = EmailAuthProvider.getCredential(email, oldPassword)
        currentUser.reauthenticate(credential)
            .addOnCompleteListener { reauthTask ->
                if (reauthTask.isSuccessful) {
                    currentUser.updatePassword(newPassword)
                        .addOnCompleteListener { updateTask ->
                            showLoading(false, binding)
                            if (updateTask.isSuccessful) {
                                Toast.makeText(
                                    context,
                                    "Password updated successfully",
                                    Toast.LENGTH_SHORT
                                ).show()
                                dismiss()
                            } else {
                                Toast.makeText(
                                    context,
                                    "Failed to update password: ${updateTask.exception?.message}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                } else {
                    showLoading(false, binding)
                    binding.incorrectPasswordTV.visibility = View.VISIBLE
                    Toast.makeText(
                        context,
                        "Old password is incorrect",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun showLoading(isLoading: Boolean, binding: DialogChangePasswordBinding) {
        binding.btnConfirm.isEnabled = !isLoading
        binding.btnCancel.isEnabled = !isLoading
    }
}