import org.gradle.api.JavaVersion

object Config {
    const val minSdk = 21
    const val compileSdk = 29
    const val targetSdk = 29
    val javaVersion = JavaVersion.VERSION_1_8
    val javaVersion_1_6 = JavaVersion.VERSION_1_6
    const val buildTools = "29.0.2"
}

object Versions {
    const val javaxAnnotationVersion = "1.0"
    const val javaxInjectVersion = "1"

    const val kotlinVersion = "1.3.61"
    const val gradleAndroid = "3.6.0"

    const val junit = "4.12"
    const val androidxEspresso = "3.1.0"
    const val androidxTesting = "1.1.1"

    const val appCompatVersion = "1.1.0"
    const val materialVersion = "1.0.0"
    const val constraintLayoutVersion = "1.1.3"
    const val recyclerViewVersion = "1.1.0"
    const val swipeRefreshVersion = "1.0.0"
    const val cardViewVersion = "1.0.0"
    const val coreVersion = "1.3.0"
    const val googleAuthVersion = "18.0.0"

    const val navComponentVersion = "2.3.0-alpha06"

    const val roomVersion = "2.2.3"
    const val lifecycleVersion= "2.2.0"

    const val kotlinxVersion = "1.3.1"

    const val glideVersion = "4.10.0"
    const val coilVersion = "0.11.0"

    const val retrofitVersion = "2.6.1"
    const val okhttp3Version = "3.12.0"

    const val multilineCollapsingVersion= "27.1.1"
    const val materialLoadingButtonVersion= "0.0.1"
    const val circleImageViewVersion = "3.1.0"
    const val imagePickerVersion = "2.2.0"
    const val imageCropperVersion = "2.8.0"
    const val calculatorDialogVersion = "2.2.0"
    const val expandableViewVersion = "2.9.2"

    const val chuckVersion= "1.1.0"

    const val firebaseAnalyticsVersion = "17.3.0"
    const val firebaseAuthVersion = "19.3.0"
    const val firebaseFirestoreVersion = "21.4.2"
    const val firebaseMessagingVersion = "20.1.4"
    const val firebaseStorageVersion = "19.1.1"
    const val firebaseInAppMessagingVersion = "19.0.4"
    const val firebaseRemoteConfigVersion = "19.1.3"
}

object Deps {

    const val javaxAnnotation = "javax.annotation:jsr250-api:${Versions.javaxAnnotationVersion}"
    const val javaxInject = "javax.inject:javax.inject:${Versions.javaxInjectVersion}"

    // androidx
    const val androidxConstraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"
    const val androidxRecyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerViewVersion}"
    const val androidxAppCompat = "androidx.appcompat:appcompat:${Versions.appCompatVersion}"
    const val androidxSwipeRefresh = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefreshVersion}"
    const val androidxCardView = "androidx.cardview:cardview:${Versions.cardViewVersion}"
    const val androidxCore = "androidx.core:core-ktx:${Versions.coreVersion}"

    // navigation component
    const val androidxNavFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navComponentVersion}"
    const val androidxNavUi = "androidx.navigation:navigation-ui-ktx:${Versions.navComponentVersion}"
    const val androidxNavDynamicFeature = "androidx.navigation:navigation-dynamic-features-fragment:${Versions.navComponentVersion}"

    // lifecycle
    const val androidxLifeCycleExtension = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycleVersion}"
    const val androidxViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleVersion}"
    const val androidxLivedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycleVersion}"

    // coroutine
    const val coroutineCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinxVersion}"
    const val coroutineAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinxVersion}"

    // room
    const val androidxRoom = "androidx.room:room-ktx:${Versions.roomVersion}"
    const val androidxRoomCompiler = "androidx.room:room-compiler:${Versions.roomVersion}"

    // test
    const val testLibJunit = "junit:junit:${Versions.junit}"
    const val testAndroidxRules = "androidx.test:rules:${Versions.androidxTesting}"
    const val testAndroidxRunner = "androidx.test:runner:${Versions.androidxTesting}"
    const val testAndroidxEspressoCore = "androidx.test.espresso:espresso-core:${Versions.androidxEspresso}"

    // google material
    const val googleMaterial = "com.google.android.material:material:${Versions.materialVersion}"
    const val googleAuth = "com.google.android.gms:play-services-auth:${Versions.googleAuthVersion}"

    // glide
    const val glide = "com.github.bumptech.glide:glide:${Versions.glideVersion}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glideVersion}"

    const val coil = "io.coil-kt:coil:${Versions.coilVersion}"

    // retrofit
    const val retrofitConverterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofitVersion}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp3Version}"
    const val okhttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp3Version}"

    // others
    const val materialLoadingButton = "com.github.anangkur:Material-Loading-Button:${Versions.materialLoadingButtonVersion}"
    const val multilineCollapsingToolbar = "net.opacapp:multiline-collapsingtoolbar:${Versions.multilineCollapsingVersion}"
    const val circleImageView = "de.hdodenhof:circleimageview:${Versions.circleImageViewVersion}"
    const val imagePicker = "com.github.esafirm.android-image-picker:imagepicker:${Versions.imagePickerVersion}"
    const val imageCropper = "com.theartofdev.edmodo:android-image-cropper:${Versions.imageCropperVersion}"
    const val calculatorDialog = "com.maltaisn:calcdialog:${Versions.calculatorDialogVersion}"
    const val expandableView = "net.cachapa.expandablelayout:expandablelayout:${Versions.expandableViewVersion}"

    // chuck
    const val chuck = "com.readystatesoftware.chuck:library:${Versions.chuckVersion}"
    const val chuckNoOp = "com.readystatesoftware.chuck:library-no-op:${Versions.chuckVersion}"

    // tools
    const val toolsGradleAndroid = "com.android.tools.build:gradle:${Versions.gradleAndroid}"
    const val toolsKotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"

    // firebase
    const val firebaseAnalytics = "com.google.firebase:firebase-analytics:${Versions.firebaseAnalyticsVersion}"
    const val firebaseAuth = "com.google.firebase:firebase-auth:${Versions.firebaseAuthVersion}"
    const val firebaseFirestore = "com.google.firebase:firebase-firestore-ktx:${Versions.firebaseFirestoreVersion}"
    const val firebaseMessaging = "com.google.firebase:firebase-messaging:${Versions.firebaseMessagingVersion}"
    const val firebaseStorage = "com.google.firebase:firebase-storage:${Versions.firebaseStorageVersion}"
    const val firebaseInAppMessaging = "com.google.firebase:firebase-inappmessaging-display:${Versions.firebaseInAppMessagingVersion}"
    const val firebaseConfig = "com.google.firebase:firebase-config:${Versions.firebaseRemoteConfigVersion}"
}