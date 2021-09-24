package com.permission.android.extension.annotations

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class AfterPermissionGranted(val value: Int)