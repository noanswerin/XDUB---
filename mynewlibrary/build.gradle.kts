plugins {
    alias(libs.plugins.androidLibrary)
}

android {
    namespace = "com.example.mynewlibrary"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    //okhTTp
    implementation("com.squareup.okhttp3:okhttp:3.14.9")
    //retrofit2
    api ("com.squareup.retrofit2:retrofit:2.9.0")
    //这里用api 是为了让其他模块也可以使用gson
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    //日志拦截器
    implementation ("com.squareup.okhttp3:logging-interceptor:3.10.0")
    implementation ("com.squareup.retrofit2:adapter-rxjava2:2.4.0")
    //rxjava
    api ("io.reactivex.rxjava2:rxandroid:2.1.1")
    implementation ("io.reactivex.rxjava2:rxjava:2.2.12")
    //viewbinding
    implementation ("androidx.databinding:viewbinding:7.3.1")

}