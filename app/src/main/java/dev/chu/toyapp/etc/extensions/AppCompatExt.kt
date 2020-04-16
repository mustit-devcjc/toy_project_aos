package dev.chu.toyapp.etc.extensions

import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

fun AppCompatActivity.setActionBarHome(toolbar: Toolbar, @DrawableRes res: Int? = null) {
    setSupportActionBar(toolbar)
    supportActionBar?.apply {
        res?.let {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
            setHomeAsUpIndicator(it)
        } ?: run {
            setDisplayHomeAsUpEnabled(false)
            setHomeButtonEnabled(false)
            setHomeAsUpIndicator(null)
        }
    }
}

fun AppCompatActivity.hideActionBar() {
    supportActionBar?.hide()
}

fun AppCompatActivity.showToast(res: Int) =
    Toast.makeText(this, res, Toast.LENGTH_SHORT).show()

fun AppCompatActivity.showToast(message: String) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()