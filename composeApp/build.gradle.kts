import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    
    alias(libs.plugins.jetbrainsCompose)
}

kotlin {
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        moduleName = "ChristmasCard2023"
        browser {
            commonWebpackConfig {
                outputFileName = "christmasCard2023.js"
            }
        }
        binaries.executable()
    }
    
    sourceSets {
        
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.material3)
            implementation(compose.ui)
//            implementation("com.arkivanov.decompose:decompose:2.2.2-compose-experimental")
//            implementation("com.arkivanov.decompose:extensions-compose-jetbrains:2.2.2-compose-experimental")
            @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
            implementation(compose.components.resources)
            implementation("io.github.alexzhirkevich:compottie:1.0.0") 
        }
    }
}



compose.experimental {
    web.application {}
}