package org.kethereum.contract.abi.types

import com.ionspin.kotlin.bignum.integer.BigInteger
import com.ionspin.kotlin.bignum.integer.BigInteger.Companion.ONE
import com.ionspin.kotlin.bignum.integer.BigInteger.Companion.ZERO
import com.ionspin.kotlin.bignum.integer.toBigInteger
import org.kethereum.contract.abi.types.model.type_params.BitsTypeParams
import org.kethereum.contract.abi.types.model.type_params.BytesTypeParams
import org.kethereum.contract.abi.types.model.types.*
import org.kethereum.crypto.test_data.TEST_ADDRESSES
import org.kethereum.crypto.test_data.TEST_BIGINTEGERS_INCL_NEGATIVE
import org.kethereum.crypto.test_data.TEST_BIGINTEGERS_POSITIVE_ONLY
import org.komputing.khex.extensions.hexToByteArray
import org.komputing.khex.model.HexString
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class TheDecoder {

    @Test
    fun testExample1() {
        //https://solidity.readthedocs.io/en/v0.5.12/abi-spec.html#examples
        val paginated =
            PaginatedByteArray(HexString("00000000000000000000000000000000000000000000000000000000000000450000000000000000000000000000000000000000000000000000000000000001").hexToByteArray())

        assertEquals(UIntETHType.ofPaginatedByteArray(paginated, BitsTypeParams(32))?.toKotlinType(), 69.toBigInteger())
        assertEquals(BoolETHType.ofPaginatedByteArray(paginated)?.toKotlinType(), true)
    }

    @Test
    fun testExample2() {
        //https://solidity.readthedocs.io/en/v0.5.12/abi-spec.html#examples
        val paginated =
            PaginatedByteArray(HexString("61626300000000000000000000000000000000000000000000000000000000006465660000000000000000000000000000000000000000000000000000000000").hexToByteArray())

        assertContentEquals(BytesETHType.ofPaginatedByteArray(paginated, BytesTypeParams(3))?.toKotlinType(),
            "abc".encodeToByteArray())
        assertContentEquals(BytesETHType.ofPaginatedByteArray(paginated, BytesTypeParams(3))?.toKotlinType(),
            "def".encodeToByteArray())
    }

    @Test
    fun testExample3() {
        //https://solidity.readthedocs.io/en/v0.5.12/abi-spec.html#examples
        val paginated =
            PaginatedByteArray(HexString("0000000000000000000000000000000000000000000000000000000000000060000000000000000000000000000000000000000000000000000000000000000100000000000000000000000000000000000000000000000000000000000000a0000000000000000000000000000000000000000000000000000000000000000464617665000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000003000000000000000000000000000000000000000000000000000000000000000100000000000000000000000000000000000000000000000000000000000000020000000000000000000000000000000000000000000000000000000000000003").hexToByteArray())

        assertContentEquals(DynamicSizedBytesETHType.ofPaginatedByteArray(paginated)?.toKotlinType(),
            "dave".encodeToByteArray())
        assertEquals(BoolETHType.ofPaginatedByteArray(paginated)?.toKotlinType(), true)
    }

    @Test
    fun thatStringRoundTripWorks() {
        assertEquals(StringETHType.ofString("probe").toKotlinType(), "probe")
    }

    @Test
    fun thatStringRoundTripWorksForReallyLongString() {
        assertEquals(StringETHType.ofString("probe0000000000000000000000000000000000000000000000000000000000000060000000000000000000000000000000000000000000000000000000000000000100000000000000000000000000000000000000000000000000000000000000a0000000000000000000000000000000000000000000000000000000000000000464617665000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000003000000000000000000000000000000000000000000000000000000000000000100000000000000000000000000000000000000000000000000000000000000020000000000000000000000000000000000000000000000000000000000000003")
            .toKotlinType(),
            "probe0000000000000000000000000000000000000000000000000000000000000060000000000000000000000000000000000000000000000000000000000000000100000000000000000000000000000000000000000000000000000000000000a0000000000000000000000000000000000000000000000000000000000000000464617665000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000003000000000000000000000000000000000000000000000000000000000000000100000000000000000000000000000000000000000000000000000000000000020000000000000000000000000000000000000000000000000000000000000003")
    }

    @Test
    fun thatUIntRoundTripWorks() {
        TEST_BIGINTEGERS_POSITIVE_ONLY.forEach {
            assertEquals(UIntETHType.ofNativeKotlinType(it, BitsTypeParams(256)).toKotlinType(), it, "for integer $it")
        }
    }

    @Test
    fun thatIntRoundTripWorks() {
        TEST_BIGINTEGERS_INCL_NEGATIVE.forEach {
            assertEquals(IntETHType.ofNativeKotlinType(it, BitsTypeParams(256)).toKotlinType(), it, "for integer $it")
        }
    }

    @Test
    fun thatAddressRoundTripWorks() {
        TEST_ADDRESSES.forEach {
            assertEquals(AddressETHType.ofNativeKotlinType(it).toKotlinType(), it)
        }
    }

    @Test
    fun testBoolWorks() {
        assertEquals(BoolETHType.ofNativeKotlinType(true).toKotlinType(), true)
        assertEquals(BoolETHType.ofNativeKotlinType(false).toKotlinType(), false)
    }

    @Test
    fun testDecodeString() {
        val input =
            PaginatedByteArray(HexString("0x000000000000000000000000000000000000000000000000000000000000002000000000000000000000000000000000000000000000000000000000000000046c69676900000000000000000000000000000000000000000000000000000000"))
        assertEquals(StringETHType.ofPaginatedByteArray(input)?.toKotlinType(), "ligi")
    }

    @Test
    fun testDecodeInt() {
        val input0 = PaginatedByteArray(HexString("0x0000000000000000000000000000000000000000000000000000000000000000"))
        assertEquals(IntETHType.ofPaginatedByteArray(input0, BitsTypeParams(8))?.toKotlinType(), ZERO)

        val input1 = PaginatedByteArray(HexString("0x0000000000000000000000000000000000000000000000000000000000000001"))
        assertEquals(IntETHType.ofPaginatedByteArray(input1, BitsTypeParams(8))?.toKotlinType(), ONE)

        val input_1 =
            PaginatedByteArray(HexString("0xffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff"))
        assertEquals(IntETHType.ofPaginatedByteArray(input_1, BitsTypeParams(8))?.toKotlinType(),
            BigInteger.fromLong(-1))

        val input127 =
            PaginatedByteArray(HexString("0x000000000000000000000000000000000000000000000000000000000000007f"))
        assertEquals(IntETHType.ofPaginatedByteArray(input127, BitsTypeParams(8))?.toKotlinType(),
            BigInteger.fromLong(127))

        val input_128 =
            PaginatedByteArray(HexString(("0xffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff80")))
        assertEquals(IntETHType.ofPaginatedByteArray(input_128, BitsTypeParams(16))?.toKotlinType(),
            BigInteger.fromLong(-128))
    }
}
