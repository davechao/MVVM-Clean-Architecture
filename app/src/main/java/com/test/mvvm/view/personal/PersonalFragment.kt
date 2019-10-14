package com.test.mvvm.view.personal

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.test.mvvm.R
import com.test.mvvm.databinding.FragmentPersonalBinding
import com.test.mvvm.view.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_personal.*
import org.koin.androidx.viewmodel.ext.android.viewModel

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

        viewModel.isLoading.observe(this, Observer {
            if (it) {
                progressHUD?.show()
            } else {
                progressHUD?.dismiss()
            }
        })

        viewModel.userDetailData.observe(this, Observer {
            Glide.with(requireContext())
                .load(it.avatarUrl)
                .placeholder(R.color.colorUserAvatarPlaceHolderColor)
                .into(binding.imageUserPic)

            text_login.text = it.login
            text_bio.text = it.bio
            binding.viewUserDetailInfo.setData(login = it.login, isSiteAdmin = it.isSiteAdmin!!)
            binding.viewUserDetailLoc.setData(it.location)
            binding.viewUserDetailBlog.setData(it.blog)
        })

        viewModel.getUserDetail(USER_NAME_DAVE)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_personal
    }

    override fun onResume() {
        super.onResume()
        mainViewModel?.bottomNavigationVisibility?.value = View.VISIBLE
    }

}