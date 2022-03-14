package org.kethereum.bloomfilter

expect class BitSet(nbits: Int) {
    fun set(bitIndex: Int)
    fun get(bitIndex: Int): Boolean
}
