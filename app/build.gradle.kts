plugins {
    alias(libs.plugins.android.application)

}

android {
    namespace = "house.duan.appchitieu"
    compileSdk = 34

    defaultConfig {
        applicationId = "house.duan.appchitieu"
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
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.multidex:multidex:2.0.1")
    implementation ("androidx.core:core:1.9.0")

    //implementation("com.google.android.material:material:1.9.0")
  //  implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")
    implementation ("androidx.sqlite:sqlite:2.3.1")
    implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")

}

