package com.hyk.timeCalculate

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<T: ViewDataBinding> : AppCompatActivity() {

    abstract val layoutId: Int
    protected lateinit var binding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBind(layoutId)
    }

    private fun setupBind(layoutId: Int) {
        binding = DataBindingUtil.setContentView(this, layoutId)
    }
}