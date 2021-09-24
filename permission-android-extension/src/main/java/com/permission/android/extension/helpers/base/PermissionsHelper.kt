package com.permission.android.extension.helpers.base

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.permission.android.extension.helpers.ActivityPermissionsHelper
import com.permission.android.extension.helpers.AppCompatActivityPermissionsHelper
import com.permission.android.extension.helpers.FragmentPermissionsHelper
import com.permission.android.extension.models.PermissionRequest

/**
 * Delegate class to make permission calls based on the 'host' (Fragment, Activity, etc).
 */
abstract class PermissionsHelper<T>(val host: T) {

    companion object {

        fun newInstance(host: Activity): PermissionsHelper<out Activity> {
            return ((host as? AppCompatActivity)?.let {
                AppCompatActivityPermissionsHelper(it)
            } ?: ActivityPermissionsHelper(host))
        }

        fun newInstance(host: Fragment): PermissionsHelper<Fragment> {
            return FragmentPermissionsHelper(host)
        }
    }

    // ============================================================================================
    // Public abstract methods
    // ============================================================================================

    abstract var context: Context?

    abstract fun directRequestPermissions(requestCode: Int, perms: Array<out String>)

    abstract fun shouldShowRequestPermissionRationale(perm: String): Boolean

    abstract fun showRequestPermissionRationale(permissionRequest: PermissionRequest)

    // ============================================================================================
    //  Public methods
    // ============================================================================================

    fun requestPermissions(permissionRequest: PermissionRequest) {
        if (shouldShowRationale(permissionRequest.permissions)) {
            showRequestPermissionRationale(permissionRequest)
        } else {
            directRequestPermissions(permissionRequest.requestCode, permissionRequest.permissions)
        }
    }

    fun somePermissionPermanentlyDenied(permissions: List<String>): Boolean {
        return permissions.any { permissionPermanentlyDenied(it) }
    }

    fun permissionPermanentlyDenied(permission: String): Boolean {
        return !shouldShowRequestPermissionRationale(permission)
    }

    fun somePermissionDenied(permissions: Array<out String>): Boolean {
        return shouldShowRationale(permissions)
    }

    // ============================================================================================
    //  Private methods
    // ============================================================================================

    private fun shouldShowRationale(permissions: Array<out String>): Boolean {
        return permissions.any { shouldShowRequestPermissionRationale(it) }
    }
}