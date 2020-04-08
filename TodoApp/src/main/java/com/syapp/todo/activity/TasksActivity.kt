package com.syapp.todo.activity

import android.Manifest
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.syapp.basecomponent.BaseActivity
import com.syapp.todo.R
import com.syapp.todo.util.PermissionManager
import com.syapp.todo.util.showSnackbar

class TasksActivity : BaseActivity() {

    private lateinit var appbarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tasks_activity)

        setSupportActionBar(findViewById(R.id.toolbar))

        val navController = findNavController(R.id.fragment_nav_host)
        appbarConfiguration = AppBarConfiguration.Builder(R.id.tasks_fragment_dest)
            .setDrawerLayout(findViewById(R.id.drawer_layout))
            .build()

        setupActionBarWithNavController(navController, appbarConfiguration)

        findViewById<NavigationView>(R.id.navigation_view).setupWithNavController(navController)

        PermissionManager(supportFragmentManager)
            .request(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            .subscribe({ permissionResult ->
                if (permissionResult.isGranted) {
                    window.decorView.showSnackbar("is All Granted")
                } else if (!permissionResult.shouldShowRequestPermissionRationale) {
                    window.decorView.showSnackbar("should Show RequestPermission Rationale")
                } else {
                    window.decorView.showSnackbar("else?")
                }
            }, Throwable::printStackTrace)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.fragment_nav_host).navigateUp(appbarConfiguration)
                || super.onSupportNavigateUp()
    }
}
