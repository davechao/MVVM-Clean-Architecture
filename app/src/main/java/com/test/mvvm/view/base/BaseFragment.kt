package com.test.mvvm.view.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.kaopiz.kprogresshud.KProgressHUD
import com.test.mvvm.view.main.MainViewModel
import javax.inject.Inject

abstract class BaseFragment<B : ViewDataBinding, V : BaseViewModel> : CustomDaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var binding: B
    lateinit var viewModel: V

    abstract fun setViewModelToBinding(binding: B)

    open var mainViewModel: MainViewModel? = null
    var progressHUD: KProgressHUD? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(getViewModelClass()!!)
        activity?.let {
            mainViewModel = ViewModelProviders.of(it).get(MainViewModel::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewModelToBinding(binding)
        progressHUD = KProgressHUD.create(context)
            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
    }

    fun getViewModel(): ViewModel {
        return ViewModelProviders.of(this, viewModelFactory).get(getViewModelClass()!!)
    }

    abstract fun getLayoutId(): Int

    abstract fun getViewModelClass(): Class<V>?
}