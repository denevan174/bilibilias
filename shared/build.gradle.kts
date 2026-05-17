import org.jetbrains.kotlin.gradle.plugin.mpp.Framework

plugins {
    alias(libs.plugins.bilibilias.multiplatform.library)
    alias(libs.plugins.bilibilias.multiplatform.koin)
}

kotlin {
    android {
        namespace = "com.imcys.bilibilias.shared"
    }

    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            api(project(":core:data"))
        }
    }

    targets.withType(org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget::class.java).configureEach {
        binaries.withType(Framework::class.java).configureEach {
            export(project(":core:data"))
            transitiveExport = true
        }
    }
}
