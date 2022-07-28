plugins {
    id("com.android.application")
    id("com.google.devtools.ksp").version("1.6.21-1.0.5")

    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = 32

    defaultConfig {
        applicationId = "dev.rolandsarosy.newssample"
        minSdk = 23
        targetSdk = 32
        versionCode = 1
        versionName = "0.0.1"
        resourceConfigurations.addAll(listOf("en"))
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    namespace = "dev.rolandsarosy.newssample"
    flavorDimensions += "environment"
    productFlavors {
        create("production") {
            dimension = "environment"
            versionNameSuffix = ".production"
            applicationIdSuffix = ".production"

            resValue("string", "app_name", "News Sample")
            buildConfigField("String", "BASE_URL", "\"https://content.guardianapis.com/\"")
        }

        create("staging") {
            dimension = "environment"
            versionNameSuffix = ".staging"
            applicationIdSuffix = ".staging"

            resValue("string", "app_name", "News Sample Staging")
            buildConfigField("String", "BASE_URL", "\"https://content.guardianapis.com/\"")
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    // AppCompat
    val appCompatVersion by extra("1.4.2")
    implementation("androidx.appcompat:appcompat:$appCompatVersion")

    // KTX Core
    val ktxCoreVersion by extra("1.8.0")
    implementation("androidx.core:core-ktx:$ktxCoreVersion")

    // Android material components
    val materialVersion by extra("1.6.1")
    implementation("com.google.android.material:material:$materialVersion")
}
