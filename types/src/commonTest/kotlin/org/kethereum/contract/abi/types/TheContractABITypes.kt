package org.kethereum.contract.abi.types

import org.kethereum.contract.abi.types.model.ContractABITypeDefinition
import org.kethereum.contract.abi.types.model.type_params.BitsTypeParams
import org.kethereum.contract.abi.types.model.type_params.BytesTypeParams
import org.kethereum.contract.abi.types.model.types.AddressETHType
import org.kethereum.contract.abi.types.model.types.BoolETHType
import org.kethereum.contract.abi.types.model.types.BytesETHType
import org.kethereum.contract.abi.types.model.types.IntETHType
import org.kethereum.contract.abi.types.model.types.UIntETHType
import org.komputing.khex.extensions.hexToByteArray
import org.komputing.khex.model.HexString
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertIs

class TheContractABITypes {

    @Test
    fun canDealWithAllKnownTypes() {
        listOf("bool",
            "string",
            "bytes20",
            "uint256",
            "uint96",
            "int8",
            "uint192",
            "uint32",
            "uint16",
            "bytes15",
            "bytes14",
            "address",
            "bytes12",
            "bytes32",
            "bytes5",
            "uint80",
            "bytes3",
            "bytes4",
            "uint224",
            "int256",
            "uint128",
            "int32",
            "bytes",
            "bytes8",
            "uint88",
            "uint64",
            "bytes1",
            "uint8",
            "bytes2",
            "uint24",
            "bytes16",
            "uint",
            "int",
            "byte").forEach {
            assertIs<ContractABITypeDefinition>(convertStringToABIType(it))
        }
    }

    @Test
    fun rejectsInvalidTypes() {
        listOf("bool123", "uint257", "uint-1", "bytes-1", "yolo").forEach {
            assertFailsWith<IllegalArgumentException> {
                convertStringToABIType(it)
            }
        }
    }

    @Test
    fun detectsSigned() {
        assertEquals(convertStringToABIType("uint8").ethTypeKClass, UIntETHType::class)
        assertEquals(convertStringToABIType("int8").ethTypeKClass, IntETHType::class)
    }

    @Test
    fun bitsExtractedCorrectly() {
        assertEquals(convertStringToABIType("uint8").params, BitsTypeParams(8))
        assertEquals(convertStringToABIType("int8").params, BitsTypeParams(8))
        assertEquals(convertStringToABIType("uint16").params, BitsTypeParams(16))
        assertEquals(convertStringToABIType("int16").params, BitsTypeParams(16))
    }

    @Test
    fun bytesExtractedCorrectly() {
        assertEquals(convertStringToABIType("bytes23").params, BytesTypeParams(23))
    }

    @Test
    fun unsignedIntABITypeWillNotAcceptNegativeNumber() {
        assertFailsWith<IllegalArgumentException> {
            UIntETHType.ofSting("-1", BitsTypeParams(8))
        }
    }

    @Test
    fun acceptsCorrectAddress() {
        val address = "0xbE27686a93c54Af2f55f16e8dE9E6Dc5dccE915e"

        val myAddress = AddressETHType.ofString(address)

        assertContentEquals(myAddress.paddedValue, ByteArray(12) + HexString(address).hexToByteArray())
    }

    @Test
    fun rejectsInvalidAddress() {
        assertFailsWith<IllegalArgumentException> {
            AddressETHType.ofString("0x123")
        }

        assertFailsWith<IllegalArgumentException> {
            AddressETHType.ofString("0xbE27686a93c54Af2f55f16e8dE9E6Dc5dccE915e123")
        }

        assertFailsWith<IllegalArgumentException> {
            AddressETHType.ofString("0xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx")
        }
    }

    @Test
    fun rejectsInvalidBools() {
        assertFailsWith<IllegalArgumentException> {
            BoolETHType.ofString("0x123")
        }

        assertFailsWith<IllegalArgumentException> {
            BoolETHType.ofString("yolo")
        }

        assertFailsWith<IllegalArgumentException> {
            BoolETHType.ofString("truewithdirt")
        }
    }

    @Test
    fun handlesValidBools() {
        assertContentEquals(BoolETHType.ofString("true").paddedValue,
            HexString("0x" + "0".repeat(63) + "1").hexToByteArray())

        assertContentEquals(BoolETHType.ofString("TRUE").paddedValue,
            HexString("0x" + "0".repeat(63) + "1").hexToByteArray())

        assertContentEquals(BoolETHType.ofString("false").paddedValue,
            HexString("0x" + "0".repeat(64)).hexToByteArray())

        assertContentEquals(BoolETHType.ofString("FALSE").paddedValue,
            HexString("0x" + "0".repeat(64)).hexToByteArray())
    }

    @Test
    fun handlesBytes() {
        assertContentEquals(BytesETHType.ofString("0xFA", BytesTypeParams(1)).paddedValue,
            HexString("0xFA" + "0".repeat(62)).hexToByteArray())
    }
}
