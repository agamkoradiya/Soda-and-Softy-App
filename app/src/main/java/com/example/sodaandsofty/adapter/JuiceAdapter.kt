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

class JuiceAdapter(private val juiceIconArray: TypedArray) :
    RecyclerView.Adapter<JuiceAdapter.MyViewHolder>() {

    private var juiceDetails = emptyList<ItemDetailsModel>()

    fun setData(juiceDetails: List<ItemDetailsModel>) {
        this.juiceDetails = juiceDetails
        Log.d("JuiceAdapter", "data found ")
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.soda_item_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentJuiceDetail = juiceDetails[position]
        if (currentJuiceDetail.index!! > juiceIconArray.length()) {
            holder.itemView.item_icon.setImageResource(juiceIconArray.getResourceId(0, 0))
        } else {
            holder.itemView.item_icon.setImageResource(
                juiceIconArray.getResourceId(
                    currentJuiceDetail.index!!,
                    0
                )
            )
        }
        holder.itemView.item_name.text = currentJuiceDetail.name
        holder.itemView.item_price.text = currentJuiceDetail.price
        holder.itemView.item_background_card_view.setCardBackgroundColor(
            Color.parseColor(
                currentJuiceDetail.color.toString()
            )
        )
        holder.itemView.item_price.setTextColor(
            Color.parseColor(
                currentJuiceDetail.textColor.toString()
            )
        )
    }

    override fun getItemCount(): Int {
        return juiceDetails.size
    }
}