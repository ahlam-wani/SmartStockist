package com.smartstockist.app.ui.dashboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.core.view.GravityCompat
import com.smartstockist.app.R
import com.smartstockist.app.data.model.Item
import com.smartstockist.app.databinding.ActivityDashboardBinding
import com.smartstockist.app.ui.client.ClientActivity
import com.smartstockist.app.ui.dashboard.adapter.DrawerAdapter
import com.smartstockist.app.utils.SessionManager
import kotlinx.android.synthetic.main.activity_dashboard.view.*
import kotlinx.android.synthetic.main.navigation_layout.view.*

class DashboardActivity : AppCompatActivity() {
    private val binding:ActivityDashboardBinding by lazy {
        ActivityDashboardBinding.inflate(layoutInflater)
    }

    lateinit var sessionManager: SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
            sessionManager= SessionManager(this)
        sessionManager.getToken()
        Toast.makeText(this,"token is ${sessionManager.getToken()}",Toast.LENGTH_SHORT).show()
          binding.toolbar.apply {
              ivMenu.setOnClickListener {
                  if (binding.drawer.isDrawerOpen(GravityCompat.START))
                      binding.drawer.closeDrawer(GravityCompat.END)
                  else
                      binding.drawer.openDrawer(GravityCompat.START)
              }
          }
        binding.drawer.nav_drawer.recycler_view.adapter=DrawerAdapter(getDrawerItems(),listener )
    }
    private fun getDrawerItems():List<Item>{
        val drawerItems:MutableList<Item> = java.util.ArrayList()
        val res=resources
        val arrays = res.getStringArray(R.array.drawer_items)
        val icons=res.obtainTypedArray(R.array.drawer_item_icons)
        for (i in arrays.indices){
            drawerItems.add(Item(arrays[i],icons.getResourceId(i,0)))
        }
        icons.recycle()
        return drawerItems

    }
    private val listener=object : AdapterView.OnItemClickListener {


        override fun onItemClick(p0: AdapterView<*>?, p1: View?, index: Int, p3: Long) {
            when(index){
                0->{
                     startActivity(Intent(this@DashboardActivity, ClientActivity::class.java))
                    Toast.makeText(this@DashboardActivity,"Clients",Toast.LENGTH_SHORT).show()

                }
                1-> {
                    Toast.makeText(this@DashboardActivity,"Orders",Toast.LENGTH_SHORT).show()

                }
                2-> {
                    Toast.makeText(this@DashboardActivity,"New Orders",Toast.LENGTH_SHORT).show()

                }
                3->{
                    Toast.makeText(this@DashboardActivity,"Logout",Toast.LENGTH_SHORT).show()

                }

            }

        }
    }

    override fun onBackPressed() {
        if (binding.drawer.isDrawerOpen(GravityCompat.START)){
            binding.drawer.closeDrawer((GravityCompat.START))
            return
        }
        finishAffinity()
        super.onBackPressed()
    }
}