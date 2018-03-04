package com.apollo.base.view

import android.content.res.Configuration
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import com.apollo.base.R
import com.apollo.base.extensions.doIfNotNull


abstract class BaseDrawerActivity<P> : BaseActivity<P>() where P : BasePresenter {

    /**
     * Layout reference to the drawer view. To be fill with the kotlin extension reference.
     */
    abstract val _drawerLayout: DrawerLayout

    /**
     * Layout reference to the navigation view inside the drawer. To be fill with the kotlin
     * extension reference.
     */
    abstract val _drawerNavigationView: NavigationView

    /**
     * Id resource of the menu item. Used to ignore the listener when we are already in the selected
     * screen.
     */
    abstract val drawerThisItemId: Int

    /**
     * Lambda that gets executed when one of the items in the navigation view gets selected. By
     * default it ignores drawerThisItemId
     */
    abstract val drawerOnNavigationItemSelected: (Int) -> Unit

    private var drawerActionBarToggle: ActionBarDrawerToggle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setUpDrawer()
    }


    private fun setUpDrawer() {
        drawerActionBarToggle = ActionBarDrawerToggle(this,
                _drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerActionBarToggle.doIfNotNull { _drawerLayout.addDrawerListener(it) }
        drawerActionBarToggle?.syncState()

        _drawerNavigationView.setCheckedItem(drawerThisItemId)
        _drawerNavigationView.setNavigationItemSelectedListener(this::onDrawerItemSelected)
    }

    private fun onDrawerItemSelected(menuItem: MenuItem): Boolean {
        val id = menuItem.itemId
        if (id != drawerThisItemId) {
            drawerOnNavigationItemSelected(id)
        }
        return true
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        drawerActionBarToggle?.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        drawerActionBarToggle?.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (drawerActionBarToggle?.onOptionsItemSelected(item) == true)
            return true
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (_drawerLayout.isDrawerOpen(GravityCompat.START)) {
            _drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
