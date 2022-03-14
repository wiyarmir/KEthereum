package org.kethereum.extensions

import com.ionspin.kotlin.bignum.integer.util.fromTwosComplementByteArray
import com.ionspin.kotlin.bignum.integer.util.toTwosComplementByteArray
import com.ionspin.kotlin.bignum.integer.BigInteger as MppBigInteger
import java.math.BigInteger as JvmBigInteger

fun JvmBigInteger.asMppBigInteger(): MppBigInteger = MppBigInteger.fromTwosComplementByteArray(toByteArray())
fun MppBigInteger.asJvmBigInteger(): JvmBigInteger = JvmBigInteger(toTwosComplementByteArray())
