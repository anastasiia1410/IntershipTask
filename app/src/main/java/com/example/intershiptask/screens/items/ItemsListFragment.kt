package com.example.intershiptask.screens.items

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.intershiptask.core.BaseFragment
import com.example.intershiptask.core.Service.Companion.NOTIFICATION_ID
import com.example.intershiptask.databinding.FragmentListItemsBinding
import com.example.intershiptask.screens.entity.Item
import com.example.intershiptask.utils.createNotification
import com.example.intershiptask.utils.notificationManager
import org.koin.android.ext.android.inject

class ItemsListFragment : BaseFragment<FragmentListItemsBinding>(), ItemsView {
    private val presenter by inject<ItemsPresenter>()
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

        presenter.attachView(this)
        presenter.loadItemsList()

        adapter.onItemClick = { item ->
            presenter.onItemClick(item)
        }
    }

    override fun showItemsList(itemsList: List<Item>) {
        adapter.submitList(itemsList)
    }

    override fun handlerClick(item: Item) {
        val updatedNotification =
            createNotification(requireContext(), item.id)
        requireContext().notificationManager.notify(NOTIFICATION_ID, updatedNotification)
        val action =
            ItemsListFragmentDirections.actionItemsListFragmentToDetailItemFragment(item.id)
        findNavController().navigate(action)
    }
}

