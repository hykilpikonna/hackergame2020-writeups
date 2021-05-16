package org.hydev.experiment

import org.junit.Test

/**
 * TODO: Write a description for this class!
 *
 * @author HyDEV Team (https://github.com/HyDevelop)
 * @author Hykilpikonna (https://github.com/hykilpikonna)
 * @author Vanilla (https://github.com/VergeDX)
 * @since 2020-10-31 22:15
 */
class CtfBillsTests
{
    @Test
    fun test715()
    {
        test("柒元壹角伍分", 715)
    }

    @Test
    fun test1046()
    {
        test("拾元肆角陆分", 1046)
    }

    @Test
    fun test15573()
    {
        test("壹佰伍拾柒元柒角叁分", 15773)
    }

    fun test(s: String, i: Int)
    {
        print(chineseToNum(s))
        assert(chineseToNum(s) == i)
    }
}
