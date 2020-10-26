package com.example.sodaandsofty.ui.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.sodaandsofty.R
import com.example.sodaandsofty.ui.activity.SignInActivity
import com.example.sodaandsofty.utils.toast
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_my_shop.*
import javax.inject.Inject

@AndroidEntryPoint
class MyShopFragment : Fragment(R.layout.fragment_my_shop) {

    @Inject
    lateinit var mAuth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fb_btn.setOnClickListener {
            requireContext().toast("Facebook button clicked")
        }

        insta_btn.setOnClickListener {
            requireContext().toast("Instagram button clicked")
        }

        map_btn.setOnClickListener {
            requireContext().toast("Map button clicked")
        }

        log_out_btn.setOnClickListener {
            mAuth.signOut()
            val intent = Intent(requireContext(), SignInActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
    }
}