plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
}
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }
}
dependencies {
    implementation(kotlin("stdlib")) // Or be more specific with Kotlin version if needed
    // Example: Coroutines core if base use cases or async utils are here
    implementation(libs.kotlinx.coroutines.core) // Use latest stable

    // Test dependencies
    testImplementation(libs.junit)
    testImplementation(libs.mockk) // Or your preferred mocking framework
}
