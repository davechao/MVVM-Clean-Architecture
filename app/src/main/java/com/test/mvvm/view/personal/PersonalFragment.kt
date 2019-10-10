package com.test.mvvm.view.personal

import android.os.Bundle
import android.view.View
import com.test.mvvm.R
import com.test.mvvm.databinding.FragmentPersonalBinding
import com.test.mvvm.view.base.BaseFragment

class PersonalFragment : BaseFragment<FragmentPersonalBinding, PersonalViewModel>() {

    override fun setViewModelToBinding(binding: FragmentPersonalBinding) {
        binding.viewModel = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_personal
    }

    override fun getViewModelClass(): Class<PersonalViewModel>? {
        return PersonalViewModel::class.java
    }

    override fun onResume() {
        super.onResume()
        mainViewModel?.bottomNavigationVisibility?.value = View.VISIBLE
    }

}