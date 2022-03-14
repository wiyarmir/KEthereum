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
                implementation(project(":extensions_kotlin"))
                implementation("com.ionspin.kotlin:bignum:0.3.2")
                implementation("com.github.komputing:khex:${Versions.khex}")
            }
        }
    }
}
