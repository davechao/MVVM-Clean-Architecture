package com.test.mvvm.view.personal

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.test.mvvm.R
import com.test.mvvm.databinding.FragmentPersonalBinding
import com.test.mvvm.model.api.ApiResult.*
import com.test.mvvm.view.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_personal.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class PersonalFragment : BaseFragment<FragmentPersonalBinding>() {

    private val viewModel by viewModel<PersonalViewModel>()

    companion object {
        const val USER_NAME = "davechao"
    }

    override fun setViewModelToBinding(binding: FragmentPersonalBinding) {
        binding.viewModel = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.noContentData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Loading -> progressHUD?.show()
                is Loaded -> progressHUD?.dismiss()
                is Empty -> Timber.d("Empty: No Content")
                is Error -> Timber.e("Error: $it")
            }
        })

        viewModel.userDetailData.observe(viewLifecycleOwner, Observer {
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
                is Error -> Timber.e("Error: $it")
            }
        })

        viewModel.getUserDetail(USER_NAME)

//        viewModel.testNoContentApi()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_personal
    }

    override fun onResume() {
        super.onResume()
        mainViewModel?.bottomNavigationVisibility?.value = View.VISIBLE
    }
}
