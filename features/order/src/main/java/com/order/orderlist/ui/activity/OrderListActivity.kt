package com.order.orderlist.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.actions.Actions
import com.extensions.observeNotNull
import com.extensions.showErrorDialog
import com.extensions.showView
import com.network.model.orderlist.OrderListResponse
import com.order.R
import com.order.orderlist.ui.adapter.OrderListAdapter
import com.order.orderlist.viewmodel.OrderListViewModel
import kotlinx.android.synthetic.main.activity_order_list.*

class OrderListActivity : AppCompatActivity() {

    private val token: String? by lazy {
        intent.extras?.getString("LOGIN_TOKEN")
    }

    private val filtered = mutableListOf<OrderListResponse>()

    private val orderListViewModel by lazy {
        ViewModelProvider(this).get(OrderListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_list)
        initObservers()
        initButtonProceedToCart()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.order_list_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_exit -> {
            Actions.openLoginFromOrderList(this@OrderListActivity)
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    private fun initButtonProceedToCart() {
        btnProceedToCart.setOnClickListener {
            if (filtered.isEmpty()) {
                Toast.makeText(
                    this@OrderListActivity,
                    R.string.order_list_select_item,
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Actions.openOrderCheckout(
                    this@OrderListActivity,
                    ArrayList(filtered)
                )
            }
        }
    }

    private fun animateOrderProductList() = rvOrderList.scheduleLayoutAnimation()

    private fun initObservers() = with(orderListViewModel) {
        mutableLiveDataOrderListSuccess.observeNotNull(this@OrderListActivity) {
            val orderListResponse = it as List<OrderListResponse>
            with(rvOrderList) {
                adapter = OrderListAdapter(orderListResponse,
                    { orderListAdd ->
                        filtered.add(orderListAdd)
                    }, { orderListRemove ->
                        filtered.remove(orderListRemove)
                    })

                animateOrderProductList()
            }
        }
        mutableLiveDataLoading.observeNotNull(this@OrderListActivity) { showProgress ->
            progressBar.showView(showProgress)
        }
        mutableLiveDataOrderListError.observeNotNull(this@OrderListActivity) {
            showErrorDialog(it)
        }
        orderListViewModel.getOrderList(token)
    }
}
