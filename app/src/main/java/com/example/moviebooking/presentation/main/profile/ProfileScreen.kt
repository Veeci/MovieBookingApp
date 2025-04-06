package com.example.moviebooking.presentation.main.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.baseproject.domain.utils.navigatorViewModel
import com.example.baseproject.domain.utils.safeClick
import com.example.baseproject.presentation.BaseFragment
import com.example.baseproject.utils.MediaUtil.loadImage
import com.example.moviebooking.MainNavigator
import com.example.moviebooking.R
import com.example.moviebooking.databinding.FragmentProfileScreenBinding
import com.example.moviebooking.domain.common.Const
import com.example.moviebooking.presentation.main.profile.dialogs.ChangePasswordDialog
import com.example.moviebooking.presentation.main.profile.dialogs.EditProfileDialog
import com.google.firebase.auth.FirebaseAuth

class ProfileScreen : BaseFragment<FragmentProfileScreenBinding, ProfileRouter, MainNavigator>(
    R.layout.fragment_profile_screen
) {
    override val navigator: MainNavigator by navigatorViewModel()

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()
    }

    override fun initView(savedInstanceState: Bundle?, binding: FragmentProfileScreenBinding) {
        setupUI()
        onclickListener()
    }

    private fun setupUI() {
        with(binding) {
            auth.currentUser?.let { user ->
                user.displayName.takeIf { !it.isNullOrEmpty() }?.let { name ->
                    username.text = name
                } ?: run {
                    username.text = auth.currentUser?.email
                }

                user.photoUrl.takeIf { it != null }?.let { profileImage ->
                    profileAvatar.loadImage(
                        source = profileImage,
                        defaultImage = enumValues<Const.DefaultAvatar>().random().resId
                    )
                } ?: run {
                    profileAvatar.setImageResource(enumValues<Const.DefaultAvatar>().random().resId)
                }
            }
        }
    }

    private fun onclickListener() {
        with(binding) {
            btnEditProfile.safeClick {
                EditProfileDialog().show(parentFragmentManager, EditProfileDialog::class.java.name)
            }

            btnChangePassword.safeClick {
                ChangePasswordDialog().show(parentFragmentManager, ChangePasswordDialog::class.java.name)
            }

            btnWatchList.safeClick {  }

            btnSignOut.safeClick {
                auth.signOut()
                navigator.backToLogin()
            }
        }
    }
}