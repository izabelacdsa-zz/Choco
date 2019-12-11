package com.order.orderlist.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.actions.Actions
import com.order.R
import com.order.orderlist.viewmodel.OrderListViewModel

class OrderListActivity : AppCompatActivity() {

    private val orderListViewModel by lazy {
        ViewModelProvider(this).get(OrderListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_list)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.order_list_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_exit -> {
            Actions.openLoginFromOrderList(this@OrderListActivity )
            true
        }
        else -> {
            super.onOptionsItemSelected(item)

        }
    }
}
