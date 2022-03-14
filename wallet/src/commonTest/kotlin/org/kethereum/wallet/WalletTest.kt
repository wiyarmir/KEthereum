package org.kethereum.wallet

import kotlinx.serialization.decodeFromString
import org.kethereum.extensions.toHexStringNoPrefix
import org.kethereum.wallet.data.ADDRESS_NO_PREFIX
import org.kethereum.wallet.data.AES_128_CTR_TEST_JSON
import org.kethereum.wallet.data.KEY_PAIR
import org.kethereum.wallet.data.PASSWORD
import org.kethereum.wallet.data.PRIVATE_KEY_STRING
import org.kethereum.wallet.data.SCRYPT_TEST_JSON
import org.kethereum.wallet.model.WalletForImport
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class WalletTest {

    @Test
    fun testCreateStandard() {
        assertEquals(KEY_PAIR.createWallet(PASSWORD, STANDARD_SCRYPT_CONFIG).address, ADDRESS_NO_PREFIX)
    }

    @Test
    fun testCreateLight() {
        assertEquals(KEY_PAIR.createWallet(PASSWORD, LIGHT_SCRYPT_CONFIG).address, ADDRESS_NO_PREFIX)
    }

    @Test
    fun testEncryptDecryptStandard() {
        assertEquals(KEY_PAIR.createWallet(PASSWORD, STANDARD_SCRYPT_CONFIG).decrypt(PASSWORD), KEY_PAIR)
    }

    @Test
    fun testEncryptDecryptLight() {
        assertEquals(KEY_PAIR.createWallet(PASSWORD, LIGHT_SCRYPT_CONFIG).decrypt(PASSWORD), KEY_PAIR)
    }

    @Test
    fun testDecryptAes128Ctr() {
        val walletFile = load(AES_128_CTR_TEST_JSON)
        val (privateKey) = walletFile.toTypedWallet().decrypt(PASSWORD)
        assertEquals(privateKey.key.toHexStringNoPrefix(), PRIVATE_KEY_STRING)
    }

    @Test
    fun testDecryptScrypt() {
        val walletFile = load(SCRYPT_TEST_JSON)
        val (privateKey) = walletFile.toTypedWallet().decrypt(PASSWORD)
        assertEquals(privateKey.key.toHexStringNoPrefix(), PRIVATE_KEY_STRING)
    }

    @Test
    fun testGenerateRandomBytes() {
        assertContentEquals(generateRandomBytes(0), byteArrayOf())
        assertEquals(generateRandomBytes(10).size, 10)
    }

    private fun load(source: String): WalletForImport = json.decodeFromString(source)!!
}
