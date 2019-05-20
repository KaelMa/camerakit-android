package com.camerakit.app

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.camerakit.CameraKitView

/**
 * Created by kaelma on 2019/5/20.
 */
class CameraFragment : Fragment() {

    lateinit var cameraView:CameraKitView

    companion object {
        @JvmStatic
        fun newInstance():CameraFragment {
            return CameraFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.camera_fragment, container, false)
        initCameraView(view)

        return view
    }

    override fun onStart() {
        super.onStart()
        cameraView.onStart()
    }

    override fun onResume() {
        super.onResume()
        cameraView.onResume()
    }

    override fun onPause() {
        super.onPause()
        cameraView.onPause()
    }

    override fun onStop() {
        super.onStop()
        cameraView.onStop()
    }

    private fun initCameraView(view: View) {
        cameraView = view.findViewById(R.id.camera)
        cameraView.setPermissionsListener(object : CameraKitView.PermissionsListener {
            override fun onPermissionsSuccess() {
                Log.v("CameraKitView", "onPermissionsSuccess")
            }

            override fun onPermissionsFailure() {
                Log.v("CameraKitView", "onPermissionsFailure")
            }
        })
        cameraView.cameraListener = object : CameraKitView.CameraListener {
            override fun onOpened() {
                Log.v("CameraKitView", "CameraListener: onOpened()")
            }

            override fun onClosed() {
                Log.v("CameraKitView", "CameraListener: onClosed()")
            }
        }
        cameraView.previewListener = object : CameraKitView.PreviewListener {
            override fun onStart() {
                Log.v("CameraKitView", "PreviewListener: onStart()")
            }

            override fun onStop() {
                Log.v("CameraKitView", "PreviewListener: onStop()")
            }
        }
        cameraView.errorListener = CameraKitView.ErrorListener { view, error ->
            Log.v("CameraKitView", "error: $error")
        }
    }

}