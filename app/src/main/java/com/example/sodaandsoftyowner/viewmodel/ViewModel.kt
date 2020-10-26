package com.example.sodaandsoftyowner.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ViewModel @ViewModelInject constructor(
    private val db: FirebaseFirestore,
    private val mAuth: FirebaseAuth
) : ViewModel() {

    fun changeAvailability(uId: String, offerName: String) {
        db.collection("allUsers").document(uId).update(offerName, false)
    }

    fun resetAvailability() {
        db.collection("allUsers").get().addOnSuccessListener {

            val map: MutableMap<String, Boolean> = mutableMapOf()
            map["offer1Available"] = true
            map["offer2Available"] = true
            map["offer3Available"] = true

            for (document in it) {
                document.reference.update(map as Map<String, Any>)
            }
        }
    }
}