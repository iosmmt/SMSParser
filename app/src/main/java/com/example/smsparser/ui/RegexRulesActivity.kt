package com.example.smsparser.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.smsparser.databinding.ActivityRegexRulesBinding
import java.util.regex.Pattern

class RegexRulesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegexRulesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegexRulesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupClickListeners()
    }

    private fun setupClickListeners() {
        // 返回按钮
        binding.btnBack.setOnClickListener {
            finish()
        }

        // 测试正则按钮
        binding.btnTestRegex.setOnClickListener {
            testRegexPattern()
        }

        // 保存规则按钮
        binding.btnSaveRegex.setOnClickListener {
            saveRegexRule()
        }
    }

    private fun testRegexPattern() {
        val pattern = binding.etRegexPattern.text.toString()
        val testText = binding.etTestText.text.toString()

        if (pattern.isEmpty()) {
            Toast.makeText(this, "请输入正则表达式", Toast.LENGTH_SHORT).show()
            return
        }

        if (testText.isEmpty()) {
            Toast.makeText(this, "请输入测试文本", Toast.LENGTH_SHORT).show()
            return
        }

        try {
            val regex = Pattern.compile(pattern)
            val matcher = regex.matcher(testText)
            
            val result = StringBuilder()
            var matchCount = 0
            
            while (matcher.find()) {
                matchCount++
                result.append("匹配 $matchCount: \"${matcher.group()}\"")
                if (matcher.groupCount() > 0) {
                    result.append(" (组: ")
                    for (i in 1..matcher.groupCount()) {
                        result.append("\"${matcher.group(i)}\"")
                        if (i < matcher.groupCount()) {
                            result.append(", ")
                        }
                    }
                    result.append(")")
                }
                result.append("\n")
            }

            if (matchCount == 0) {
                binding.tvTestResult.text = "未找到匹配项"
            } else {
                binding.tvTestResult.text = "找到 $matchCount 个匹配项：\n$result"
            }

        } catch (e: Exception) {
            binding.tvTestResult.text = "正则表达式错误：${e.message}"
        }
    }

    private fun saveRegexRule() {
        val pattern = binding.etRegexPattern.text.toString()
        
        if (pattern.isEmpty()) {
            Toast.makeText(this, "请输入正则表达式", Toast.LENGTH_SHORT).show()
            return
        }

        try {
            // 验证正则表达式是否有效
            Pattern.compile(pattern)
            
            // 这里可以保存到SharedPreferences或数据库
            Toast.makeText(this, "正则规则已保存", Toast.LENGTH_SHORT).show()
            finish()
            
        } catch (e: Exception) {
            Toast.makeText(this, "正则表达式无效：${e.message}", Toast.LENGTH_SHORT).show()
        }
    }
} 