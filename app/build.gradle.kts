plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.arashaghelifar.fallingword"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.arashaghelifar.fallingword"
        minSdk = 21
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
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {


    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")

//    Hilt
    implementation("com.google.dagger:hilt-android:2.44")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")
    testImplementation("junit:junit:4.12")
    kapt("com.google.dagger:hilt-android-compiler:2.44")


//    Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.11.0")

    // data store
    implementation("androidx.datastore:datastore-preferences:1.0.0")

//    Jetpack Compose
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2024.02.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

//    JUnit and Mockito for unit testing
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.mockito.kotlin:mockito-kotlin:3.2.0")

//    Coroutines test dependencies for testing suspend functions
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.0")

//    Mockk for mocking objects in Kotlin
    testImplementation("io.mockk:mockk:1.12.0")

//    Kotlinx serialization for JSON serialization/deserialization
    testImplementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")

//    AndroidX core testing library for LiveData and other architecture components testing
    testImplementation("androidx.arch.core:core-testing:2.2.0")

//    Jetpack compose testing dependencies
    testImplementation("androidx.compose.ui:ui-test-junit4:1.6.1")


//    instrumental test
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

}


// Allow references to generated code
kapt {
    correctErrorTypes = true
}