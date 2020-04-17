package dev.chu.toyapp.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<T: ViewDataBinding>: AppCompatActivity() {

    @LayoutRes
    abstract fun getLayoutRes() : Int
    abstract fun initView(savedInstanceState: Bundle?)

    protected lateinit var binding: T
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(getLayoutRes())
        binding = DataBindingUtil.setContentView(this, getLayoutRes())
        binding.lifecycleOwner = this

        title = ""

        initView(savedInstanceState)
    }
}