package org.kethereum.crypto

import com.ionspin.kotlin.bignum.integer.toBigInteger
import org.kethereum.extensions.hexToBigInteger
import org.kethereum.model.SignatureData
import org.komputing.khex.model.HexString
import kotlin.test.Test
import kotlin.test.assertEquals

class TheSignatures {

    @Test
    fun createsCorrectSignatureHex() {

        val signatureData = SignatureData(
            HexString("0x0031f6d21dec448a213585a4a41a28ef3d4337548aa34734478b563036163786").hexToBigInteger(),
            HexString("0x2ff816ee6bbb82719e983ecd8a33a4b45d32a4b58377ef1381163d75eedc900b").hexToBigInteger(),
            27.toBigInteger()
        )

        assertEquals(signatureData.toHex(),
            "0031f6d21dec448a213585a4a41a28ef3d4337548aa34734478b5630361637862ff816ee6bbb82719e983ecd8a33a4b45d32a4b58377ef1381163d75eedc900b1b")
    }
}
