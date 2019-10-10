package com.test.mvvm.view.base

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.AndroidInjection
import javax.inject.Inject

open class BaseActivity<B : ViewDataBinding, V : BaseViewModel> : CustomDaggerActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: V
    lateinit var binding: B
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(getViewModelClass()!!)
        binding = DataBindingUtil.setContentView(this, getLayoutId())
    }

    open fun getLayoutId(): Int {
        return -1
    }

    open fun getViewModelClass(): Class<V>? {
        return null
    }
}