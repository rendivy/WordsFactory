plugins {
    kotlin("kapt")
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}


dependencies {
    implementation(libs.kotlinx.coroutines.core)
    implementation("com.google.dagger:dagger:2.48")
    kapt("com.google.dagger:dagger-compiler:2.48")
}