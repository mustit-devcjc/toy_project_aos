package dev.chu.toyapp.etc.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.transition.MaterialArcMotion
import com.google.android.material.transition.MaterialContainerTransform
import com.google.android.material.transition.MaterialContainerTransformSharedElementCallback
import dev.chu.toyapp.R

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

/** get a material container arc transform. */
fun AppCompatActivity.getContentTransform(): MaterialContainerTransform {
    return MaterialContainerTransform().apply {
        addTarget(android.R.id.content)
        duration = 450
        pathMotion = MaterialArcMotion()
    }
}

/** apply material exit container transformation. */
fun AppCompatActivity.applyExitMaterialTransform() {
    window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
    setExitSharedElementCallback(MaterialContainerTransformSharedElementCallback())
    window.sharedElementsUseOverlay = false
}

/** apply material entered container transformation. */
fun AppCompatActivity.applyMaterialTransform(transitionName: String) {
    window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
    ViewCompat.setTransitionName(findViewById<View>(android.R.id.content), transitionName)

    // set up shared element transition
    setEnterSharedElementCallback(MaterialContainerTransformSharedElementCallback())
    window.sharedElementEnterTransition = getContentTransform()
    window.sharedElementReturnTransition = getContentTransform()
}

fun AppCompatActivity.simpleToolbarWithHome(toolbar: Toolbar, title_: String = "") {
    setSupportActionBar(toolbar)
    supportActionBar?.run {
        setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        setDisplayHomeAsUpEnabled(true)
        title = title_
    }
}

inline fun <reified T> Context.startActivity(bundle: Bundle) {
    startActivity(Intent(this, T::class.java).apply {
        for (key in bundle.keySet()) {
            when(val data = bundle.get(key)) {
                is String -> putExtra(key, data)
                is Int -> putExtra(key, data)
                is Bundle -> putExtra(key, data)
            }
        }
    })
}