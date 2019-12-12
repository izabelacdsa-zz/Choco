package com.order.ordercheckout.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.actions.Actions
import com.order.R
import kotlinx.android.synthetic.main.activity_confirmation_order.*

class ConfirmationOrderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation_order)
        initComponents()
    }

    private fun initComponents() {
        btnLeaveApp.setOnClickListener {
            Actions.openLoginFromOrderList(this@ConfirmationOrderActivity)
        }
    }
}
