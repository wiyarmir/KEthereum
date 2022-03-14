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
                implementation("com.ionspin.kotlin:bignum:${Versions.bignum}")
                implementation("com.github.komputing:khex:${Versions.khex}")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

//
//val copyTestResources: Task = tasks.create<Copy>("copyTestResources") {
//    description = """
//        Copies resources from src/test/resources to build/classes/java/test so they are accessible by test classes
//        """.trim()
//    from("$projectDir/src/main/resources")
//    into("$buildDir/classes/java/test")
//}
//
//tasks.withType<Test> {
//    dependsOn(copyTestResources)
//}
