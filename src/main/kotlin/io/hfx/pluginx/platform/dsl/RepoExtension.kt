package io.hfx.pluginx.platform.dsl

import io.hfx.pluginx.platform.internal.RepositoryInjector
import io.hfx.pluginx.platform.internal.RepositoryResolver
import org.gradle.api.Project
import javax.inject.Inject

@HfxDsl
open class RepoExtension @Inject constructor(private val project: Project) {

    fun usePresetRepositories() {
        val resolvedRepos = RepositoryResolver.resolvedRepositories(project)
        RepositoryInjector.injectResolvedRepositories(project.repositories, resolvedRepos, project)
    }

}