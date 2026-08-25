plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
}

kapt {
    arguments {
        arg("AROUTER_MODULE_NAME", project.name)
        arg("room.schemaLocation", "$projectDir/schemas")
    }
}

android {
    namespace = "com.minhr.design.module_test"
    compileSdk = 37

    defaultConfig {
        applicationId = "com.minhr.design.module_test"
        minSdk = 24
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        multiDexEnabled = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures {
        dataBinding = true
        buildConfig = true
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
}

kotlin {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11)
    }
}

dependencies {
    implementation(project(":common-base"))
    implementation(project(":common-ui"))
    api(libs.room.rxjava2)
    implementation(libs.jjwt)
    implementation(libs.jwtdecode)
    kapt(libs.dagger.compiler)
    kapt(libs.room.compiler)
    kapt(libs.kotlin.metadata.jvm)
}
