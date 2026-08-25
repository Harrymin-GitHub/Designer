plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
}

val isBuildModule = providers.gradleProperty("isBuildModule").orElse("false").get().toBoolean()

kapt {
    arguments {
        arg("AROUTER_MODULE_NAME", project.name)
    }
}

android {
    namespace = "com.minhr.design"
    compileSdk = 37

    defaultConfig {
        applicationId = "com.minhr.design"
        minSdk = 24
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        multiDexEnabled = true
        ndk {
            abiFilters += listOf("armeabi-v7a", "arm64-v8a")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
        buildConfig = true
    }

    val keyPath = providers.gradleProperty("KEY_PATH").orNull
    val store = keyPath?.let { file(it) }
    if (store != null && store.exists()) {
        signingConfigs {
            create("release") {
                storeFile = store
                storePassword = providers.gradleProperty("KEY_PASS").get()
                keyAlias = providers.gradleProperty("ALIAS_NAME").get()
                keyPassword = providers.gradleProperty("ALIAS_PASS").get()
            }
        }
    }

    buildTypes {
        debug {
            isDebuggable = true
            isMinifyEnabled = false
        }
        release {
            isMinifyEnabled = false
            isShrinkResources = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            if (store != null && store.exists()) {
                signingConfig = signingConfigs.getByName("release")
            }
        }
    }
}

kotlin {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11)
    }
}

dependencies {
    kapt(libs.arouter.compiler)
    kapt(libs.dagger.compiler)
    kapt(libs.kotlin.metadata.jvm)
    implementation(project(":common-base"))
    implementation(project(":common-ui"))
    if (!isBuildModule) {
        implementation(project(":module-core"))
        implementation(project(":module-mall"))
        implementation(project(":module-discover"))
    }
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
