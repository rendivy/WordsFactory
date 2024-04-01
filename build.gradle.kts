buildscript {
    dependencies {
        classpath(libs.google.services)
    }
}
plugins {
    id("com.google.gms.google-services") version "4.4.1" apply false
    alias(libs.plugins.dagger.hilt) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false
    alias(libs.plugins.kotlinx.serialization) apply false
    alias(libs.plugins.androidLibrary) apply false
}