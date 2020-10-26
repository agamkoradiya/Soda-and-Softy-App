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

class ShingSodaAdapter(private val shingSodaIconArray: TypedArray) :
    RecyclerView.Adapter<ShingSodaAdapter.MyViewHolder>() {

    private var shingSodaDetails = emptyList<ItemDetailsModel>()

    fun setData(shingSodaDetails: List<ItemDetailsModel>) {
        this.shingSodaDetails = shingSodaDetails
        Log.d("ShingSodaAdapter", "data found ")
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.soda_item_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentShingSodaDetail = shingSodaDetails[position]
        if (currentShingSodaDetail.index!! > shingSodaIconArray.length()) {
            holder.itemView.item_icon.setImageResource(shingSodaIconArray.getResourceId(0, 0))
        } else {
            holder.itemView.item_icon.setImageResource(
                shingSodaIconArray.getResourceId(
                    currentShingSodaDetail.index!!,
                    0
                )
            )
        }
        holder.itemView.item_name.text = currentShingSodaDetail.name
        holder.itemView.item_price.text = currentShingSodaDetail.price
        holder.itemView.item_background_card_view.setCardBackgroundColor(
            Color.parseColor(
                currentShingSodaDetail.color.toString()
            )
        )
        holder.itemView.item_price.setTextColor(
            Color.parseColor(
                currentShingSodaDetail.textColor.toString()
            )
        )
    }

    override fun getItemCount(): Int {
        return shingSodaDetails.size
    }
}