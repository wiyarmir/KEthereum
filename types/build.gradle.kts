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
                implementation(project(":model"))

                implementation("com.github.komputing:khex:${Versions.khex}")

                implementation("com.ionspin.kotlin:bignum:${Versions.bignum}")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(project(":test_data"))
            }
        }
        val jvmMain by getting {
            dependencies {
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(("org.junit.jupiter:junit-jupiter-params:${Versions.jupiter}"))
            }
        }
    }
}
