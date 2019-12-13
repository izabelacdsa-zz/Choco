package com.order.orderlist.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.order.R
import com.order.orderlist.ui.adapter.model.OrderCheckoutModel

class OrderCheckoutAdapter : RecyclerView.Adapter<FileOrderCheckoutViewHolder>() {
    var productList = mutableListOf<OrderCheckoutModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FileOrderCheckoutViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_order_summary,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = productList.size

    override fun onBindViewHolder(holder: FileOrderCheckoutViewHolder, position: Int) =
        holder.bind(productList[position])

}

class FileOrderCheckoutViewHolder(containerView: View) :
    RecyclerView.ViewHolder(containerView) {
    private val tvOrderSummaryProductName
            by lazy { itemView.findViewById<AppCompatTextView>(R.id.tvOrderSummaryProductName) }
    private val tvOrderSummaryProductPriceTotal
            by lazy { itemView.findViewById<AppCompatTextView>(R.id.tvOrderSummaryProductPriceTotal) }

    fun bind(orderCheckoutModel: OrderCheckoutModel) {
        tvOrderSummaryProductName.text = orderCheckoutModel.productName
        tvOrderSummaryProductPriceTotal.text = orderCheckoutModel.productPrice
    }
}
