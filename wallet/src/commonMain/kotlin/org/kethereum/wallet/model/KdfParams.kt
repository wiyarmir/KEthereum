package org.kethereum.wallet.model

import kotlinx.serialization.Serializable

sealed class KdfParams {
    abstract var dklen: Int
    abstract var salt: String?
}

@Serializable
data class Aes128CtrKdfParams(
    var c: Int = 0,
    var prf: String? = null,
    override var dklen: Int = 0,
    override var salt: String? = null,
) : KdfParams()

@Serializable
data class ScryptKdfParams(
    var n: Int = 0,
    var p: Int = 0,
    var r: Int = 0,

    override var dklen: Int = 0,
    override var salt: String? = null,
) : KdfParams()
