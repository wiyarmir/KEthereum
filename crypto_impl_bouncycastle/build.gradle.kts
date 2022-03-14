plugins {
    kotlin("jvm")
}

dependencies {
    implementation("org.bouncycastle:bcprov-jdk15to18:1.69")
    implementation(project(":crypto_api"))
    implementation(project(":crypto_impl_java_provider"))
    implementation(project(":model"))
    implementation(project(":extensions_kotlin"))
    implementation("com.ionspin.kotlin:bignum:0.3.2")
}
