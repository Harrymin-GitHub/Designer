plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "com.shuyu.gsyvideoplayer"
    compileSdk = 37

    defaultConfig {
        minSdk = 24
        consumerProguardFiles("consumer-rules.pro")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    sourceSets {
        getByName("main") {
            jniLibs.srcDirs("libs")
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

dependencies {
    implementation(libs.ijkplayer.java)
    implementation(libs.ijkplayer.exo)
    implementation(libs.videocache)
    implementation(libs.androidx.appcompat)
    implementation(libs.transitionseverywhere)
}
