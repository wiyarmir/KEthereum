package org.kethereum.bloomfilter

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class BloomFilterTest {
    @Test
    fun mightContain() {
        val bloomFilter = BloomFilter(100)
        bloomFilter.add("hello".encodeToByteArray())
        bloomFilter.add("bloom filter".encodeToByteArray())

        assertTrue(bloomFilter.mightContain("hello".encodeToByteArray()))
        assertTrue(bloomFilter.mightContain("bloom filter".encodeToByteArray()))
        assertFalse(bloomFilter.mightContain("xxx".encodeToByteArray()))
    }
}
