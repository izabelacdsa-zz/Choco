package com.order.ordercheckout.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.actions.Actions
import com.order.R
import com.order.orderlist.ui.adapter.model.OrderCheckoutModel
import com.order.orderlist.viewmodel.OrderListViewModel
import kotlinx.android.synthetic.main.activity_order_checkout.*

class OrderCheckoutActivity : AppCompatActivity() {

    private val productName: String? by lazy {
        intent.extras?.getString("PRODUCT_NAME")
    }

    private val productPrice: String? by lazy {
        intent.extras?.getString("PRODUCT_PRICE")
    }

    private val orderCheckoutModel = mutableListOf<OrderCheckoutModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_checkout)
        initComponents()
    }

    private fun initComponents() {
        btnOrderProduct.setOnClickListener {
            Actions.openConfirmationOrder(this@OrderCheckoutActivity)
        }
    }


}
