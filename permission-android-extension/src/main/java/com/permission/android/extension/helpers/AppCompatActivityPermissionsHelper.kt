package com.permission.android.extension.helpers

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.permission.android.extension.dialogs.RationaleDialog
import com.permission.android.extension.helpers.base.PermissionsHelper
import com.permission.android.extension.models.PermissionRequest

/**
 * Permissions helper for [AppCompatActivity].
 */
internal class AppCompatActivityPermissionsHelper(
    host: AppCompatActivity
) : PermissionsHelper<AppCompatActivity>(host) {

    override var context: Context? = host

    override fun directRequestPermissions(requestCode: Int, permissions: Array<out String>) {
        ActivityCompat.requestPermissions(host, permissions, requestCode)
    }

    override fun shouldShowRequestPermissionRationale(permission: String): Boolean {
        return ActivityCompat.shouldShowRequestPermissionRationale(host, permission)
    }

    override fun showRequestPermissionRationale(permissionRequest: PermissionRequest) {
        RationaleDialog(host, permissionRequest).showCompatDialog()
    }
}