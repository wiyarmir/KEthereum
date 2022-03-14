package org.kethereum.bloomfilter

import com.ionspin.kotlin.bignum.integer.BigInteger
import com.ionspin.kotlin.bignum.integer.Sign
import kotlinx.atomicfu.locks.reentrantLock
import kotlinx.atomicfu.locks.withLock
import org.komputing.khash.sha256.extensions.sha256

class BloomFilter(private val size: Int) {
    private val bits = BitSet(size)

    // TODO performance of not being a write/read lock
    private val lock = reentrantLock()

    fun add(value: ByteArray) {
        lock.withLock {
            for (seed in 1..3) {
                bits.set(hashing(size, seed, value))
            }
        }
    }

    fun mightContain(value: ByteArray): Boolean {
        lock.withLock {
            for (seed in 1..3) {
                if (!bits.get(hashing(size, seed, value))) {
                    return false
                }
            }
        }
        return true
    }

    private fun hashing(filterSize: Int, seed: Int, value: ByteArray) =
        BigInteger.fromByteArray(value.plus(seed.toByte()).sha256(), Sign.POSITIVE)
            .remainder(BigInteger.fromLong(filterSize.toLong()))
            .intValue()
}
