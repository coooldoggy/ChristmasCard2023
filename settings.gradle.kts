rootProject.name = "christmas-card"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven( "https://oss.sonatype.org/content/repositories/snapshots/" )
        maven(url = "https://maven.pkg.jetbrains.space/public/p/skiko/maven")
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven( "https://oss.sonatype.org/content/repositories/snapshots/" )
        maven(url = "https://maven.pkg.jetbrains.space/public/p/skiko/maven")
    }
}

include(":composeApp")

if (extra.properties.getOrDefault("skiko.composite.build", "") == "1") {
    includeBuild("../../skiko") {
        dependencySubstitution {
            substitute(module("org.jetbrains.skiko:skiko")).using(project(":"))
        }
    }
}

