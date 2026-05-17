// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.protobuf) apply false
    alias(libs.plugins.gms.google.services) apply false
    alias(libs.plugins.firebase.crashlytics) apply false
    alias(libs.plugins.firebase.perf) apply false
    alias(libs.plugins.kotlin.plugin.serialization) apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.android.multiplatform.library) apply false
    alias(libs.plugins.androidx.room3) apply false
    alias(libs.plugins.wire) apply false
    alias(libs.plugins.kmp.nativecoroutines) apply false

}

val iosArtifactModules = listOf(
    ":shared" to "ASShared",
)

tasks.register("assembleSharedIosArtifacts") {
    group = "build"
    description = "Builds the aggregated iOS XCFramework artifacts in each source module's build directory."

    dependsOn(
        iosArtifactModules.map { (path, frameworkName) ->
            "$path:assemble${frameworkName}ReleaseXCFramework"
        }
    )
}
