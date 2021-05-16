package org.hydev.experiment

import java.io.File
import kotlin.math.pow

/**
 * TODO: Write a description for this class!
 *
 * @author HyDEV Team (https://github.com/HyDevelop)
 * @author Hykilpikonna (https://github.com/hykilpikonna)
 * @author Vanilla (https://github.com/VergeDX)
 * @since 2020-10-31 21:30
 */
fun main(args: Array<String>)
{

    val str = File("./bills.csv").readText().replace("\r\n", "\n")

    var total = 0
    str.split("\n").forEachIndexed { i, line ->
        if (line.isEmpty()) return@forEachIndexed

        try
        {
            val chinese = line.split(",")[0]
            val count = line.split(",")[1].toInt()
            total += chineseToNum(chinese) * count
        }
        catch (_: NumberFormatException) { }
    }

    println(total)
}

val chineseNumbers = listOf('零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖')
val scientific = mapOf('拾' to 1, '佰' to 2, '仟' to 3, '万' to 4, '亿' to 8, '兆' to 12)
val units = mapOf('元' to 100, '角' to 10, '分' to 1)

fun chineseToNum(chinese: String): Int
{
    var chinese = chinese
    chineseNumbers.forEachIndexed { n, c -> chinese = chinese.replace(c.toString(), n.toString()) }

    var amount = 0
    var pendingAmount = 0
    var digitAmount = 0
    for (c in chinese) // 每个字符
    {
        // 是数字
        if (c.isDigit())
        {
            // 处理中价格加上这个数
            digitAmount += c.toString().toInt();
        }

        // 是个十百千万
        else if (scientific.containsKey(c))
        {
            // 特殊情况: 十在前面不是单位而是 1x
            if (c == '拾' && chinese.startsWith("拾")) pendingAmount = 10

            // 处理中价格乘上 10^x
            else
            {
                pendingAmount += digitAmount * 10.0.pow(scientific[c]!!).toInt()
                digitAmount = 0
            }
        }

        // 是单位
        else if (units.containsKey(c))
        {
            // 结束处理, 添加, 重置
            amount += pendingAmount * units[c]!! + digitAmount * units[c]!!
            pendingAmount = 0
            digitAmount = 0
        }

        else
        {
            println("Unknown: $c")
        }
    }

    return amount
}
