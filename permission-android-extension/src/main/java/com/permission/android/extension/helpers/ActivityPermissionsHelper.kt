package com.permission.android.extension.helpers

import android.app.Activity
import android.content.Context
import androidx.core.app.ActivityCompat
import com.permission.android.extension.dialogs.RationaleDialog
import com.permission.android.extension.helpers.base.PermissionsHelper
import com.permission.android.extension.models.PermissionRequest

/**
 * Permissions helper for [Activity].
 */
internal class ActivityPermissionsHelper(
    host: Activity
) : PermissionsHelper<Activity>(host) {

    override var context: Context? = host

    override fun directRequestPermissions(requestCode: Int, permissions: Array<out String>) {
        ActivityCompat.requestPermissions(host, permissions, requestCode)
    }

    override fun shouldShowRequestPermissionRationale(permission: String): Boolean {
        return ActivityCompat.shouldShowRequestPermissionRationale(host, permission)
    }

    override fun showRequestPermissionRationale(permissionRequest: PermissionRequest) {
        RationaleDialog(host, permissionRequest).showDialog()
    }
}