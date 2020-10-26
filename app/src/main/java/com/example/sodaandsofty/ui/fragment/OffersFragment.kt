package com.example.sodaandsofty.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.sodaandsofty.R
import com.example.sodaandsofty.adapter.OffersAdapter
import com.example.sodaandsofty.connectivity.ConnectivityLiveData
import com.example.sodaandsofty.viewmodel.ItemViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_offers.*
import javax.inject.Inject

@AndroidEntryPoint
class OffersFragment : Fragment(R.layout.fragment_offers) {

    private val viewModel by viewModels<ItemViewModel>()

    @Inject
    lateinit var connectivityLiveData: ConnectivityLiveData

    @Inject
    lateinit var mAuth: FirebaseAuth

    private lateinit var snackBar: Snackbar

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        snackBar = Snackbar.make(
            parent_layout,
            "Check Your Internet Connection!!!",
            Snackbar.LENGTH_INDEFINITE
        )
        snackBar.show()

        connectivityLiveData.observe(viewLifecycleOwner, { isNetworkAvailable ->
            when (isNetworkAvailable) {
                true -> {
                    Log.d("OffersFragment", "Internet available")
                    snackBar.dismiss()

                    // Checking OfferList available or not:
                    viewModel.getIsOfferListAvailable(requireContext())
                    viewModel.isOfferListAvailable.observe(
                        viewLifecycleOwner,
                        { isOfferListAvailable ->
                            Log.d(
                                "OffersFragment",
                                "isOfferListAvailable - > :  $isOfferListAvailable"
                            )
                            when (isOfferListAvailable) {
                                true -> {
                                    Log.d("OffersFragment", "Offer List available")
                                    offer_list_recyclerview.visibility = View.VISIBLE

                                    // Setting Adapter:
                                    viewModel.getAllOfferItemDetails(requireContext())

                                    val regularSodaIcons =
                                        resources.obtainTypedArray(R.array.regular_soda_icons)
                                    val shingSodaIcons =
                                        resources.obtainTypedArray(R.array.shing_soda_icons)
                                    val softyIcons = resources.obtainTypedArray(R.array.softy_icons)
                                    val flavourCreamIcons =
                                        resources.obtainTypedArray(R.array.flavour_with_ice_cream)
                                    val juiceIcons = resources.obtainTypedArray(R.array.juice)

                                    val offerAdapter = OffersAdapter(
                                        regularSodaIcons,
                                        shingSodaIcons,
                                        softyIcons,
                                        flavourCreamIcons,
                                        juiceIcons,
                                        mAuth.uid.toString(),
                                        OffersFragmentDirections,
                                        this.findNavController()
                                    )
                                    offer_list_recyclerview.adapter = offerAdapter
                                    viewModel.offerItemDetails.observe(
                                        viewLifecycleOwner,
                                        {
                                            Log.d("OffersFragment", "Juice Details : $it")
                                            offerAdapter.setData(it)
                                        })

                                    // offer available for current user or not
                                    viewModel.getOfferAvailableForCurrentUser(requireContext())
                                    viewModel.offerAvailableForCurrentUserDetails.observe(
                                        viewLifecycleOwner,
                                        {
                                            Log.d("OffersFragment", it.toString())
                                            offerAdapter.setAvailableDetailData(it)
                                        })
                                }
                                false -> {
                                    Log.d("OffersFragment", "Offer List Unavailable")
                                    offer_list_recyclerview.visibility = View.GONE
                                }
                            }
                        })
                }
                false -> {
                    Log.d("OffersFragment", "Internet Unavailable")
                    snackBar.show()
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        snackBar.dismiss()
    }
}