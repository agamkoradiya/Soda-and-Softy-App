package com.example.sodaandsoftyowner.ui.fragment

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.CodeScannerView
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode
import com.example.sodaandsoftyowner.R
import com.example.sodaandsoftyowner.connectivity.ConnectivityLiveData
import com.example.sodaandsoftyowner.viewmodel.ViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_sign_in.parent_layout
import kotlinx.android.synthetic.main.fragment_scan_qr.*
import javax.inject.Inject

@AndroidEntryPoint
class ScanQrFragment : Fragment(R.layout.fragment_scan_qr) {

    private val viewModel by viewModels<ViewModel>()

    @Inject
    lateinit var connectivityLiveData: ConnectivityLiveData

    private lateinit var codeScanner: CodeScanner
    private lateinit var scannerView: CodeScannerView
    private var receivedContent: String = ""
    private val REQUEST_CODE = 111

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        reset_btn.setOnClickListener {

            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Reset Confirmation")
                .setMessage("Are you sure you want to reset all offers availability?")
                .setPositiveButton("Yes") { _, _ ->
                    viewModel.resetAvailability()
                    Snackbar.make(requireView(), "Reset Succeed", Snackbar.LENGTH_LONG).show()
                }
                .setNegativeButton("No") { dialog, _ ->
                    dialog.cancel()
                }.show()
        }

        val snackBar: Snackbar = Snackbar.make(
            parent_layout.rootView,
            "Check Your Internet Connection!!!",
            Snackbar.LENGTH_INDEFINITE
        )
        snackBar.show()
        connectivityLiveData.observe(requireActivity(), { isAvailable ->
            when (isAvailable) {
                true -> {
                    snackBar.dismiss()
                    scannerView.visibility = View.VISIBLE
                    reset_btn.visibility = View.VISIBLE
                }
                false -> {
                    snackBar.show()
                    scannerView.visibility = View.GONE
                    reset_btn.visibility = View.GONE
                }
            }
        })
        scannerView = view.findViewById<CodeScannerView>(R.id.scanner_view)
        codeScanner = CodeScanner(requireContext(), scannerView)

        scannerView.setOnClickListener {
            codeScanner.startPreview()
        }
        setupPermissions()
    }

    private fun scanNow() {
        codeScanner.camera = CodeScanner.CAMERA_BACK // or CAMERA_FRONT or specific camera id
        codeScanner.formats = CodeScanner.ALL_FORMATS // list of type BarcodeFormat,
        // ex. listOf(BarcodeFormat.QR_CODE)
        codeScanner.autoFocusMode = AutoFocusMode.SAFE // or CONTINUOUS
        codeScanner.scanMode = ScanMode.SINGLE // or CONTINUOUS or PREVIEW
        codeScanner.isAutoFocusEnabled = true // Whether to enable auto focus or not
        codeScanner.isFlashEnabled = false // Whether to enable flash or not

        // Callbacks
        codeScanner.decodeCallback = DecodeCallback {
            activity?.runOnUiThread {
                Log.d("ScanQrFragment", "onViewCreated: " + it.text)
                receivedContent = it.text
                insertReceivedContentIntoDb()
            }
        }
        codeScanner.errorCallback = ErrorCallback { // or ErrorCallback.SUPPRESS
            activity?.runOnUiThread {
                Log.d("ScanQrFragment", "onViewCreated: " + it.message)
                Toast.makeText(requireContext(), "Try Again", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun insertReceivedContentIntoDb() {
        Log.d("ScanQrFragment", "onViewCreated: $receivedContent")

        val splitWords = receivedContent.split("`~")
        Log.d("ScanQrFragment", splitWords.toString())

        // INSERTING NEW CARD FROM SCANNING
        if (splitWords[0] == "code.fun") {
            Log.d("ScanQrFragment", "Uid -> ${splitWords[1]}")
            Log.d("ScanQrFragment", "offerId -> ${splitWords[2]}")

            viewModel.changeAvailability(splitWords[1], splitWords[2])
        } else {
            Toast.makeText(requireContext(), "Scan only our app's QR code", Toast.LENGTH_LONG)
                .show()
        }
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }
    // FOR PERMISSION

    private fun setupPermissions() {
        val permission = ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.CAMERA
        )

        if (permission != PackageManager.PERMISSION_GRANTED) {
            makeRequest()
        } else {
            codeScanner.startPreview()
            scanNow()
        }
    }

    private fun makeRequest() {
        requestPermissions(arrayOf(Manifest.permission.CAMERA), REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            REQUEST_CODE -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Log.i("ScanQrFragment", "Permission has been denied by user")

                } else {
                    Log.i("ScanQrFragment", "Permission has been granted by user")
                    codeScanner.startPreview()
                    scanNow()
                }
            }
        }
    }
}