plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("maven-publish")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("org.jlleitschuh.gradle.ktlint") version "12.1.1"
}

android {
    namespace = "com.example.baseproject"
    compileSdk = 35

    defaultConfig {
        minSdk = 24
        multiDexEnabled = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

afterEvaluate {}

dependencies {
    api("androidx.core:core-ktx:1.15.0")
    api("androidx.appcompat:appcompat:1.7.0")
    api("com.google.android.material:material:1.12.0")
    api("androidx.activity:activity:1.9.3")
    api("androidx.constraintlayout:constraintlayout:2.2.0")
    api("androidx.exifinterface:exifinterface:1.4.0-alpha01")
    api("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    testApi("junit:junit:4.13.2")
    androidTestApi("androidx.test.ext:junit:1.2.1")
    androidTestApi("androidx.test.espresso:espresso-core:3.6.1")

    // Koin DI
    api("io.insert-koin:koin-android:4.0.0")

    // Room Local Database
    api("androidx.room:room-runtime:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")
    api("androidx.room:room-ktx:2.6.1")

    // Encrypted shared preferences
    api("androidx.security:security-crypto:1.1.0-alpha06")

    // Retrofit Remote
    api("com.squareup.retrofit2:retrofit:2.11.0")
    api("com.squareup.retrofit2:converter-gson:2.11.0")
    api("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.14")

    // Navigation
    api("androidx.navigation:navigation-fragment-ktx:2.8.4")
    api("androidx.navigation:navigation-ui-ktx:2.8.4")

    // ExoPlayer
    api("androidx.media3:media3-exoplayer:1.5.0")
    api("androidx.media3:media3-exoplayer-dash:1.5.0")
    api("androidx.media3:media3-ui:1.5.0")

    // Coil
    api("io.coil-kt:coil:2.7.0")

    // Shimmer
    api("com.facebook.shimmer:shimmer:0.5.0")

    // Lottie
    api("com.airbnb.android:lottie:6.4.1")
}

afterEvaluate {
    publishing {
        publications {
            register<MavenPublication>("release") {
                groupId = "com.github.Veeci"
                artifactId = "BaseProject"
                version = "1.0.4"

                afterEvaluate {
                    from(components["release"])
                }
            }
        }
        repositories {
            maven {
                url = uri("https://jitpack.io")
            }
        }
    }
}
