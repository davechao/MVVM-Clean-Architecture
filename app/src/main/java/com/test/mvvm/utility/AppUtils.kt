package com.test.mvvm.utility

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.provider.Settings
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.test.mvvm.App
import com.test.mvvm.BuildConfig

object AppUtils {

    fun showToast(context: Context, text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    fun hideKeyboard(activity: FragmentActivity) {
        val view = activity.currentFocus
        if (view != null) {
            val inputManager =
                activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    fun showKeyboard(context: Context, editText: View) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(editText, InputMethodManager.SHOW_FORCED)
    }

    @SuppressLint("HardwareIds")
    fun getAndroidID(): String = Settings.Secure.getString(
        App.applicationContext().contentResolver,
        Settings.Secure.ANDROID_ID
    )

    fun getAppVersion(): String {
        return StringBuilder("v")
            .append(BuildConfig.VERSION_NAME)
            .toString()
    }

    fun getDeviceName(): String = Build.MODEL

}