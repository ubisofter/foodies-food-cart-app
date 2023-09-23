plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "ru.requestdesign.foodies"
    compileSdk = 34

    defaultConfig {
        applicationId = "ru.requestdesign.foodies"
        minSdk = 26
        //noinspection EditedTargetSdkVersion
        targetSdk = 34
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

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8

    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

kapt {
    generateStubs = true
}

dependencies {

    // Jetpack Compose: Для использования Jetpack Compose для создания пользовательского интерфейса:
    implementation("androidx.compose.ui:ui:1.5.1")
    implementation("androidx.compose.material:material:1.5.1")
    implementation("androidx.compose.foundation:foundation:1.5.1")
    implementation("androidx.compose.ui:ui-tooling-preview:1.5.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")

    // Navigation Compose: Для управления навигацией между фрагментами:
    implementation("androidx.navigation:navigation-compose:2.7.2")

    // GSON: Для работы с данными в JSON-файлах:
    implementation("com.google.code.gson:gson:2.10.1")

    // Lottie: Для отображения анимации Lottie в SplashScreen:
    implementation("com.airbnb.android:lottie:6.1.0")
    implementation("com.airbnb.android:lottie-compose:6.1.0")

    /** Для использования архитектурных компонентов ViewModel и LiveData: **/
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")

    // Material Icons: Для использования иконок:
    implementation("androidx.compose.material:material-icons-core:1.5.1")
    implementation("androidx.compose.material:material-icons-extended:1.5.1")

    // Coil (или другая библиотека для загрузки изображений): Для загрузки и отображения изображений блюд:
    implementation("io.coil-kt:coil:2.4.0")
    implementation("io.coil-kt:coil-compose:2.4.0")

    // Preview
    debugImplementation("androidx.compose.ui:ui-tooling:1.5.1")
    implementation("androidx.compose.ui:ui-tooling-preview:1.5.1")

    // Animations
    implementation("androidx.compose.animation:animation-core:1.5.1")

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}