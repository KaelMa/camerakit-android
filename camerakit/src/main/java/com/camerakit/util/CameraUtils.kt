package com.camerakit.util

import android.content.Context
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraManager
import android.os.Build

/**
 * is camera2 supported
 * @param context
 */
fun hasCamera2(context: Context?): Boolean {
    if (context == null) return false
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) return false
    try {
        val manager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager
        val idList = manager.cameraIdList
        var notFull = true
        if (idList.size == 0) {
            notFull = false
        } else {
            for (str in idList) {
                if (str == null || str.trim { it <= ' ' }.isEmpty()) {
                    notFull = false
                    break
                }
                val characteristics = manager.getCameraCharacteristics(str)

                val supportLevel = characteristics.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL)!!
                if (supportLevel == CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL_LEGACY) {
                    notFull = false
                    break
                }
            }
        }
        return notFull
    } catch (ignore: Throwable) {
        return false
    }

}

