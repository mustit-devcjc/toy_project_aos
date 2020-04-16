package dev.chu.toyapp.etc.extensions

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import dev.chu.toyapp.etc.listener.OnBackPressedListener

fun FragmentActivity.replaceFragment(@IdRes res: Int, fragment: Fragment, tag: String? = null) {
    val fragmentTransaction: FragmentTransaction = this.supportFragmentManager.beginTransaction()
    if (tag != null) {
        fragmentTransaction.replace(res, fragment, tag)
    } else {
        fragmentTransaction.replace(res, fragment)
    }
    fragmentTransaction.addToBackStack(null)
//    fragmentTransaction.commit()
    fragmentTransaction.commitAllowingStateLoss()
}

fun FragmentActivity.onBackPressedFragment() {
    val fragmentList = supportFragmentManager.fragments
    for (fragment in fragmentList) {
        if (fragment is OnBackPressedListener) {
            (fragment as OnBackPressedListener).onBackPressed()
        }
    }
}