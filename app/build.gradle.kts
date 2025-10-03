plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.harbdm.urbanexplorer"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.harbdm.urbanexplorer"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.harbdm.urbanexplorer.HiltTestRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }

        debug {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        dataBinding = true
    }
}

dependencies {

    implementation(project(":di"))
    implementation(project(":core"))
    implementation(project(":core:designsystem"))
    implementation(project(":core:ui"))
    implementation(project(":core:navigation"))
    implementation(project(":feature:spots"))
    implementation(project(":feature:about"))
    implementation(project(":feature:home"))

    testImplementation(project(":testing"))
    androidTestImplementation(project(":testing"))

    androidTestImplementation(project(":domain"))
    androidTestImplementation(project(":data"))


    implementation(libs.androidx.appcompat.v171)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

// Hilt (for ViewModel injection and other Android components)
    // Hilt (for Dependency Injection)
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    // For Hilt navigation compose
    implementation(libs.androidx.hilt.navigation.compose)

    // Navigation Compose
    implementation(libs.androidx.navigation.compose)

    // Navigation fragment
    implementation(libs.androidx.navigation.fragment.ktx)


    implementation(libs.androidx.ui.text.google.fonts)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    androidTestImplementation(libs.androidx.runner)


    androidTestImplementation(libs.hilt.android.testing)
    kspAndroidTest(libs.hilt.compiler)

    androidTestImplementation(libs.androidx.room.testing)
    androidTestImplementation(libs.truth)

    testImplementation(libs.junit)
}