package com.example.moviebooking.presentation.main.profile.dialogs

import android.os.Bundle
import android.widget.Toast
import com.example.baseproject.domain.utils.journeyViewModel
import com.example.baseproject.domain.utils.safeClick
import com.example.baseproject.presentation.BaseDialog
import com.example.moviebooking.MainNavigator
import com.example.moviebooking.R
import com.example.moviebooking.databinding.DialogEditProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest

class EditProfileDialog : BaseDialog<DialogEditProfileBinding, MainNavigator>(
    R.layout.dialog_edit_profile
) {
    override val navigator: MainNavigator by journeyViewModel()

    private val firebaseAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    override fun initView(savedInstanceState: Bundle?, binding: DialogEditProfileBinding) {
        initUI(binding)
        initClickListener(binding)
    }

    private fun initUI(binding: DialogEditProfileBinding) {
        val currentUser = firebaseAuth.currentUser
        if (currentUser != null) {
            binding.emailET.setText(currentUser.email)
            binding.nameET.setText(currentUser.displayName)
        } else {
            Toast.makeText(context, "No user logged in", Toast.LENGTH_SHORT).show()
            dismiss()
        }
    }

    private fun initClickListener(binding: DialogEditProfileBinding) {
        binding.btnCancel.safeClick { dismiss() }

        binding.btnConfirm.safeClick {
            val newEmail = binding.emailET.text.toString().trim()
            val newName = binding.nameET.text.toString().trim()

            if (validateInput(newEmail, newName)) {
                updateProfile(newEmail, newName)
            }
        }

        binding.btnEditProfileAvatar.safeClick {
            Toast.makeText(context, "Avatar editing not implemented yet", Toast.LENGTH_SHORT).show()
        }
    }

    private fun validateInput(email: String, name: String): Boolean {
        if (email.isEmpty()) {
            binding.emailET.error = "Email cannot be empty"
            return false
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.emailET.error = "Invalid email format"
            return false
        }
        if (name.isEmpty()) {
            binding.nameET.error = "Name cannot be empty"
            return false
        }
        return true
    }

    private fun updateProfile(newEmail: String, newName: String) {
        val currentUser = firebaseAuth.currentUser ?: return

        showLoading(true)

        val profileUpdates = UserProfileChangeRequest.Builder()
            .setDisplayName(newName)
            .build()

        currentUser.updateProfile(profileUpdates)
            .addOnCompleteListener { profileTask ->
                if (profileTask.isSuccessful) {
                    currentUser.verifyBeforeUpdateEmail(newEmail)
                        .addOnCompleteListener { emailTask ->
                            showLoading(false)
                            if (emailTask.isSuccessful) {
                                Toast.makeText(
                                    context,
                                    "Profile updated. Check your email to verify the new address.",
                                    Toast.LENGTH_LONG
                                ).show()
                                dismiss()
                            } else {
                                Toast.makeText(
                                    context,
                                    "Failed to update email: ${emailTask.exception?.message}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                } else {
                    showLoading(false)
                    Toast.makeText(
                        context,
                        "Failed to update name: ${profileTask.exception?.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.btnConfirm.isEnabled = !isLoading
        binding.btnCancel.isEnabled = !isLoading
    }
}