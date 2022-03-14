plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {implementation(project(":crypto"))
                implementation(project(":crypto_api"))
                implementation(project(":keccak_shortcut"))
                implementation(project(":extensions_kotlin"))
                implementation(project(":model"))

                implementation("com.github.komputing:khex:${Versions.khex}")

                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")

                implementation("com.ionspin.kotlin:bignum:${Versions.bignum}")
                implementation("com.github.cy6erGn0m.kotlinx-uuid:kotlinx-uuid-core:0.1.0")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(project(":test_data"))
                implementation(project(":crypto_impl_spongycastle"))
            }
        }
        val jvmMain by getting {
            dependencies {
            }
        }
        val jvmTest by getting {
            dependencies {
            }
        }
    }
}
