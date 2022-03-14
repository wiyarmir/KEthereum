apply {
    from("https://raw.githubusercontent.com/ligi/gradle-common/master/versions_plugin_stable_only.gradle")
}

buildscript {
    repositories {
        gradlePluginPortal()
        maven("https://jitpack.io")
    }

    dependencies {
        classpath(kotlin("gradle-plugin", version = Versions.kotlin))
        classpath(kotlin("serialization", version = Versions.kotlin))
        classpath("com.github.ben-manes:gradle-versions-plugin:${Versions.versions_plugin}")
        classpath("com.github.komputing:kethabi:0.3.1")
        classpath("org.jetbrains.kotlinx:atomicfu-gradle-plugin:0.17.1")
    }
}


subprojects {
    repositories {
        mavenCentral()
        maven("https://jitpack.io")
        maven("https://kotlin.bintray.com/kotlinx")
    }
//
//    apply<JacocoPlugin>()
//    apply<MavenPublishPlugin>()
//    apply<KotlinMultiplatformPlugin>()
//
//    tasks.withType<Test> {
//        useJUnitPlatform()
//    }
//
//    configure<JavaPluginExtension> {
//        withSourcesJar()
//        withJavadocJar()
//    }
//
//    configure<PublishingExtension> {
//        publications {
//            create<MavenPublication>("maven") {
//                from(components["java"])
//            }
//        }
//    }
//
//    afterEvaluate {
//
//        dependencies {
//            "implementation"("org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}")
//
//            "testImplementation"("org.assertj:assertj-core:3.20.2")
//            "testImplementation"("org.junit.jupiter:junit-jupiter-api:${Versions.jupiter}")
//            "testImplementation"("org.junit.jupiter:junit-jupiter-params:${Versions.jupiter}")
//            "testRuntimeOnly"("org.junit.jupiter:junit-jupiter-engine:${Versions.jupiter}")
//
//            "testImplementation"("org.jetbrains.kotlin:kotlin-test")
//            "testImplementation"("io.mockk:mockk:1.12.0")
//        }
//    }
//
//    val compileKotlin: org.jetbrains.kotlin.gradle.tasks.KotlinCompile by tasks
//    val compileTestKotlin: org.jetbrains.kotlin.gradle.tasks.KotlinCompile by tasks
//
//    compileKotlin.kotlinOptions {
//        jvmTarget = "1.8"
//    }
//    compileTestKotlin.kotlinOptions {
//        jvmTarget = "1.8"
//    }
}
