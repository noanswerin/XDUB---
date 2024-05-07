plugins {
    alias(libs.plugins.androidApplication)
}

android {
    namespace = "com.example.myweather"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.myweather"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    buildFeatures {
        viewBinding = true //开启ViewBinding
        buildConfig = true //开启buildConfig，可以获取版本名和版本号
    }

    sourceSets {
        getByName("main") {
            jniLibs.srcDirs("libs")
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation(project(":mynewlibrary"))

    //Room数据库
    implementation ("androidx.room:room-runtime:2.4.2")
    annotationProcessor ("androidx.room:room-compiler:2.4.2")
    //Room 支持RxJava2
    implementation ("androidx.room:room-rxjava2:2.4.2")
    //腾讯MMKV
    implementation ("com.tencent:mmkv:1.2.11")
    //Gson
    implementation ("com.google.code.gson:gson:2.9.0")

    //Glide
    implementation ("com.github.bumptech.glide:glide:4.14.2")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.14.2")

    implementation( "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")





}