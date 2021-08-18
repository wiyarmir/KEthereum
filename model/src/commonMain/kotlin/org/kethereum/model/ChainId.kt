package org.kethereum.model

import com.ionspin.kotlin.bignum.integer.BigInteger
import kotlin.jvm.JvmInline

@JvmInline
value class ChainId(val value: BigInteger) {
    constructor(longValue: Long) : this(BigInteger.fromLong(longValue))
}
