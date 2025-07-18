package io.hfx.pluginx.platform.dsl

import org.gradle.api.Action
import org.gradle.api.Project
import javax.inject.Inject

@HfxDsl
abstract class PlatformExtension @Inject constructor(private val project: Project) {

    fun repositories(action: Action<in RepoExtension>) {
        val block = project.objects.newInstance(RepoExtension::class.java, project)
        action.execute(block)
    }

    fun publishing(action: Action<in PublishingRepoExtension>) {
        val block = project.objects.newInstance(PublishingRepoExtension::class.java, project)
        action.execute(block)
    }
}