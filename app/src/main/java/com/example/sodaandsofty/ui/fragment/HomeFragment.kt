package com.example.sodaandsofty.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.sodaandsofty.R
import com.example.sodaandsofty.adapter.*
import com.example.sodaandsofty.viewmodel.ItemViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel by viewModels<ItemViewModel>()

    @Inject
    lateinit var mAuth: FirebaseAuth

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        welcome_txt.text = "Welcome " + mAuth.currentUser?.displayName.toString()

        // First row (Regular Soda)
        viewModel.getAllRegularSodaDetails(requireContext())
        val regularSodaIcons = resources.obtainTypedArray(R.array.regular_soda_icons)
        val regularSodaAdapter = RegularSodaAdapter(regularSodaIcons)
        regular_soda_recyclerview.adapter = regularSodaAdapter
        viewModel.regularSodaDetails.observe(viewLifecycleOwner, {
            Log.d("HomeFragment", "Regular Soda Details : $it")
            regularSodaAdapter.setData(it)
        })

        // Second row (Shing Soda)
        viewModel.getAllShingrSodaDetails(requireContext())
        val shingSodaIcons = resources.obtainTypedArray(R.array.shing_soda_icons)
        val shingSodaAdapter = ShingSodaAdapter(shingSodaIcons)
        shing_soda_recyclerview.adapter = shingSodaAdapter
        viewModel.shingSodaDetails.observe(viewLifecycleOwner, {
            Log.d("HomeFragment", "Shing Soda Details : $it")
            shingSodaAdapter.setData(it)
        })


        // Third row (Softy)
        viewModel.getAllSoftyDetails(requireContext())
        val softyIcons = resources.obtainTypedArray(R.array.softy_icons)
        val softyAdapter = SoftyAdapter(softyIcons)
        softy_recyclerview.adapter = softyAdapter
        viewModel.softyDetails.observe(viewLifecycleOwner, {
            Log.d("HomeFragment", "Softy Details : $it")
            softyAdapter.setData(it)
        })

        // Fourth row (Flavour with Ice Cream)
        viewModel.getAllFlavourCreamDetails(requireContext())
        val flavourCreamIcons = resources.obtainTypedArray(R.array.flavour_with_ice_cream)
        val flavourCreamAdapter = FlavourCreamAdapter(flavourCreamIcons)
        flavour_with_ice_cream_recyclerview.adapter = flavourCreamAdapter
        viewModel.flavourCreamDetails.observe(viewLifecycleOwner, {
            Log.d("HomeFragment", "Flavour Cream Details : $it")
            flavourCreamAdapter.setData(it)
        })


        // Fifth row (Juice)
        viewModel.getAllFreshJuiceDetails(requireContext())
        val juiceIcons = resources.obtainTypedArray(R.array.juice)
        val juiceAdapter = JuiceAdapter(juiceIcons)
        fresh_juice_recyclerview.adapter = juiceAdapter
        viewModel.freshJuiceDetails.observe(viewLifecycleOwner, {
            Log.d("HomeFragment", "Juice Details : $it")
            juiceAdapter.setData(it)
        })

        // All buttons
        my_shop_btn.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToMyShopFragment()
            findNavController().navigate(action)
        }
        offers_fab.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToOffersFragment()
            findNavController().navigate(action)
        }
    }
}