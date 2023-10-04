// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.10" apply false
//    kotlin("multiplatform").version("1.8.0").apply(false)
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.10" apply false
    id("de.jensklingenberg.ktorfit") version "1.7.0" apply false
    alias(libs.plugins.hilt) apply false
    id("com.google.devtools.ksp") version "1.9.10-1.0.13" apply false

}
