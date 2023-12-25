import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    
    alias(libs.plugins.jetbrainsCompose)
}


val osName = System.getProperty("os.name")
val targetOs = when {
    osName == "Mac OS X" -> "macos"
    osName.startsWith("Win") -> "windows"
    osName.startsWith("Linux") -> "linux"
    else -> error("Unsupported OS: $osName")
}

val osArch = System.getProperty("os.arch")
var targetArch = when (osArch) {
    "x86_64", "amd64" -> "x64"
    "aarch64" -> "arm64"
    else -> error("Unsupported arch: $osArch")
}

val target = "${targetOs}-${targetArch}"

var version = "0.0.0-SNAPSHOT"
if (project.hasProperty("skiko.version")) {
    version = project.properties["skiko.version"] as String
}

val resourcesDir = file("$buildDir/resources/")
val absolutePath = resourcesDir.absolutePath

val skikoWasm by configurations.creating

val isCompositeBuild = extra.properties.getOrDefault("skiko.composite.build", "") == "1"

dependencies {
    if (isCompositeBuild) {
        val filePath = gradle.includedBuild("skiko").projectDir
            .resolve("./build/libs/skiko-wasm-$version.jar")
        skikoWasm(files(filePath))
    } else {
        skikoWasm("org.jetbrains.skiko:skiko-js-wasm-runtime:$version")
    }
}

val unzipTask = tasks.register("unzipWasm", Copy::class) {
    destinationDir = file(absolutePath)
    from(skikoWasm.map { zipTree(it) })

    if (isCompositeBuild) {
        val skikoWasmJarTask = gradle.includedBuild("skiko").task(":skikoWasmJar")
        dependsOn(skikoWasmJarTask)
    }

    // Set duplicatesStrategy for the unzip task
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}

kotlin {
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        moduleName = "ChristmasCard2023"
        browser {
            commonWebpackConfig {
                outputFileName = "christmasCard2023.js"
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    static = (static ?: mutableListOf()).apply {
                        // Serve sources to debug inside browser
                        add(project.projectDir.path)
                        add(project.projectDir.path + "/commonMain/")
                        add(project.projectDir.path + "/wasmJsMain/")
                    }
                }
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
            implementation("org.jetbrains.skiko:skiko:$version")
        }

        val commonMain by getting {
            dependsOn(commonMain.get())
        }

        val jsWasmMain by creating {
            dependsOn(commonMain)
            resources.setSrcDirs(resources.srcDirs)
            resources.srcDirs(unzipTask.map { it.destinationDir })
        }

        val wasmJsMain by getting {
            dependsOn(jsWasmMain)
        }
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.dsl.KotlinJsCompile>().configureEach {
    dependsOn(unzipTask)
}

tasks.withType<Copy> {
    if (name == "wasmJsProcessResources") {
        duplicatesStrategy = DuplicatesStrategy.INCLUDE
    }
}


compose.experimental {
    web.application {}
}