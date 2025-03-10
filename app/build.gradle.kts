plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.google.gms.google.services)
    id("kotlin-parcelize")
    id("kotlin-kapt")
}

android {
    namespace = "com.example.moviebooking"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.moviebooking"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(project(":base"))
    implementation(libs.firebase.auth)
    implementation(libs.firebase.bom)
    implementation(libs.firebase.firestore)

    // Room Local Database
    api("androidx.room:room-runtime:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")
    api("androidx.room:room-ktx:2.6.1")

    implementation("com.google.android.gms:play-services-auth:12.0.1")
    implementation("com.facebook.android:facebook-android-sdk:[8,9)")

    implementation("com.pierfrancescosoffritti.androidyoutubeplayer:core:12.1.1")
    implementation("androidx.core:core-splashscreen:1.0.1")
    implementation("com.github.smarteist:Android-Image-Slider:1.4.0")
    implementation("com.intuit.sdp:sdp-android:1.1.1")
    implementation("com.github.murgupluoglu:seatview-android:3.0.0")
    implementation("com.google.zxing:core:3.2.1")
    implementation("com.vipulasri:ticketview:1.1.2")
}

