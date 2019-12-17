package com.order.ordercheckout.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.actions.Actions
import com.network.model.orderlist.OrderListResponse
import com.order.R
import com.order.orderlist.ui.adapter.OrderCheckoutAdapter
import kotlinx.android.synthetic.main.activity_order_checkout.*
import java.util.ArrayList

class OrderCheckoutActivity : AppCompatActivity() {

    private val filtered by lazy {
        intent.getParcelableArrayListExtra<OrderListResponse>("PRODUCT_FILTERED")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_checkout)
        initAdapterProducts()
        initComponents()
        sumProducts()
        initToolbar()
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = getString(R.string.order_checkout_toolbar)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_back)
        }
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun initAdapterProducts() {
        with(rvOrderSummary) {
            adapter = filtered?.let {
                OrderCheckoutAdapter(it)
            }
        }
    }

    private fun initComponents() {
        btnOrderProduct.setOnClickListener {
            Actions.openConfirmationOrder(this@OrderCheckoutActivity)
        }
    }

    private fun sumProducts() {
        val totalPrice: Int = this.filtered.map { it.price }.sum()
        tvSummarySubtitlePrice.text = totalPrice.toString()
    }
}
