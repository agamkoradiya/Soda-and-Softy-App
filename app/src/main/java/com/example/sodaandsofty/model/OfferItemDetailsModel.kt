package com.example.sodaandsofty.model

data class OfferItemDetailsModel(
    var description: String = "",
    var documentName: String = "",
    var iconArrayNameI: String = "",
    var iconArrayNameII: String = "",
    var iconArrayNameIII: String = "",
    var indexI: Int? = null,
    var indexII: Int? = null,
    var indexIII: Int? = null,
    var nameI: String = "",
    var nameII: String = "",
    var nameIII: String = "",
    var priceI: String = "",
    var priceII: String = "",
    var priceIII: String = ""
)