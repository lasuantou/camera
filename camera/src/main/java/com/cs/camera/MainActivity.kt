package com.cs.camera

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cs.camera.camera1.CameraActivity
import com.cs.camera.camera2.CameraActivity2
import com.cs.camera.camera2.CameraActivity2Face
import com.cs.camera.util.PermissionUtils
import com.cs.camera.util.PermissionUtils.PERMISSION_REQUEST_CODE
import com.cs.camera.util.PermissionUtils.PERMISSION_SETTING_CODE
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

//    private val permissionsList = arrayOf(Manifest.permission.READ_PHONE_STATE,
//            Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO)

    private val permissionsList = arrayOf(Manifest.permission.RECORD_AUDIO, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btCapture.setOnClickListener {
            PermissionUtils.checkPermission(this, permissionsList, Runnable {
                startActivity(Intent(this, CaptureActivity::class.java))
            })
        }
        btCamera.setOnClickListener {
            PermissionUtils.checkPermission(this, permissionsList, Runnable {
                val intent = Intent(this, CameraActivity::class.java)
                intent.putExtra(CameraActivity.TYPE_TAG, CameraActivity.TYPE_CAPTURE)
                startActivity(intent)
            })
        }
        btCameraRecord.setOnClickListener {
            PermissionUtils.checkPermission(this, permissionsList, Runnable {
                val intent = Intent(this, CameraActivity::class.java)
                intent.putExtra(CameraActivity.TYPE_TAG, CameraActivity.TYPE_RECORD)
                startActivity(intent)
            })
        }

        btCamera2.setOnClickListener {
            PermissionUtils.checkPermission(this, permissionsList, Runnable {
                val intent = Intent(this, CameraActivity2::class.java)
                startActivity(intent)
            })
        }

        btCamera2Face.setOnClickListener {
            PermissionUtils.checkPermission(this, permissionsList, Runnable {
                startActivity(Intent(this, CameraActivity2Face::class.java))
            })
        }

        PermissionUtils.checkPermission(this, permissionsList, Runnable {
        })
    }


    /**
     * ???????????????????????????????????????
     */
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        log("onRequestPermissionsResult ")

        when (requestCode) {
            PERMISSION_REQUEST_CODE -> {
                var allGranted = true

                grantResults.forEach {
                    if (it != PackageManager.PERMISSION_GRANTED) {
                        allGranted = false
                    }
                }

                if (allGranted) {  //?????????????????????
                    log("onRequestPermissionsResult ?????????????????????")
                } else {
                    log("????????????????????????,??????????????????????????????????????????????????????setting ")

                    // ????????????????????????,?????????????????????????????????????????????
                    PermissionUtils.showPermissionSettingDialog(this)
                }
            }
        }
    }


    /**
     * ??????????????????????????????????????????????????????
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            PERMISSION_SETTING_CODE -> { //??????????????????????????????????????????????????????????????????
                log("???????????????????????????????????????????????????")
                PermissionUtils.checkPermission(this, permissionsList, Runnable {
                    val intent = Intent(this, CameraActivity2::class.java)
                    startActivity(intent)
                })
            }

        }
    }
}

