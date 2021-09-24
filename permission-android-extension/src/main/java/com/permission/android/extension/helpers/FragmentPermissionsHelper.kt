package com.permission.android.extension.helpers

import android.content.Context
import androidx.fragment.app.Fragment
import com.permission.android.extension.dialogs.RationaleDialog
import com.permission.android.extension.helpers.base.PermissionsHelper
import com.permission.android.extension.models.PermissionRequest

/**
 * Permissions helper for [Fragment].
 */
internal class FragmentPermissionsHelper(
    host: Fragment
) : PermissionsHelper<Fragment>(host) {

    override var context: Context? = host.activity

    override fun directRequestPermissions(requestCode: Int, permissions: Array<out String>) {
        host.requestPermissions(permissions, requestCode)
    }

    override fun shouldShowRequestPermissionRationale(permission: String): Boolean {
        return host.shouldShowRequestPermissionRationale(permission)
    }

    override fun showRequestPermissionRationale(permissionRequest: PermissionRequest) {
        context?.let {
            RationaleDialog(it, permissionRequest).showCompatDialog()
        }
    }
}