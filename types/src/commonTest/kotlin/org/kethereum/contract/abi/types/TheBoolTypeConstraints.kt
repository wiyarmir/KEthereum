package org.kethereum.contract.abi.types

import org.kethereum.contract.abi.types.model.types.BoolETHType
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class TheBoolTypeConstraints {

    @Test
    fun ofStringWorksForGoodInput() {
        assertEquals(BoolETHType.ofString("true").toKotlinType(), true)
        assertEquals(BoolETHType.ofString("TRUE").toKotlinType(), true)
        assertEquals(BoolETHType.ofString("True").toKotlinType(), true)

        assertEquals(BoolETHType.ofString("false").toKotlinType(), false)
        assertEquals(BoolETHType.ofString("FALSE").toKotlinType(), false)
        assertEquals(BoolETHType.ofString("False").toKotlinType(), false)
    }

    @Test
    fun ofStringFailsForInvalid() {
        assertFailsWith<IllegalArgumentException> {
            BoolETHType.ofString("true ")
        }
        assertFailsWith<IllegalArgumentException> {
            BoolETHType.ofString("")
        }
    }
}
