package dev.chu.toyapp.etc.extensions

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

val Any.TAG: String
    get() = this::class.simpleName ?: this.toString()

inline fun <reified T : ViewDataBinding> bindings(
    view: View
): Lazy<T> = lazy { requireNotNull(DataBindingUtil.bind<T>(view)) { "cannot find the matched view to layout." } }