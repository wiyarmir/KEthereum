package org.kethereum.crypto.test_data

actual class Resource actual constructor(actual val name: String) {

    private val resource = object {}.javaClass.getResource(name)

    actual fun exists(): Boolean = resource != null

    actual fun readText(): String = resource.readText()
}
