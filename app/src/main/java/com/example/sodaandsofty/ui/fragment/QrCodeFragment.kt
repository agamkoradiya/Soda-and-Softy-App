package com.example.sodaandsofty.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.sodaandsofty.R
import com.example.sodaandsofty.viewmodel.ItemViewModel
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_qr_code.*

@AndroidEntryPoint
class QrCodeFragment : Fragment(R.layout.fragment_qr_code) {
    private val viewModel by viewModels<ItemViewModel>()
    private val args: QrCodeFragmentArgs by navArgs()
    private lateinit var content: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        description_qr_txt.text = args.description

        val uId = args.uId
        val offerId = args.offerId

        content = "code.fun`~$uId`~$offerId"

        val barcodeEncoder = BarcodeEncoder()
        val bitmap = barcodeEncoder.encodeBitmap(content, BarcodeFormat.QR_CODE, 512, 512)

        imageView.setImageBitmap(bitmap)

        val action = QrCodeFragmentDirections.actionQrCodeFragmentToOffersFragment()
        // offer available for current user or not
        viewModel.getOfferAvailableForCurrentUser(requireContext())
        viewModel.offerAvailableForCurrentUserDetails.observe(
            viewLifecycleOwner,
            {
                when (args.offerId) {
                    "offer1Available" -> {
                        if (!(it.offer1Available)) {
                            Toast.makeText(
                                requireContext(),
                                "Offer code successfully applied\nThank you!!!",
                                Toast.LENGTH_LONG
                            ).show()
                            findNavController().navigate(action)
                        }
                    }

                    "offer2Available" -> {
                        if (!(it.offer2Available)) {
                            Toast.makeText(
                                requireContext(),
                                "Offer code successfully applied\nThank you!!!",
                                Toast.LENGTH_LONG
                            ).show()
                            findNavController().navigate(action)
                        }
                    }

                    "offer3Available" -> {
                        if (!(it.offer3Available)) {
                            Toast.makeText(
                                requireContext(),
                                "Offer code successfully applied\nThank you!!!",
                                Toast.LENGTH_LONG
                            ).show()
                            findNavController().navigate(action)
                        }
                    }
                }
            })
    }
}