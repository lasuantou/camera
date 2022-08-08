package com.cs.camera.camera2

import android.os.Bundle
import android.view.TextureView
import androidx.appcompat.app.AppCompatActivity
import android.view.WindowManager
import com.cs.camera.R
import kotlinx.android.synthetic.main.activity_camera.*
import kotlinx.android.synthetic.main.activity_camera2.*
import kotlinx.android.synthetic.main.activity_camera2.btnTakePic
import kotlinx.android.synthetic.main.activity_camera2.ivExchange

/**
 * author :  chensen
 * data  :  2018/3/18
 * desc :
 */
class CameraActivity2 : AppCompatActivity() {

    private lateinit var mCamera2Helper: Camera2Helper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera2)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

        mCamera2Helper = Camera2Helper(this, textureView)

        btnTakePic.setOnClickListener { mCamera2Helper.takePic() }
        ivExchange.setOnClickListener { mCamera2Helper.exchangeCamera() }
    }

    override fun onDestroy() {
        super.onDestroy()
        mCamera2Helper.releaseCamera()
        mCamera2Helper.releaseThread()
    }

    override fun onStop(){
        super.onStop()
        mCamera2Helper.stopCamera()
    }

    override fun onResume() {
        super.onResume()
        mCamera2Helper.resumeCamera()
    }
}

