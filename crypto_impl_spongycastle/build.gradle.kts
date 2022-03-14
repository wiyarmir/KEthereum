plugins {
    kotlin("jvm")
}

dependencies {
    implementation("com.madgag.spongycastle:prov:1.58.0.0")
    implementation(project(":crypto_api"))
    implementation(project(":crypto_impl_java_provider"))
    implementation(project(":model"))
    implementation(project(":extensions_kotlin"))
    implementation("com.ionspin.kotlin:bignum:${Versions.bignum}")
}
