package com.example.todoapp.activity

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.todoapp.R
import com.google.android.material.navigation.NavigationView
import dagger.android.support.DaggerAppCompatActivity

class TasksActivity : DaggerAppCompatActivity() {

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
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.fragment_nav_host).navigateUp(appbarConfiguration)
                || super.onSupportNavigateUp()
    }
}
