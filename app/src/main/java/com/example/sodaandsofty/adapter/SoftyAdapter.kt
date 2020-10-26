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

class SoftyAdapter(private val softyIconArray: TypedArray) :
    RecyclerView.Adapter<SoftyAdapter.MyViewHolder>() {

    private var softyDetails = emptyList<ItemDetailsModel>()

    fun setData(softyDetails: List<ItemDetailsModel>) {
        this.softyDetails = softyDetails
        Log.d("SoftyAdapter", "data found ")
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.soda_item_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.d("SoftyAdapter", "SoftyDetail: $softyDetails")

        Log.d("SoftyAdapter", "softyDetails.size: ${softyDetails.size}")
        Log.d("SoftyAdapter", "softyIconArray.length(): ${softyIconArray.length()}")

        val currentSoftyDetail = softyDetails[position]
        if (currentSoftyDetail.index!! > softyIconArray.length()) {
            holder.itemView.item_icon.setImageResource(softyIconArray.getResourceId(0, 0))
        } else {
            holder.itemView.item_icon.setImageResource(
                softyIconArray.getResourceId(
                    currentSoftyDetail.index!!,
                    0
                )
            )
        }
        holder.itemView.item_name.text = currentSoftyDetail.name
        holder.itemView.item_price.text = currentSoftyDetail.price
        holder.itemView.item_background_card_view.setCardBackgroundColor(
            Color.parseColor(
                currentSoftyDetail.color.toString()
            )
        )
        holder.itemView.item_price.setTextColor(
            Color.parseColor(
                currentSoftyDetail.textColor.toString()
            )
        )
    }

    override fun getItemCount(): Int {
        return softyDetails.size
    }
}