package com.example.intershiptask.screens.main

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import com.example.intershiptask.R
import com.example.intershiptask.core.Service
import com.example.intershiptask.databinding.ActivityMainBinding
import com.example.intershiptask.screens.items.ItemsListFragmentDirections
import com.example.intershiptask.utils.ACTIVITY_ACTION
import org.koin.android.ext.android.inject


class MainActivity : AppCompatActivity(), MainView {
    private lateinit var binding: ActivityMainBinding
    private val presenter by inject<MainPresenter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter.attachView(this)
        requestPermissionAndStartService()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (intent?.action == ACTIVITY_ACTION) {
            presenter.setIdValue()
        }
    }

    override fun requestPermissionAndStartService() {
        val requestPermissionLauncher: ActivityResultLauncher<String> =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
                if (result) {
                    if (!presenter.isServiceRunning) {
                        ContextCompat.startForegroundService(
                            this,
                            Service.createIntent(
                                this,
                                Service.ACTION_START_FOREGROUND_SERVICE
                            )
                        )
                        presenter.isServiceRunning = true
                    }
                }
            }

        if (!presenter.isServiceRunning && Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }

    override fun navigate(id: Int) {
        val navController = findNavController(R.id.fcMain)
        if (id != -1) {
            val action =
                ItemsListFragmentDirections.actionItemsListFragmentToDetailItemFragment(
                    id
                )
            navController.navigate(action)
        } else {
            navController.navigate(R.id.itemsListFragment)
        }
    }
}

