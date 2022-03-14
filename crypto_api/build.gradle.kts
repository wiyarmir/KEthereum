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
                implementation(project(":model"))
                implementation(project(":extensions_kotlin"))
                implementation("com.ionspin.kotlin:bignum:${Versions.bignum}")
            }
        }
    }
}
