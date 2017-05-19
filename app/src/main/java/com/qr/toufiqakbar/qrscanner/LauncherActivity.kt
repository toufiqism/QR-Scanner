package com.qr.toufiqakbar.qrscanner

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import android.view.View
import android.widget.Button
import com.dm.zbar.android.scanner.ZBarConstants
import com.dm.zbar.android.scanner.ZBarScannerActivity

import java.lang.Exception
import android.Manifest.permission
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat

import android.support.v4.content.ContextCompat


class LauncherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)




        val btnScan = findViewById(R.id.btnScan) as Button

        btnScan.setOnClickListener {
            v: View? ->
            try {
                val captureQrIntent = Intent(this@LauncherActivity, ZBarScannerActivity::class.java)
                startActivityForResult(captureQrIntent, REQUEST_CODE_QR)
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        try {
            if (requestCode == REQUEST_CODE_QR && resultCode == Activity.RESULT_OK) {


                val qrResult = data?.getStringExtra(ZBarConstants.SCAN_RESULT)
                val intent = Intent(this@LauncherActivity, TextToSpeechActivity::class.java)
                intent.putExtra("TestResult", qrResult)
                startActivity(intent)
                finish()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    companion object {
        private val REQUEST_CODE_QR = 1001
        var TAG = "LAUNCHERACTIVITY"

    }
}


