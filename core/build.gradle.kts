plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {

    // OkHttp
    api(platform("com.squareup.okhttp3:okhttp-bom:4.9.0"))
    api("com.squareup.okhttp3:okhttp")
    api("com.squareup.okhttp3:logging-interceptor")

    // Retrofit
    val retrofit_version = "2.9.0"
    api ("com.squareup.retrofit2:retrofit:$retrofit_version")
    api ("com.squareup.retrofit2:converter-gson:$retrofit_version")

    // Gson
    api ("com.google.code.gson:gson:2.9.0")

    // Paging3 Common
    val paging_version = "3.2.1"
    implementation ("androidx.paging:paging-common:$paging_version")

    // Coroutines core
    val coroutines_version = "1.6.0"
    api ("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version")
}