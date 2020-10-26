package com.example.sodaandsofty.adapter

import android.content.res.TypedArray
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sodaandsofty.R
import com.example.sodaandsofty.model.ItemDetailsModel
import kotlinx.android.synthetic.main.soda_item_layout.view.*

class FlavourCreamAdapter(private val flavourCreamIconArray: TypedArray) :
    RecyclerView.Adapter<FlavourCreamAdapter.MyViewHolder>() {

    private var flavourCreamDetails = emptyList<ItemDetailsModel>()

    fun setData(flavourCreamDetails: List<ItemDetailsModel>) {
        this.flavourCreamDetails = flavourCreamDetails
        Log.d("FlavourCreamAdapter", "data found ")
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.soda_item_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentFlavourCreamDetail = flavourCreamDetails[position]
        if (currentFlavourCreamDetail.index!! > flavourCreamIconArray.length()) {
            holder.itemView.item_icon.setImageResource(flavourCreamIconArray.getResourceId(0, 0))
        } else {
            holder.itemView.item_icon.setImageResource(
                flavourCreamIconArray.getResourceId(
                    currentFlavourCreamDetail.index!!,
                    0
                )
            )
        }
        holder.itemView.item_name.text = currentFlavourCreamDetail.name
        holder.itemView.item_price.text = currentFlavourCreamDetail.price
        holder.itemView.item_background_card_view.setCardBackgroundColor(
            Color.parseColor(
                currentFlavourCreamDetail.color.toString()
            )
        )
        holder.itemView.item_price.setTextColor(
            Color.parseColor(
                currentFlavourCreamDetail.textColor.toString()
            )
        )
    }

    override fun getItemCount(): Int {
        return flavourCreamDetails.size
    }
}