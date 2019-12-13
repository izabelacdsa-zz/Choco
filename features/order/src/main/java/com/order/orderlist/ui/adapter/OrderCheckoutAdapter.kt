package com.order.orderlist.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.network.model.orderlist.OrderListResponse
import com.order.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_order_summary.view.*
import java.util.ArrayList

class OrderCheckoutAdapter(
    private var productList: ArrayList<OrderListResponse>
) : RecyclerView.Adapter<FileOrderCheckoutViewHolder>() {

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

class FileOrderCheckoutViewHolder(override val containerView: View) :
    RecyclerView.ViewHolder(containerView),
    LayoutContainer {
    fun bind(
        orderList: OrderListResponse
    ) {
        with(itemView) {
            tvOrderSummaryProductName.text = orderList.name
            tvOrderSummaryProductPriceTotal.text = orderList.price.toString()
        }

        Glide.with(itemView.context)
            .load(orderList.photo)
            .circleCrop()
            .into(itemView.ivOrderSummaryProduct)
    }
}


