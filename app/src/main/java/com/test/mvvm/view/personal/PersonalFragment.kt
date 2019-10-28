package com.test.mvvm.view.personal

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.test.mvvm.R
import com.test.mvvm.databinding.FragmentPersonalBinding
import com.test.mvvm.model.api.Error
import com.test.mvvm.model.api.Loaded
import com.test.mvvm.model.api.Loading
import com.test.mvvm.model.api.Success
import com.test.mvvm.view.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_personal.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class PersonalFragment : BaseFragment<FragmentPersonalBinding>() {

    private val viewModel by viewModel<PersonalViewModel>()

    companion object {
        const val USER_NAME_DAVE = "davechao"
    }

    override fun setViewModelToBinding(binding: FragmentPersonalBinding) {
        binding.viewModel = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getUserDetail(USER_NAME_DAVE).observe(viewLifecycleOwner, Observer {
            when (it) {
                is Loading -> progressHUD?.show()
                is Loaded -> progressHUD?.dismiss()
                is Success -> {
                    Glide.with(requireContext())
                        .load(it.result.avatarUrl)
                        .placeholder(R.color.colorUserAvatarPlaceHolderColor)
                        .into(binding.imageUserPic)

                    text_login.text = it.result.login
                    text_bio.text = it.result.bio
                    binding.viewUserDetailInfo.setData(
                        login = it.result.login,
                        isSiteAdmin = it.result.isSiteAdmin!!
                    )
                    binding.viewUserDetailLoc.setData(it.result.location)
                    binding.viewUserDetailBlog.setData(it.result.blog)
                }
                is Error -> Timber.e("Error: ${it.errorMessage}")
            }
        })
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_personal
    }

    override fun onResume() {
        super.onResume()
        mainViewModel?.bottomNavigationVisibility?.value = View.VISIBLE
    }

}