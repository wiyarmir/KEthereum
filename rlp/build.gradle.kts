plugins {
    kotlin("multiplatform")
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
            dependencies {
                implementation(project(":extensions_kotlin"))
                implementation("com.ionspin.kotlin:bignum:0.3.1")
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation("com.github.komputing:khex:${Versions.khex}")

                implementation("org.assertj:assertj-core:3.20.2")
                implementation("org.junit.jupiter:junit-jupiter-api:${Versions.jupiter}")
                implementation("org.junit.jupiter:junit-jupiter-params:${Versions.jupiter}")
                runtimeOnly("org.junit.jupiter:junit-jupiter-engine:${Versions.jupiter}")

                implementation("org.jetbrains.kotlin:kotlin-test")
                implementation("io.mockk:mockk:1.12.0")
            }
        }
    }
}
