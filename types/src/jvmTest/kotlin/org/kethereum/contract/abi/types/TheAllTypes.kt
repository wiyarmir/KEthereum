package org.kethereum.contract.abi.types

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.kethereum.contract.abi.types.model.NamedETHType
import java.util.stream.Stream
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue


class TheAllTypes {

    companion object {

        @JvmStatic
        fun allSupportedTypesGenerator(): Stream<String> = Stream.of(*allSupportedETHTypes.toTypedArray())

        @JvmStatic
        fun allUnSupportedTypesGenerator(): Stream<String> = Stream.of(*allUnsupportedETHTypes.toTypedArray())

        @JvmStatic
        fun allETHTypesGenerator(): Stream<String> = Stream.of(*allETHTypes.toTypedArray())

    }

    @ParameterizedTest(name = "should be able to convert type {0}")
    @MethodSource("allSupportedTypesGenerator")
    fun allSupportedETHTypesCanBeConverted(typeName: String) {
        assertNotNull(convertStringToABITypeOrNullNoAliases(typeName))
    }

    @ParameterizedTest(name = "should not be able to convert unsupported type {0}")
    @MethodSource("allUnSupportedTypesGenerator")
    fun allUnSupportedETHTypesCanNotBeConverted(typeName: String) {
        assertNull(convertStringToABITypeOrNullNoAliases(typeName))
    }

    @ParameterizedTest(name = "should be able to detect type {0} as supported")
    @MethodSource("allSupportedTypesGenerator")
    fun allSupportedETHTypesCanBeDetected(typeName: String) {
        assertTrue(NamedETHType(typeName).isSupportedETHType())
    }

    @ParameterizedTest(name = "should detect type {0} as unsupported")
    @MethodSource("allUnSupportedTypesGenerator")
    fun allUnSupportedETHTypesCanNotBeDetected(typeName: String) {
        assertTrue(NamedETHType(typeName).isUnSupportedETHType())
    }

    @ParameterizedTest(name = "should detect type {0} as ETH-type")
    @MethodSource("allETHTypesGenerator")
    fun allETHTypesCanNotBeDetected(typeName: String) {
        assertTrue(NamedETHType(typeName).isETHType())
    }

}
