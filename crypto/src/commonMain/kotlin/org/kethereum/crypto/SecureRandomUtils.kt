package org.kethereum.crypto

expect class SecureRandom{
    fun nextBytes(bytes:ByteArray)
}

expect object SecureRandomUtils {
    fun secureRandom(): SecureRandom
}
