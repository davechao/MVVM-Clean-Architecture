package com.test.mvvm.view.base

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import com.kaopiz.kprogresshud.KProgressHUD

abstract class BaseDialogFragment : DialogFragment() {

    var progressHUD: KProgressHUD? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressHUD = KProgressHUD.create(context)
            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
    }

    override fun onStart() {
        super.onStart()
        if (dialog != null) {
            if (isFullLayout()) {
                dialog!!.window!!.setLayout(
                    WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.MATCH_PARENT
                )
                dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            } else {
                val widthPixels = (resources.displayMetrics.widthPixels * 0.8).toInt()
                dialog!!.window!!.setLayout(widthPixels, ViewGroup.LayoutParams.WRAP_CONTENT)
                dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }
        }
    }

    abstract fun isFullLayout(): Boolean

    abstract fun getLayoutId(): Int
}
