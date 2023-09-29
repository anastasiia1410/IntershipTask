package com.example.intershiptask.screens.items

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.intershiptask.core.BaseFragment
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

        lifecycleScope.launch {
            viewModel.state.collect { state ->
                adapter.submitList(state.items)
            }
        }

        viewModel.showList()


//        adapter.onItemClick = { item ->
//            viewModel.handleEvent(ItemEvents.OpenDetailItemById(item.id))
//            val updatedNotification =
//                createNotification(requireContext(), item.id)
//            requireContext().notificationManager.notify(NOTIFICATION_ID, updatedNotification)
//            val action =
//                ItemsListFragmentDirections.actionItemsListFragmentToDetailItemFragment(item.id)
//            findNavController().navigate(action)
//
//        }
    }
}

