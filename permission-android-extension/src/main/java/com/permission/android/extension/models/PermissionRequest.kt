package com.permission.android.extension.models

import android.content.Context
import androidx.annotation.StringRes
import androidx.annotation.StyleRes
import com.permission.android.extension.R

/**
 * An immutable model object that holds all of the parameters associated with a permission request,
 * such as the permissions, request code, and rationale.
 *
 * @see PermissionRequest.Builder
 */
data class PermissionRequest(
    @StyleRes
    var theme: Int,
    var requestCode: Int,
    var permissions: Array<out String>,
    var rationale: String?,
    var positiveButtonText: String?,
    var negativeButtonText: String?
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PermissionRequest

        if (theme != other.theme) return false
        if (requestCode != other.requestCode) return false
        if (!permissions.contentEquals(other.permissions)) return false
        if (rationale != other.rationale) return false
        if (positiveButtonText != other.positiveButtonText) return false
        if (negativeButtonText != other.negativeButtonText) return false

        return true
    }

    override fun hashCode(): Int {
        var result = theme
        result = 31 * result + requestCode
        result = 31 * result + permissions.contentHashCode()
        result = 31 * result + (rationale?.hashCode() ?: 0)
        result = 31 * result + (positiveButtonText?.hashCode() ?: 0)
        result = 31 * result + (negativeButtonText?.hashCode() ?: 0)
        return result
    }

    /**
     * Builder to build a permission request with variable options.
     *
     * @see PermissionRequest
     */
    @Suppress("UNUSED")
    class Builder(var context: Context?) {
        @StyleRes
        private var theme = 0
        private var requestCode = 0
        private var permissions: Array<out String> = emptyArray()
        private var rationale = context?.getString(R.string.rationale_ask)
        private var positiveButtonText = context?.getString(android.R.string.ok)
        private var negativeButtonText = context?.getString(android.R.string.cancel)

        fun theme(@StyleRes theme: Int) = apply { this.theme = theme }
        fun requestCode(requestCode: Int) = apply { this.requestCode = requestCode }
        fun permissions(permissions: Array<out String>) = apply { this.permissions = permissions }
        fun rationale(rationale: String) = apply { this.rationale = rationale }
        fun rationale(@StringRes resId: Int) = apply { this.rationale = context?.getString(resId) }
        fun positiveButtonText(positiveButtonText: String) =
            apply { this.positiveButtonText = positiveButtonText }

        fun positiveButtonText(@StringRes resId: Int) =
            apply { this.positiveButtonText = context?.getString(resId) }

        fun negativeButtonText(negativeButtonText: String) =
            apply { this.negativeButtonText = negativeButtonText }

        fun negativeButtonText(@StringRes resId: Int) =
            apply { this.negativeButtonText = context?.getString(resId) }

        fun build(): PermissionRequest {
            return PermissionRequest(
                theme = theme,
                requestCode = requestCode,
                permissions = permissions,
                rationale = rationale,
                positiveButtonText = positiveButtonText,
                negativeButtonText = negativeButtonText
            )
        }
    }
}