package com.example.intershiptask.screens.main

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.intershiptask.R
import com.example.intershiptask.core.Service
import com.example.intershiptask.databinding.ActivityMainBinding
import com.example.intershiptask.screens.items.ItemsListFragmentDirections
import com.example.intershiptask.utils.ACTIVITY_ACTION
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {
    private val viewModel by viewModel<MainViewModel>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val requestPermissionLauncher: ActivityResultLauncher<String> =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
                if (result) {
                    if (!viewModel.isServiceRunning) {
                        ContextCompat.startForegroundService(
                            this,
                            Service.createIntent(
                                this,
                                Service.ACTION_START_FOREGROUND_SERVICE
                            )
                        )
                        viewModel.isServiceRunning = true
                    }
                }
            }

        if (!viewModel.isServiceRunning && Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }

        lifecycleScope.launch {
            viewModel.mainNavigationFlow.collect { event ->
                when (event) {
                    is MainNavigationEvent.NavigateToDetailFragment -> navigateToDetailItem(
                        event.id
                    )

                    is MainNavigationEvent.NavigateToItemListFragment -> navigateToItemList()
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (intent?.action == ACTIVITY_ACTION) {
            viewModel.handleEvent(MainEvent.OpenDetailItemOrListFromNotify)
        }
    }

    private fun navigateToDetailItem(itemId: Int) {
        val navController = findNavController(R.id.fcMain)
        val action = ItemsListFragmentDirections.actionItemsListFragmentToDetailItemFragment(itemId)
        navController.navigate(action)
    }

    private fun navigateToItemList() {
        val navController = findNavController(R.id.fcMain)
        navController.navigate(R.id.itemsListFragment)
    }
}

