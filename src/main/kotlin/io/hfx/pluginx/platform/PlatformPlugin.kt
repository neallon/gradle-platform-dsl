package io.hfx.pluginx.platform

import io.hfx.pluginx.platform.dsl.PlatformExtension
import io.hfx.pluginx.platform.internal.Constants
import io.hfx.pluginx.platform.task.ConfigRepoHelpTask
import io.hfx.pluginx.platform.task.PresetRepoListTask
import org.gradle.api.Plugin
import org.gradle.api.Project

class PlatformPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.tasks.register(Constants.REPO_HELP_TASK_NAME, ConfigRepoHelpTask::class.java)
        project.tasks.register(Constants.REPO_LIST_TASK_NAME, PresetRepoListTask::class.java)

        project.extensions.create(Constants.PLUGIN_NAME, PlatformExtension::class.java, project)
    }
}