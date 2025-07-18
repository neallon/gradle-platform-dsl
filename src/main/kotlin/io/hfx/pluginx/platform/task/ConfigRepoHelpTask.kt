package io.hfx.pluginx.platform.task

import io.hfx.pluginx.platform.internal.Constants
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

open class ConfigRepoHelpTask : DefaultTask() {
    init {
        group = Constants.PLUGIN_NAME
        description = "Shows how to configure hfxPlatform repositories using gradle.properties"
    }

    @TaskAction
    fun showHelp() {
        val bold = "\u001B[1m"
        val green = "\u001B[32m"
        val cyan = "\u001B[36m"
        val yellow = "\u001B[33m"
        val reset = "\u001B[0m"

        logger.lifecycle(
            """
            |${bold}${cyan}===============================
            | hfxPlatform Repository Guide
            |===============================${reset}
            |
            |${bold}Define repositories in${reset} ${yellow}gradle.properties${reset}:
            |
            |  ${green}hfx.myRepo.repo.url=https://example.com/maven${reset}
            |  ${green}hfx.myRepo.repo.user=yourUsername${reset}
            |  ${green}hfx.myRepo.repo.password=yourPassword${reset}
            |
            |${bold}- repo.url${reset} is ${bold}required${reset}.
            |${bold}- repo.user${reset} and ${bold}repo.password${reset} are optional.
            |
            |You may define ${bold}multiple${reset} repositories with different names:
            |  e.g. ${green}hfx.repo1.repo.url${reset}, ${green}hfx.repo2.repo.url${reset}
            |
            |${bold}Property Resolution Order:${reset}
            |  1. ${yellow}~/.gradle/gradle.properties${reset}  (global)
            |  2. ${yellow}./gradle.properties${reset}          (project-level, overrides global)
            |
            |${bold}Auto-injected into:${reset}
            |  - ${cyan}project.repositories${reset}
            |  - ${cyan}publishing.repositories${reset} (if applicable)
            |
            |No additional setup needed. Just run:
            |  ${green}./gradlew build${reset}
            |
            |To list available tasks:
            |  ${green}./gradlew tasks --group hfxPlatform${reset}
            |
            """.trimMargin()
        )
    }
}