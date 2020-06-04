package com.enesseval.moviedeneme.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.ui.AppBarConfiguration
import com.enesseval.moviedeneme.R
import com.enesseval.moviedeneme.model.Movie
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.fragment_layout.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar : Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.icon_default)
        toolbar.setTitleTextColor(resources.getColor(R.color.white))


        val leftDraw : DrawerLayout = findViewById(R.id.drawer_layout)
        toolbar.setNavigationOnClickListener {
            if (leftDraw.isDrawerOpen(GravityCompat.START)) {
                leftDraw.closeDrawer(GravityCompat.START)
            } else {
                leftDraw.openDrawer(GravityCompat.START)
            }
        }
        val navView : NavigationView = findViewById(R.id.nav_left)
        navView.setNavigationItemSelectedListener(this)

    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean{
        val leftDraw : DrawerLayout = findViewById(R.id.drawer_layout)
        val but = findViewById<View>(R.id.goProfile)

        when(item.itemId){
            R.id.goProfile -> {
                fragment.childFragmentManager.beginTransaction().replace(
                    R.id.fragment,
                    ProfileFragment()
                ).commit()
                if(leftDraw!!.isDrawerOpen(GravityCompat.START)){
                    leftDraw!!.closeDrawer(GravityCompat.START)
                }
            }
        }
        return true
    }
    fun filter(view: View){
        val rightDraw : DrawerLayout = findViewById(R.id.drawer_layout)
        if (rightDraw.isDrawerOpen(GravityCompat.END)){
            rightDraw.closeDrawer(GravityCompat.END)
        }else{
            rightDraw.openDrawer(GravityCompat.END)
        }
    }

}
