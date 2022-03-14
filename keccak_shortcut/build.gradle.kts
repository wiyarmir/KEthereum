plugins {
    kotlin("multiplatform")
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("com.github.komputing:khex:${Versions.khex}")
                implementation("com.github.komputing.khash:keccak:${Versions.khash}")
                implementation("com.ionspin.kotlin:bignum:0.3.2")
            }
        }
    }
}
