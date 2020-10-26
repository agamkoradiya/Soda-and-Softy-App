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

class RegularSodaAdapter(private val regularSodaIconArray: TypedArray) :
    RecyclerView.Adapter<RegularSodaAdapter.MyViewHolder>() {

    private var regularSodaDetails = emptyList<ItemDetailsModel>()

    fun setData(regularSodaDetails: List<ItemDetailsModel>) {
        this.regularSodaDetails = regularSodaDetails
        Log.d("RegularSodaAdapter", "data found ")
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.soda_item_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentRegularSodaDetail = regularSodaDetails[position]
        if (currentRegularSodaDetail.index!! > regularSodaIconArray.length()) {
            holder.itemView.item_icon.setImageResource(regularSodaIconArray.getResourceId(0, 0))
        } else {
            holder.itemView.item_icon.setImageResource(
                regularSodaIconArray.getResourceId(
                    currentRegularSodaDetail.index!!,
                    0
                )
            )
        }
        holder.itemView.item_name.text = currentRegularSodaDetail.name
        holder.itemView.item_price.text = currentRegularSodaDetail.price
        holder.itemView.item_background_card_view.setCardBackgroundColor(
            Color.parseColor(
                currentRegularSodaDetail.color.toString()
            )
        )
        holder.itemView.item_price.setTextColor(
            Color.parseColor(
                currentRegularSodaDetail.textColor.toString()
            )
        )
    }

    override fun getItemCount(): Int {
        return regularSodaDetails.size
    }
}