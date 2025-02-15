package org.kethereum.crypto

import org.kethereum.crypto.api.cipher.AESCipher
import org.kethereum.crypto.api.ec.Curve
import org.kethereum.crypto.api.ec.KeyPairGenerator
import org.kethereum.crypto.api.ec.Signer
import org.kethereum.crypto.api.mac.Hmac
import org.kethereum.crypto.impl.kdf.PBKDF2
import org.kethereum.crypto.impl.kdf.SCrypt

fun <T> loadClass(name: String): T = try {
    @Suppress("UNCHECKED_CAST")
    Class.forName("org.kethereum.crypto.impl.$name").newInstance() as T
} catch (e: ClassNotFoundException) {
    throw RuntimeException("There is not implementation found for $name - you need to either depend on crypto_impl_spongycastle or crypto_impl_bouncycastle")
}

actual object CryptoAPI {
    actual val hmac by lazy { loadClass("mac.HmacImpl") as Hmac }

    actual val keyPairGenerator by lazy { loadClass("ec.EllipticCurveKeyPairGenerator") as KeyPairGenerator }
    actual val curve by lazy { loadClass("ec.EllipticCurve") as Curve }
    actual val signer by lazy { loadClass("ec.EllipticCurveSigner") as Signer }

    actual val pbkdf2 by lazy { loadClass("kdf.PBKDF2Impl") as PBKDF2 }
    actual val scrypt by lazy { loadClass("kdf.SCryptImpl") as SCrypt }

    actual val aesCipher by lazy { loadClass("cipher.AESCipherImpl") as AESCipher }
}
