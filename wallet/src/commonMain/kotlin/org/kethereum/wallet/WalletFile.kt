@file:JvmName("WalletFile")

package org.kethereum.wallet

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import org.kethereum.model.ECKeyPair
import org.kethereum.wallet.model.Aes128CtrKdfParams
import org.kethereum.wallet.model.CipherException
import org.kethereum.wallet.model.KdfParams
import org.kethereum.wallet.model.ScryptConfig
import org.kethereum.wallet.model.ScryptKdfParams
import org.kethereum.wallet.model.WalletForImport
import kotlin.jvm.JvmName

object KdfParamsSerializer : JsonContentPolymorphicSerializer<KdfParams>(KdfParams::class) {
    override fun selectDeserializer(element: JsonElement) = when {
        "prf" in element.jsonObject -> Aes128CtrKdfParams.serializer()
        "n" in element.jsonObject -> ScryptKdfParams.serializer()
        else -> throw IllegalArgumentException("Could not detect KDFParams")
    }
}

internal val json = Json {
    ignoreUnknownKeys = true
}

@Throws(CipherException::class)
fun ECKeyPair.generateWalletJSON(
    password: String,
    config: ScryptConfig,
) = createWallet(password, config).let { wallet ->
    json.encodeToString(wallet)
}

@Throws(CipherException::class)
fun String.loadKeysFromWalletJsonString(password: String) =
    json.decodeFromString<WalletForImport?>(this)?.toTypedWallet()?.decrypt(password)

