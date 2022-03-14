plugins {
    kotlin("multiplatform")
    id("kotlinx-atomicfu")
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
                implementation("com.github.komputing:khex:${Versions.khex}")
                implementation("com.github.komputing.khash:sha256:${Versions.khash}")
                implementation("com.ionspin.kotlin:bignum:${Versions.bignum}")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(project(":test_data"))
            }
        }
    }
}
