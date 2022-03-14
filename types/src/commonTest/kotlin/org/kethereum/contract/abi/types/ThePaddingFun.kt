package org.kethereum.contract.abi.types

import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class ThePaddingFun {

    @Test
    fun paddingWorks() {
        assertContentEquals(byteArrayOf(2).leftPadToFixedSize(3), byteArrayOf(0, 0, 2))
    }

    @Test
    fun noChangeWhenNotNeeded() {
        assertContentEquals(byteArrayOf(4, 2).leftPadToFixedSize(2), byteArrayOf(4, 2))
    }

    @Test
    fun emptyWorks() {
        assertContentEquals(ByteArray(0), byteArrayOf())
    }

    @Test
    fun failsOnTooLargeInput() {
        assertFailsWith(IllegalArgumentException::class) {
            byteArrayOf(2, 3).leftPadToFixedSize(1)
        }
    }
}

