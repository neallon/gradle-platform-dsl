plugins {
    kotlin("jvm") version "2.1.20"
    id("java-gradle-plugin")
    id("maven-publish")
    id("com.gradle.plugin-publish") version "1.3.1"
}

group = "io.github.neallon"
version = "1.1.0"

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(11)
}

gradlePlugin {
    plugins {
        create("pluginPlatform") {
            id = "io.github.neallon.platform-dsl"
            implementationClass = "io.hfx.pluginx.platform.PlatformPlugin"
            displayName = "hfxPlatform — Unified Gradle Repository & Publishing DSL"
            description =
                "A Gradle plugin to unify and simplify repository and publishing repository configuration with a consistent DSL and auto-injection support."
            tags = listOf(
                "repository",
                "publishing",
                "repository-injection",
                "gradle-plugin",
                "dsl"
            )
            website = "https://github.com/neallon/gradle-platform-dsl"
            vcsUrl = "https://github.com/neallon/gradle-platform-dsl"
        }
    }
}
