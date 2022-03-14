plugins {
    kotlin("multiplatform")
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
        testRuns["test"].executionTask.configure {
            useJUnit()
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":model"))
                implementation(project(":keccak_shortcut"))
                implementation(project(":extensions_kotlin"))
                implementation(project(":crypto_api"))

                implementation("com.github.komputing:khex:${Versions.khex}")

                implementation("com.ionspin.kotlin:bignum:0.3.2")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(project(":test_data"))
                implementation("com.github.komputing.khash:sha256:${Versions.khash}")
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation("org.slf4j:slf4j-api:${Versions.slf4j}")
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("reflect"))
                implementation(project(":crypto_impl_spongycastle"))
            }
        }
    }
}
