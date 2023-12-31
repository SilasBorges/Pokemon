plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
    id("io.gitlab.arturbosch.detekt")
    id("androidx.navigation.safeargs.kotlin")
}

apply {
    from("../config/detekt/detekt.gradle")
}

android {
    namespace = "com.companysilas.pokemon"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.companysilas.pokemon"
        minSdk = 26
        targetSdk = 33
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {

    implementation(project(":core"))

    //AndroidX
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //Material
    implementation("com.google.android.material:material:1.9.0")
    testImplementation("junit:junit:4.13.2")

    //Palette
    implementation ("androidx.palette:palette:1.0.0")

    // Navigation
    val nav_version = "2.5.3"
    implementation ("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation ("androidx.navigation:navigation-ui-ktx:$nav_version")

    // ViewModel and LiveData
    val lifecycle_version = "2.6.2"
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version")

    //Coroutines
    val coroutines_version = "1.6.0"
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version")

    // Paging3
    val paging_version = "3.2.1"
    implementation ("androidx.paging:paging-runtime-ktx:$paging_version")

    // Glide
    val glide_version = "4.16.0"
    implementation ("com.github.bumptech.glide:glide:$glide_version")

    // CircleImage
    implementation ("de.hdodenhof:circleimageview:3.1.0")

    // Other Libs
    implementation ("com.facebook.shimmer:shimmer:0.5.0")
    implementation ("androidx.datastore:datastore-preferences:1.0.0")

    // Koin
    implementation ("io.insert-koin:koin-android:3.1.5")
}