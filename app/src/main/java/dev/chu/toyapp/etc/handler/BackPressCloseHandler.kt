package dev.chu.toyapp.etc.handler

import androidx.appcompat.app.AppCompatActivity
import dev.chu.toyapp.R
import dev.chu.toyapp.etc.extensions.showToast

class BackPressCloseHandler(private val activity: AppCompatActivity) {
    private var backKeyPressedTime = 0L

    fun onBackPressed() {
        if(System.currentTimeMillis() > backKeyPressedTime + 2000) {
            activity.showToast(R.string.back_pressed_message)
            backKeyPressedTime = System.currentTimeMillis()
            return
        }

        if(System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            activity.finish()
        }
    }
}