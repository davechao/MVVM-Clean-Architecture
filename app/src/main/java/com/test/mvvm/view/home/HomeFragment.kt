package com.test.mvvm.view.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.mvvm.R
import com.test.mvvm.databinding.FragmentHomeBinding
import com.test.mvvm.view.adapter.UserInfoListAdapter
import com.test.mvvm.view.base.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    override fun setViewModelToBinding(binding: FragmentHomeBinding) {
        binding.viewModel = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userInfoListAdapter = UserInfoListAdapter()

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = userInfoListAdapter
        }

        viewModel.isLoading.observe(this, Observer {
            if (it) {
                progressHUD?.show()
            } else {
                progressHUD?.dismiss()
            }
        })

        viewModel.getUsers().observe(this, Observer {
            userInfoListAdapter.submitList(it)
        })
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun getViewModelClass(): Class<HomeViewModel>? {
        return HomeViewModel::class.java
    }

    override fun onResume() {
        super.onResume()
        mainViewModel?.bottomNavigationVisibility?.value = View.VISIBLE
    }

}