import java.util.Properties

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    kotlin("plugin.serialization") version libs.versions.kotlin
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.monty.jetgooglerepository.android"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.monty.jetgooglerepository.android"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner ="androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.4"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            val p = Properties()
            p.load(project.rootProject.file("local.properties").reader())
            val apiKey: String = p.getProperty("API_KEY")
            buildConfigField("String", "API_KEY", apiKey)
        }
        getByName("debug") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            val p = Properties()
            p.load(project.rootProject.file("local.properties").reader())
            val apiKey: String = p.getProperty("API_KEY")
            buildConfigField("String", "API_KEY", apiKey)
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

}

dependencies {

    implementation(libs.material.design)

    //Compose
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material3)
    implementation(libs.androidx.activity.compose)
    debugImplementation(libs.compose.ui.tooling)

    implementation(libs.compose.foundation)
    implementation(libs.compose.icons.extended)
    implementation(libs.compose.navigation)
//    implementation(libs.compose.animation)

    //Glide
    implementation(libs.glide)

    //lottie
    implementation(libs.lottie)

    //retrofit
    implementation(libs.squareup.retrofit)
    implementation(libs.squareup.converter)
    implementation(libs.squareup.interceptor)
    implementation(libs.squareup.okhttp)

    //Room
    implementation(libs.room.runtime)
    kapt(libs.room.compiler)

    // Kotlin Extensions and Coroutines support for Room
    implementation(libs.room.kotlin)

    //Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    kapt(libs.hilt.compiler)
    implementation(libs.hilt.navigation.compose)

    //Coroutines
    implementation(libs.coroutines.runtime)
    implementation(libs.coroutines.viewModel)
    implementation(libs.androidx.viewmodel.compose)

    //Serialization
    implementation(libs.kotlinx.serialization)

    //Local unit test
    testImplementation(libs.testing.jUnit)
    testImplementation(libs.testing.mockwebserver)
    testImplementation(libs.testing.truth)
    testImplementation(libs.testing.assertK)

    //Instrumental unit test
    androidTestImplementation(libs.testing.jUnit)
    androidTestImplementation(libs.testing.runner)

    androidTestImplementation(libs.testing.espresso)
    androidTestImplementation(libs.testing.jUnit.ext)
    androidTestImplementation(libs.testing.rules)
    androidTestImplementation(libs.testing.room)

    androidTestImplementation(libs.testing.composeTesting)
    androidTestImplementation (libs.testing.truth)
    debugImplementation(libs.testing.composeTestingManifest)

    kaptAndroidTest(libs.hilt.android.compiler)
    androidTestImplementation(libs.testing.hilt)




}
