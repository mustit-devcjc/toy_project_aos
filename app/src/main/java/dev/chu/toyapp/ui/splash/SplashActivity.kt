package dev.chu.toyapp.ui.splash

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import androidx.annotation.LayoutRes
import androidx.core.app.ActivityCompat
import dev.chu.toyapp.GlobalApplication
import dev.chu.toyapp.R
import dev.chu.toyapp.base.BaseActivity
import dev.chu.toyapp.ui.main.MainActivity
import java.io.File

class SplashActivity : BaseActivity() {
    @LayoutRes
    override fun getLayoutRes(): Int = R.layout.activity_splash

    // region file make
    //permission is automatically granted on sdk<23 upon installation
    private val isStoragePermissionGranted: Boolean
        get() = if (Build.VERSION.SDK_INT >= 23) {
            if (GlobalApplication.getInstance()
                    .checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
            ) {
                true
            } else {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
                false
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            true
        }

    private fun createFolder() {
        if (isStoragePermissionGranted) {
            val folder = File(
                Environment.getExternalStorageDirectory()
                    .toString() + File.separator + "Something"
            )
            if (!folder.exists()) {
                folder.mkdir()
            }
        }
    }
    // endregion

    override fun initView(savedInstanceState: Bundle?) {
        createFolder()

        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 1000)
    }
}