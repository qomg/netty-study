package com.example.it

import org.junit.Test

class VersionNumberTest {
    @Test
    fun formatVersionNumber() {
        val original = "10903005"
        val formatted = formatNumber(original)
        println("Original: $original")   // 输出: 10903005
        println("Formatted: $formatted") // 输出: 1.9.3(5)
        assert(formatted == "1.9.3(5)")
    }

    fun formatNumber(input: String): String {
        // 检查输入是否合法（纯数字且长度=8）
        require(input.matches(Regex("\\d{8}"))) { "Input must be 8-digit number" }

        return input.replace(Regex("(\\d)(\\d{2})(\\d{2})(\\d{3})")) { result ->
            val (major, minor, stage, build) = result.destructured
            "${major}.${minor.toInt()}.${stage.toInt()}(${build.toInt()})"
        }
    }
}