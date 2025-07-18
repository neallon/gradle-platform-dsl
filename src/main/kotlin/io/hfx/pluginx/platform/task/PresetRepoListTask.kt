package io.hfx.pluginx.platform.task

import io.hfx.pluginx.platform.internal.Constants
import io.hfx.pluginx.platform.internal.RepositoryResolver
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

open class PresetRepoListTask : DefaultTask() {
    init {
        group = Constants.PLUGIN_NAME
        description = "Displays all preset repositories that would be applied by `hfxPlatform`"
    }

    @TaskAction
    fun listRepositories() {
        val repositories = RepositoryResolver.resolvedRepositories(project)
        if (repositories.isEmpty()) {
            logger.lifecycle("No preset repositories found.")
        } else {
            logger.lifecycle("Resolved Preset Repositories:")
            repositories.forEach {
                logger.lifecycle("- ${it.name}: ${it.url}")
            }
        }
    }
}