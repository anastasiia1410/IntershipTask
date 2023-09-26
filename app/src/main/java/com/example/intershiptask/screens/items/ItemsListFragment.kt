package com.example.intershiptask.screens.items

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.intershiptask.core.BaseFragment
import com.example.intershiptask.core.Service
import com.example.intershiptask.core.Service.Companion.NOTIFICATION_ID
import com.example.intershiptask.databinding.FragmentListItemsBinding
import com.example.intershiptask.utils.createNotification
import com.example.intershiptask.utils.notificationManager
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ItemsListFragment : BaseFragment<FragmentListItemsBinding>() {
    private val viewModel by viewModel<ItemsViewModel>()
    private val adapter by lazy { ItemsAdapter() }

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): FragmentListItemsBinding {
        return FragmentListItemsBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            rvRecycler.layoutManager = LinearLayoutManager(requireContext())
            rvRecycler.adapter = adapter
        }

        adapter.onItemClick = { item ->
            val updatedNotification =
                createNotification(requireContext(), item.id, "Chose item with id ${item.id}")
            requireContext().notificationManager.notify(NOTIFICATION_ID, updatedNotification)
            viewModel.saveId(item.id)
            val action =
                ItemsListFragmentDirections.actionItemsListFragmentToDetailItemFragment(item.id)
            findNavController().navigate(action)
        }
    }

    companion object {
        const val ITEM_KEY = "item_key"
        const val TAG = "ServiceSocket"
    }
}

