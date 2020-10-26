package com.example.sodaandsofty.adapter

import android.content.res.TypedArray
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.sodaandsofty.R
import com.example.sodaandsofty.model.OfferItemDetailsModel
import com.example.sodaandsofty.model.UserOfferDataModel
import com.example.sodaandsofty.ui.fragment.OffersFragmentDirections
import kotlinx.android.synthetic.main.offer_item_layout.view.*

class OffersAdapter(
    private val regularSodaIcons: TypedArray,
    private val shingSodaIcons: TypedArray,
    private val softyIcons: TypedArray,
    private val flavourCreamIcons: TypedArray,
    private val juiceIcons: TypedArray,
    private val uId: String,
    private val OffersFragmentDirections: OffersFragmentDirections.Companion,
    private val findNavController: NavController
) :
    RecyclerView.Adapter<OffersAdapter.MyViewHolder>() {

    private var offerItemDetails = emptyList<OfferItemDetailsModel>()
    private var offerAvailableForCurrentUserDetails = UserOfferDataModel()

    fun setData(offerItemDetails: List<OfferItemDetailsModel>) {
        this.offerItemDetails = offerItemDetails
        Log.d("OffersAdapter", "data found ")
        notifyDataSetChanged()
    }

    fun setAvailableDetailData(offerAvailableForCurrentUserDetails: UserOfferDataModel) {
        this.offerAvailableForCurrentUserDetails = offerAvailableForCurrentUserDetails
        Log.d("OffersAdapter", "data found ")
        notifyDataSetChanged()
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.offer_item_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentOffer = offerItemDetails[position]

        holder.itemView.apply {
            this.description_txt.text = currentOffer.description
            this.item_name_i.text = currentOffer.nameI
            this.item_name_ii.text = currentOffer.nameII
            this.item_name_iii.text = currentOffer.nameIII
            this.item_price_i.text = currentOffer.priceI
            this.item_price_ii.text = currentOffer.priceII
            this.item_price_iii.text = currentOffer.priceIII

            when (position) {
                0 -> {
                    if (offerAvailableForCurrentUserDetails.offer1Available) {
                        this.isAvailable_txt.text = "Available"
                        this.isAvailable_txt.setTextColor(Color.GREEN)
                    } else {
                        this.isAvailable_txt.text = "Unavailable"
                        this.isAvailable_txt.setTextColor(Color.RED)
                    }
                }
                1 -> {
                    if (offerAvailableForCurrentUserDetails.offer2Available) {
                        this.isAvailable_txt.text = "Available"
                        this.isAvailable_txt.setTextColor(Color.GREEN)
                    } else {
                        this.isAvailable_txt.text = "Unavailable"
                        this.isAvailable_txt.setTextColor(Color.RED)
                    }
                }
                2 -> {
                    if (offerAvailableForCurrentUserDetails.offer3Available) {
                        this.isAvailable_txt.text = "Available"
                        this.isAvailable_txt.setTextColor(Color.GREEN)
                    } else {
                        this.isAvailable_txt.text = "Unavailable"
                        this.isAvailable_txt.setTextColor(Color.RED)
                    }
                }
            }
            try {
                when (currentOffer.iconArrayNameI) {
                    "regular_soda_icons" -> {
                        this.item_icon_i.setImageResource(
                            regularSodaIcons.getResourceId(
                                currentOffer.indexI!!,
                                0
                            )
                        )
                    }

                    "shing_soda_icons" -> {
                        this.item_icon_i.setImageResource(
                            shingSodaIcons.getResourceId(
                                currentOffer.indexI!!,
                                0
                            )
                        )
                    }

                    "softy_icons" -> {
                        this.item_icon_i.setImageResource(
                            softyIcons.getResourceId(
                                currentOffer.indexI!!,
                                0
                            )
                        )
                    }

                    "flavour_with_ice_cream" -> {
                        this.item_icon_i.setImageResource(
                            flavourCreamIcons.getResourceId(
                                currentOffer.indexI!!,
                                0
                            )
                        )
                    }

                    "juice" -> {
                        this.item_icon_i.setImageResource(
                            juiceIcons.getResourceId(
                                currentOffer.indexI!!,
                                0
                            )
                        )
                    }

                    else -> {

                    }
                }

                when (currentOffer.iconArrayNameII) {
                    "regular_soda_icons" -> {
                        this.item_icon_ii.setImageResource(
                            regularSodaIcons.getResourceId(
                                currentOffer.indexII!!,
                                0
                            )
                        )
                    }

                    "shing_soda_icons" -> {
                        this.item_icon_ii.setImageResource(
                            shingSodaIcons.getResourceId(
                                currentOffer.indexII!!,
                                0
                            )
                        )
                    }

                    "softy_icons" -> {
                        this.item_icon_ii.setImageResource(
                            softyIcons.getResourceId(
                                currentOffer.indexII!!,
                                0
                            )
                        )
                    }

                    "flavour_with_ice_cream" -> {
                        this.item_icon_ii.setImageResource(
                            flavourCreamIcons.getResourceId(
                                currentOffer.indexII!!,
                                0
                            )
                        )
                    }

                    "juice" -> {
                        this.item_icon_ii.setImageResource(
                            juiceIcons.getResourceId(
                                currentOffer.indexII!!,
                                0
                            )
                        )
                    }

                    else -> {

                    }
                }

                when (currentOffer.iconArrayNameIII) {
                    "regular_soda_icons" -> {
                        this.item_icon_iii.setImageResource(
                            regularSodaIcons.getResourceId(
                                currentOffer.indexIII!!,
                                0
                            )
                        )
                    }

                    "shing_soda_icons" -> {
                        this.item_icon_iii.setImageResource(
                            shingSodaIcons.getResourceId(
                                currentOffer.indexIII!!,
                                0
                            )
                        )
                    }

                    "softy_icons" -> {
                        this.item_icon_iii.setImageResource(
                            softyIcons.getResourceId(
                                currentOffer.indexIII!!,
                                0
                            )
                        )
                    }

                    "flavour_with_ice_cream" -> {
                        this.item_icon_iii.setImageResource(
                            flavourCreamIcons.getResourceId(
                                currentOffer.indexIII!!,
                                0
                            )
                        )
                    }

                    "juice" -> {
                        this.item_icon_iii.setImageResource(
                            juiceIcons.getResourceId(
                                currentOffer.indexIII!!,
                                0
                            )
                        )
                    }

                    else -> {

                    }
                }

            } catch (e: ArrayIndexOutOfBoundsException) {
                Log.d("OffersAdapter", "ArrayIndexOutOfBoundsException")
            }

            this.card_view.setOnClickListener {
                val action = OffersFragmentDirections.actionOffersFragmentToQrCodeFragment(
                    uId,
                    currentOffer.documentName,
                    currentOffer.description
                )
                when (position) {
                    0 -> {
                        if (offerAvailableForCurrentUserDetails.offer1Available) {
                            findNavController.navigate(action)
                        } else {
                            Toast.makeText(this.context, "Already used...", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                    1 -> {
                        if (offerAvailableForCurrentUserDetails.offer2Available) {
                            findNavController.navigate(action)
                        } else {
                            Toast.makeText(this.context, "Already used...", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                    2 -> {
                        if (offerAvailableForCurrentUserDetails.offer3Available) {
                            findNavController.navigate(action)
                        } else {
                            Toast.makeText(this.context, "Already used...", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            }
        }

//        if (currentJuiceDetail.index!! > juiceIconArray.length()) {
//            holder.itemView.item_icon.setImageResource(juiceIconArray.getResourceId(0, 0))
//        } else {
//            holder.itemView.item_icon.setImageResource(
//                juiceIconArray.getResourceId(
//                    currentJuiceDetail.index!!,
//                    0
//                )
//            )
//        }
    }

    override fun getItemCount(): Int {
        return offerItemDetails.size
    }
}