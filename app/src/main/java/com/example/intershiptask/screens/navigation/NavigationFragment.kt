package com.example.intershiptask.screens.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.intershiptask.screens.items.ItemsListFragmentDirections
import com.example.intershiptask.screens.main.MainViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


@Suppress("DEPRECATION")
class NavigationFragment : Fragment() {
    private val viewModel by sharedViewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            viewModel.isFirstStart.collect { isFirstStart ->
                if (isFirstStart) {
                    navigateToStartScreen()
                } else {
                    viewModel.isNotificationClick.collect { onNotificationClick ->
                        if (onNotificationClick) {
                            val id = viewModel.idFlow.value
                            navigate(id)
                        }
                    }
                }
            }
        }

        // viewModel.setScreen()
    }

    private fun navigate(id: Int) {
        if (id != -1) {
            val action =
                ItemsListFragmentDirections.actionItemsListFragmentToDetailItemFragment(
                    id
                )
            findNavController().navigate(action)

        } else {
            val action =
                NavigationFragmentDirections.actionNavigationFragmentToItemsListFragment()
            findNavController().navigate(action)
        }
    }

    private fun navigateToStartScreen() {
        val action =
            NavigationFragmentDirections.actionNavigationFragmentToItemsListFragment()
        findNavController().navigate(action)
    }
}



