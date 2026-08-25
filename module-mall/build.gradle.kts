plugins {
    alias(libs.plugins.android.library)
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
    namespace = "com.minhr.design.module_mall"
    compileSdk = 37

    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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

    sourceSets {
        getByName("main") {
            manifest.srcFile(
                if (isBuildModule) "src/main/module/AndroidManifest.xml"
                else "src/main/AndroidManifest.xml"
            )
        }
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
    kapt(libs.arouter.compiler)
    kapt(libs.dagger.compiler)
    kapt(libs.room.compiler)
    kapt(libs.kotlin.metadata.jvm)
}
