@file:UseSerializers(KdfParamsSerializer::class)

package org.kethereum.wallet.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import org.kethereum.wallet.KdfParamsSerializer

const val AES_128_CTR = "pbkdf2"
const val SCRYPT = "scrypt"

@Serializable
data class CipherParams(var iv: String)

@Serializable
data class WalletCrypto(
    var cipher: String,
    var ciphertext: String,
    var cipherparams: CipherParams,
    var kdf: String,
    var kdfparams: KdfParams,
    var mac: String,
)

@Serializable
internal data class WalletForImport(

    var address: String? = null,

    var crypto: WalletCrypto? = null,

    @SerialName("Crypto")//for MyEtherWallet json
    var cryptoFromMEW: WalletCrypto? = null,

    var id: String? = null,
    var version: Int = 0,
)

@Serializable
data class Wallet(
    val address: String?,
    val crypto: WalletCrypto,
    val id: String,
    val version: Int,
)
