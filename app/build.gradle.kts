
plugins {
    id("com.android.application")
    id("kotlin-parcelize")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("kotlin-kapt")
    alias(libs.plugins.hilt)
    id("dagger.hilt.android.plugin")
    id("de.jensklingenberg.ktorfit") version "1.7.0"
    id ("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp") version "1.9.10-1.0.13"
}

android {
    namespace = "com.example.composegenapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.composegenapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}



dependencies {

 //   implementation(libs.engage.core)

    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    implementation(libs.core.ktx)
    implementation(libs.appcompat)

    val composeBom = platform(libs.androidx.compose.bom)
    implementation(composeBom)

    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.foundation.layout)
    implementation(libs.androidx.compose.ui.util)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.animation)
    implementation(libs.androidx.compose.material.iconsExtended)
    implementation(libs.androidx.compose.ui.tooling.preview)

    implementation(libs.androidx.material3)

    implementation(libs.io.ktor.ktor.client.core)
    implementation(libs.ktor.ktor.client.okhttp)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)

    implementation(libs.ktorfit.lib)
    ksp("de.jensklingenberg.ktorfit:ktorfit-ksp:1.7.0")

    implementation(libs.io.ktor.ktor.client.core)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    implementation(libs.coil)
    implementation(libs.coil.compose)

    implementation(libs.androidx.material.icons.extended)

    kapt(libs.androidx.room.compiler)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)

    implementation (libs.androidx.navigation.compose)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    kapt(libs.hilt.ext.compiler)


    implementation(libs.timber)


    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    debugImplementation(libs.androidx.compose.ui.tooling)
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

}
