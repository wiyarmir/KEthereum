package org.kethereum.crypto.impl.ec

import com.ionspin.kotlin.bignum.integer.BigInteger
import org.kethereum.crypto.api.ec.CurvePoint
import org.kethereum.extensions.asJvmBigInteger
import org.kethereum.extensions.asMppBigInteger
import org.spongycastle.math.ec.ECPoint

class EllipticCurvePoint(private val ecPoint: ECPoint) : CurvePoint {
    override val x: BigInteger
        get() = ecPoint.xCoord.toBigInteger().asMppBigInteger()
    override val y: BigInteger
        get() = ecPoint.yCoord.toBigInteger().asMppBigInteger()

    override fun mul(n: BigInteger): CurvePoint =
        ecPoint.multiply(n.asJvmBigInteger()).toCurvePoint()

    override fun add(p: CurvePoint): CurvePoint =
        (p as? EllipticCurvePoint)?.let {
            ecPoint.add(p.ecPoint).toCurvePoint()
        } ?: throw UnsupportedOperationException("Only SpongyCurvePoint multiplication available")

    override fun normalize(): CurvePoint =
        ecPoint.normalize().toCurvePoint()

    override fun isInfinity(): Boolean =
        ecPoint.isInfinity

    override fun encoded(compressed: Boolean): ByteArray =
        ecPoint.getEncoded(compressed)

}

internal fun ECPoint.toCurvePoint(): CurvePoint = EllipticCurvePoint(this)
