package org.kethereum.wallet

import kotlinx.serialization.encodeToString
import org.kethereum.model.ECKeyPair
import org.kethereum.wallet.model.CipherException
import org.kethereum.wallet.model.FiledWallet
import org.kethereum.wallet.model.ScryptConfig
import org.kethereum.wallet.model.Wallet
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

@Throws(CipherException::class)
fun ECKeyPair.generateWalletFile(
    password: String,
    destinationDirectory: File,
    config: ScryptConfig,
) = createWallet(password, config).let { wallet ->
    FiledWallet(wallet, File(destinationDirectory, wallet.getWalletFileName()).apply {
        writeText(json.encodeToString(wallet))
    })
}

@Throws(CipherException::class)
fun File.loadKeysFromWalletFile(password: String) = readText().loadKeysFromWalletJsonString(password)

fun Wallet.getWalletFileName() =
    SimpleDateFormat("'UTC--'yyyy-MM-dd'T'HH-mm-ss.SSS'--'", Locale.ENGLISH).apply {
        timeZone = TimeZone.getTimeZone("UTC")
    }.format(Date()) + address + ".json"
