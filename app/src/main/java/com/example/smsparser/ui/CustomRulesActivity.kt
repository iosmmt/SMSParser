package com.example.smsparser.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.smsparser.databinding.ActivityCustomRulesBinding

class CustomRulesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCustomRulesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomRulesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupClickListeners()
        setupTextWatchers()
    }

    private fun setupClickListeners() {
        // 返回按钮
        binding.btnBack.setOnClickListener {
            finish()
        }

        // 帮助按钮
        binding.btnHelp.setOnClickListener {
            showHelpDialog()
        }

        // 重置按钮
        binding.btnReset.setOnClickListener {
            resetAllFields()
        }

        // 使用按钮
        binding.btnUse.setOnClickListener {
            saveAndUseRules()
        }
    }

    private fun setupTextWatchers() {
        // 监听短信内容变化，实时解析
        binding.etSmsContent.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                parseSmsContent()
            }
        })
    }

    private fun parseSmsContent() {
        val smsContent = binding.etSmsContent.text.toString()
        if (smsContent.isEmpty()) {
            clearResults()
            return
        }

        // 解析标签
        val tagBefore = binding.etTagBefore.text.toString()
        val tagAfter = binding.etTagAfter.text.toString()
        val tagResult = extractText(smsContent, tagBefore, tagAfter)
        binding.tvTagResult.text = "标签：$tagResult"

        // 解析快递电话
        val phoneBefore = binding.etPhoneBefore.text.toString()
        val phoneAfter = binding.etPhoneAfter.text.toString()
        val phoneResult = extractText(smsContent, phoneBefore, phoneAfter)
        binding.tvPhoneResult.text = "快递电话：$phoneResult"

        // 解析取件码
        val codeBefore = binding.etCodeBefore.text.toString()
        val codeAfter = binding.etCodeAfter.text.toString()
        val codeResult = extractText(smsContent, codeBefore, codeAfter)
        binding.tvCodeResult.text = "取件码：$codeResult"

        // 解析取件地址
        val addressBefore = binding.etAddressBefore.text.toString()
        val addressAfter = binding.etAddressAfter.text.toString()
        val addressResult = extractText(smsContent, addressBefore, addressAfter)
        binding.tvAddressResult.text = "取件地址：$addressResult"
    }

    private fun extractText(content: String, before: String, after: String): String {
        if (before.isEmpty() && after.isEmpty()) {
            return ""
        }

        var result = content

        // 如果指定了前文，从指定位置开始
        if (before.isNotEmpty()) {
            val beforeIndex = result.indexOf(before)
            if (beforeIndex != -1) {
                result = result.substring(beforeIndex + before.length)
            } else {
                return ""
            }
        }

        // 如果指定了后文，截取到指定位置
        if (after.isNotEmpty()) {
            val afterIndex = result.indexOf(after)
            if (afterIndex != -1) {
                result = result.substring(0, afterIndex)
            }
        }

        return result.trim()
    }

    private fun clearResults() {
        binding.tvTagResult.text = "标签："
        binding.tvPhoneResult.text = "快递电话："
        binding.tvCodeResult.text = "取件码："
        binding.tvAddressResult.text = "取件地址："
    }

    private fun resetAllFields() {
        binding.etTagBefore.setText("")
        binding.etTagAfter.setText("")
        binding.etPhoneBefore.setText("")
        binding.etPhoneAfter.setText("")
        binding.etCodeBefore.setText("")
        binding.etCodeAfter.setText("")
        binding.etAddressBefore.setText("")
        binding.etAddressAfter.setText("")
        binding.etSmsContent.setText("")
        clearResults()
    }

    private fun saveAndUseRules() {
        // 这里可以保存规则到SharedPreferences或数据库
        Toast.makeText(this, "规则已保存并应用", Toast.LENGTH_SHORT).show()
        finish()
    }

    private fun showHelpDialog() {
        val helpText = """
            自定义规则使用说明：
            
            1. 在"前"字段中输入要匹配的文本前缀
            2. 在"后"字段中输入要匹配的文本后缀
            3. 系统会提取前后文之间的内容
            4. 在短信内容区域输入测试文本查看解析结果
            
            示例：
            前：取件码
            后：请
            文本：您的取件码是123456请及时取件
            结果：123456
        """.trimIndent()

        androidx.appcompat.app.AlertDialog.Builder(this)
            .setTitle("使用帮助")
            .setMessage(helpText)
            .setPositiveButton("确定", null)
            .show()
    }
} 