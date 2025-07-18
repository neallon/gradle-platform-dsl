package io.hfx.pluginx.platform.dsl

import io.hfx.pluginx.platform.internal.RepositoryInjector
import io.hfx.pluginx.platform.internal.RepositoryResolver
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import javax.inject.Inject

@HfxDsl
open class PublishingRepoExtension @Inject constructor(private val project: Project) {

    fun usePresetPublishingRepositories() {
        val publishing = project.extensions.findByType(PublishingExtension::class.java)
        if (publishing != null) {
            val resolvedRepos = RepositoryResolver.resolvedRepositories(project)
            RepositoryInjector.injectResolvedRepositories(publishing.repositories, resolvedRepos, project)
        }
    }
}