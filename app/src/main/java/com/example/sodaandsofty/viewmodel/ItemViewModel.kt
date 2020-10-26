package com.example.sodaandsofty.viewmodel

import android.content.Context
import android.util.Log
import android.widget.EditText
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sodaandsofty.model.ItemDetailsModel
import com.example.sodaandsofty.model.OfferItemDetailsModel
import com.example.sodaandsofty.model.OfferListAvailableModel
import com.example.sodaandsofty.model.UserOfferDataModel
import com.example.sodaandsofty.utils.toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemViewModel @ViewModelInject constructor(
    private val db: FirebaseFirestore,
    private val mAuth: FirebaseAuth
) : ViewModel() {

    // Add FirstTime User
    fun checkIsUserExist(
        context: Context,
        uId: String,
    ) {
        db.collection("allUsers").whereEqualTo(FieldPath.documentId(), uId)
            .get().addOnSuccessListener {
                if (it.documents.isEmpty()) {
                    Log.d("TAG", "New User -> Adding new user in firestore")
                    // Save Process Started
                    CoroutineScope(Dispatchers.IO).launch {
                        db.collection("allUsers").document(uId).set(
                            UserOfferDataModel(
                                offer1Available = true,
                                offer2Available = true,
                                offer3Available = true
                            )
                        )
                            .addOnSuccessListener {
                                context.toast("Welcome")
                            }.addOnFailureListener {
                                context.toast("Try Again...")
                            }
                    }
                } else {
                    context.toast("Welcome again...")
                    Log.d("TAG", "Already exists User")

                }
            }
    }


    // First row (Regular soda) view model
    private val _regularSodaDetails = MutableLiveData<List<ItemDetailsModel>>()
    val regularSodaDetails: LiveData<List<ItemDetailsModel>>
        get() = _regularSodaDetails

    fun getAllRegularSodaDetails(context: Context) {
        db.collection("plainSoda").addSnapshotListener { value, error ->
            error?.let {
                context.toast("Try Again...")
            }
            value?.let {
                val regularSodaList = mutableListOf<ItemDetailsModel>()
                for (document in it.iterator()) {
                    val regularSodaItem = document.toObject(ItemDetailsModel::class.java)
                    regularSodaList.add(regularSodaItem)
                }
                _regularSodaDetails.value = regularSodaList
            }
        }
    }


    // Second row (Shing soda) view model
    private val _shingSodaDetails = MutableLiveData<List<ItemDetailsModel>>()
    val shingSodaDetails: LiveData<List<ItemDetailsModel>>
        get() = _shingSodaDetails

    fun getAllShingrSodaDetails(context: Context) {
        db.collection("shingSoda").addSnapshotListener { value, error ->
            error?.let {
                context.toast("Try Again...")
            }
            value?.let {
                val shingSodaList = mutableListOf<ItemDetailsModel>()
                for (document in it.iterator()) {
                    val shingSodaItem = document.toObject(ItemDetailsModel::class.java)
                    shingSodaList.add(shingSodaItem)
                }
                _shingSodaDetails.value = shingSodaList
            }
        }
    }

    // Third row (Softy) view model
    private val _softyDetails = MutableLiveData<List<ItemDetailsModel>>()
    val softyDetails: LiveData<List<ItemDetailsModel>>
        get() = _softyDetails

    fun getAllSoftyDetails(context: Context) {
        db.collection("softy").addSnapshotListener { value, error ->
            error?.let {
                context.toast("Try Again...")
            }
            value?.let {
                val softyList = mutableListOf<ItemDetailsModel>()
                for (document in it.iterator()) {
                    val softyItem = document.toObject(ItemDetailsModel::class.java)
                    softyList.add(softyItem)
                }
                _softyDetails.value = softyList
            }
        }
    }


    // Fourth row (Flavour with ice cream) view model
    private val _flavourCreamDetails = MutableLiveData<List<ItemDetailsModel>>()
    val flavourCreamDetails: LiveData<List<ItemDetailsModel>>
        get() = _flavourCreamDetails

    fun getAllFlavourCreamDetails(context: Context) {
        db.collection("flavourWithIcecream").addSnapshotListener { value, error ->
            error?.let {
                context.toast("Try Again...")
            }
            value?.let {
                val flavourCreamList = mutableListOf<ItemDetailsModel>()
                for (document in it.iterator()) {
                    val flavourCreamItem = document.toObject(ItemDetailsModel::class.java)
                    flavourCreamList.add(flavourCreamItem)
                }
                _flavourCreamDetails.value = flavourCreamList
            }
        }
    }


    // Fifth row (Fresh Juice) view model
    private val _freshJuiceDetails = MutableLiveData<List<ItemDetailsModel>>()
    val freshJuiceDetails: LiveData<List<ItemDetailsModel>>
        get() = _freshJuiceDetails

    fun getAllFreshJuiceDetails(context: Context) {
        db.collection("freshJuice").addSnapshotListener { value, error ->
            error?.let {
                context.toast("Try Again...")
            }
            value?.let {
                val freshJuiceList = mutableListOf<ItemDetailsModel>()
                for (document in it.iterator()) {
                    val freshJuiceItem = document.toObject(ItemDetailsModel::class.java)
                    freshJuiceList.add(freshJuiceItem)
                }
                _freshJuiceDetails.value = freshJuiceList
            }
        }
    }


    // Offers List is Available or not view model
    private val _isOfferListAvailable = MutableLiveData<Boolean>()
    val isOfferListAvailable: LiveData<Boolean>
        get() = _isOfferListAvailable

    fun getIsOfferListAvailable(context: Context) {
        db.collection("offersSection").document("isOfferListAvailable")
            .addSnapshotListener { value, error ->
                error?.let {
                    context.toast("Try Again...")
                }
                value?.let {
                    Log.d(
                        "OffersFragment",
                        "getIsOfferListAvailable: ${it.getBoolean("isOfferListAvailable")}"
                    )
                    _isOfferListAvailable.value = it.getBoolean("isOfferListAvailable")
                }
            }
    }


    // All Offers view model
    private val _offerItemDetails = MutableLiveData<List<OfferItemDetailsModel>>()
    val offerItemDetails: LiveData<List<OfferItemDetailsModel>>
        get() = _offerItemDetails

    fun getAllOfferItemDetails(context: Context) {
        db.collection("offersSection").document("offersData").collection("data")
            .addSnapshotListener { value, error ->
                error?.let {
                    context.toast("Try Again...")
                }
                value?.let {
                    val offerItemList = mutableListOf<OfferItemDetailsModel>()
                    for (document in it.iterator()) {
                        val offerItemItem = document.toObject(OfferItemDetailsModel::class.java)
                        offerItemList.add(offerItemItem)
                    }
                    _offerItemDetails.value = offerItemList
                }
            }
    }


    // Offers available or not for current user
    private val _offerAvailableForCurrentUserDetails = MutableLiveData<UserOfferDataModel>()
    val offerAvailableForCurrentUserDetails: LiveData<UserOfferDataModel>
        get() = _offerAvailableForCurrentUserDetails

    fun getOfferAvailableForCurrentUser(context: Context) {
        db.collection("allUsers").document(mAuth.uid.toString())
            .addSnapshotListener { value, error ->
                error?.let {
                    context.toast("Try Again...")
                }
                value?.let {
                    val offerAvailableForCurrentUser =
                        it.toObject(UserOfferDataModel::class.java)
                    _offerAvailableForCurrentUserDetails.value = offerAvailableForCurrentUser
                }
            }
    }
}