
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

    implementation("com.google.android.engage:engage-core:1.3.0")
    val ktorVersion = "2.3.4"

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui:1.5.2")
    implementation("androidx.compose.ui:ui-graphics:1.5.2")
    implementation("androidx.compose.ui:ui-tooling-preview:1.5.2")
    implementation("androidx.compose.material3:material3:1.1.2")

    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-okhttp:$ktorVersion")
    implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")

    implementation("de.jensklingenberg.ktorfit:ktorfit-lib:1.7.0")
    ksp("de.jensklingenberg.ktorfit:ktorfit-ksp:1.7.0")

    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    implementation("com.squareup.moshi:moshi-kotlin:1.14.0")

    implementation(libs.coil)
    implementation(libs.coil.compose)

    implementation("androidx.compose.material:material-icons-extended:1.5.1")

    kapt(libs.androidx.room.compiler) // 'androidx.room:room-compiler:2.5.2'
    implementation(libs.androidx.room.runtime)// 'androidx.room:room-runtime:2.5.2'
    implementation(libs.androidx.room.ktx) // implementation 'androidx.room:room-ktx:2.5.2'

    implementation (libs.androidx.navigation.compose)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    kapt(libs.hilt.ext.compiler)


    implementation(libs.timber)


    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

}