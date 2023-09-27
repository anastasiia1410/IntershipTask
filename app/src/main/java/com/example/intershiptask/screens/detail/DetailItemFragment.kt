package com.example.intershiptask.screens.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.intershiptask.R
import com.example.intershiptask.core.BaseFragment
import com.example.intershiptask.databinding.FragmentDetailItemBinding
import com.example.intershiptask.screens.entity.Item
import org.koin.android.ext.android.inject

class DetailItemFragment : BaseFragment<FragmentDetailItemBinding>(), DetailView {
    private val presenter by inject<DetailPresenter>()
    private val args by navArgs<DetailItemFragmentArgs>()

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): FragmentDetailItemBinding {
        return FragmentDetailItemBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = args.id
        val item = presenter.getItemById(id)
        setViews(item)
    }

    override fun setViews(item: Item?) {
        with(binding) {
            tvId.text = getString(R.string.id, item?.id)
            tvName.text = getString(R.string.name, item?.name)
            tvDescription.text = getString(R.string.description, item?.description)
        }
    }
}