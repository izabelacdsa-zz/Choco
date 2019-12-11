package com.order.ordercheckout.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.actions.Actions
import com.order.R
import kotlinx.android.synthetic.main.activity_order_checkout.*

class OrderCheckoutActivity : AppCompatActivity() {

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
