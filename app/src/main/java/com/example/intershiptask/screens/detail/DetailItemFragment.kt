package com.example.intershiptask.screens.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.intershiptask.R
import com.example.intershiptask.core.BaseFragment
import com.example.intershiptask.databinding.FragmentDetailItemBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailItemFragment : BaseFragment<FragmentDetailItemBinding>() {
    private val viewModel by viewModel<DetailViewModel>()

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): FragmentDetailItemBinding {
        return FragmentDetailItemBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getItemById()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    with(binding) {
                        tvId.text = getString(R.string.id, state.item?.id)
                        tvName.text = getString(R.string.name, state.item?.name)
                        tvDescription.text =
                            getString(R.string.description, state.item?.description)
                    }
                }
            }
        }
    }
}