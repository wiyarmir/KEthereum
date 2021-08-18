package org.kethereum.crypto

import org.kethereum.crypto.api.cipher.AESCipher
import org.kethereum.crypto.api.ec.Curve
import org.kethereum.crypto.api.ec.KeyPairGenerator
import org.kethereum.crypto.api.ec.Signer
import org.kethereum.crypto.api.mac.Hmac
import org.kethereum.crypto.impl.kdf.PBKDF2
import org.kethereum.crypto.impl.kdf.SCrypt

expect object CryptoAPI {
    val hmac: Hmac

    val keyPairGenerator: KeyPairGenerator
    val curve: Curve
    val signer: Signer

    val pbkdf2: PBKDF2
    val scrypt: SCrypt

    val aesCipher: AESCipher
}
