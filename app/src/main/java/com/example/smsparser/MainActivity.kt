package com.example.smsparser

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.smsparser.databinding.ActivityMainBinding
import com.example.smsparser.ui.CustomRulesActivity
import com.example.smsparser.ui.RegexRulesActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // 权限申请回调
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val allGranted = permissions.entries.all { it.value }
        if (allGranted) {
            Toast.makeText(this, "短信权限已授予", Toast.LENGTH_SHORT).show()
        } else {
            showPermissionDeniedDialog()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupClickListeners()
    }

    private fun setupClickListeners() {
        // 申请短信权限
        binding.btnRequestPermission.setOnClickListener {
            requestSmsPermission()
        }

        // 正则规则设置
        binding.btnRegexRules.setOnClickListener {
            startActivity(Intent(this, RegexRulesActivity::class.java))
        }

        // 自定义规则设置
        binding.btnCustomRules.setOnClickListener {
            startActivity(Intent(this, CustomRulesActivity::class.java))
        }
    }

    private fun requestSmsPermission() {
        val permissions = arrayOf(
            Manifest.permission.RECEIVE_SMS,
            Manifest.permission.READ_SMS,
            Manifest.permission.SEND_SMS
        )

        // 检查是否已经有权限
        val hasPermissions = permissions.all {
            ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED
        }

        if (hasPermissions) {
            Toast.makeText(this, "短信权限已授予", Toast.LENGTH_SHORT).show()
        } else {
            // 检查是否需要显示权限说明
            val shouldShowRationale = permissions.any {
                shouldShowRequestPermissionRationale(it)
            }

            if (shouldShowRationale) {
                showPermissionExplanationDialog()
            } else {
                requestPermissionLauncher.launch(permissions)
            }
        }
    }

    private fun showPermissionExplanationDialog() {
        AlertDialog.Builder(this)
            .setTitle("需要短信权限")
            .setMessage("为了解析短信内容，应用需要访问您的短信权限。请在设置中手动开启权限。")
            .setPositiveButton("确定") { _, _ ->
                requestPermissionLauncher.launch(
                    arrayOf(
                        Manifest.permission.RECEIVE_SMS,
                        Manifest.permission.READ_SMS,
                        Manifest.permission.SEND_SMS
                    )
                )
            }
            .setNegativeButton("取消", null)
            .show()
    }

    private fun showPermissionDeniedDialog() {
        AlertDialog.Builder(this)
            .setTitle("权限被拒绝")
            .setMessage("短信权限被拒绝，应用无法正常工作。请在设置中手动开启权限。")
            .setPositiveButton("打开设置") { _, _ ->
                openAppSettings()
            }
            .setNegativeButton("取消", null)
            .show()
    }

    private fun openAppSettings() {
        try {
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                data = Uri.fromParts("package", packageName, null)
            }
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(this, "无法打开设置页面", Toast.LENGTH_SHORT).show()
        }
    }
} 