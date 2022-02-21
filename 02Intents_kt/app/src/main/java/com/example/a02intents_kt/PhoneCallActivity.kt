package com.example.a02intents_kt

import android.Manifest
import android.content.Intent
import android.content.Intent.ACTION_CALL
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.a02intents_kt.databinding.ActivityPhoneCallBinding
import com.google.android.material.snackbar.Snackbar

const val CALL_PERMISSION_CODE = 121

class PhoneCallActivity : AppCompatActivity() {
    private lateinit var vb: ActivityPhoneCallBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityPhoneCallBinding.inflate(layoutInflater)
        val view = vb.root
        setContentView(view)

        vb.btnCall.setOnClickListener {
//            makePhoneCall()
            makeCallAfterPermission(it)
        }
    }

    private fun makePhoneCall() {
        val intent = Intent().apply {
            action = ACTION_CALL
            data = Uri.parse("tel: 9990")
        }
        startActivity(intent)
    }

    private fun makeCallAfterPermission(view: View) {
        // Check if Permission Already Granted
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.CALL_PHONE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            // Yes: makeCall
            makePhoneCall()
        } else {
            // NO: request for permission
            requestCallPermission(view)
        }
    }

    private fun requestCallPermission(view: View) {
//        ActivityCompat.requestPermissions(
//            this, arrayOf(Manifest.permission.CALL_PHONE),
//            CALL_PERMISSION_CODE
//        )
        // Explain why your app needs the permission
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.CALL_PHONE
            )
        ) {
            val snack = Snackbar.make(
                view, "We need you permission to make. " +
                        "When ask please give the permission", Snackbar.LENGTH_INDEFINITE
            )
            snack.setAction("OK", View.OnClickListener {
                ActivityCompat.requestPermissions(
                    this, arrayOf(Manifest.permission.CALL_PHONE),
                    CALL_PERMISSION_CODE
                )
            })
            snack.show()
        } else {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.CALL_PHONE),
                CALL_PERMISSION_CODE
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CALL_PERMISSION_CODE) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                makePhoneCall()
            } else {
                Toast.makeText(this, "Permission denied to make phone call", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}