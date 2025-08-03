package io.hfx.pluginx.platform.dsl

import io.hfx.pluginx.platform.internal.RepositoryInjector
import io.hfx.pluginx.platform.internal.RepositoryResolver
import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.artifacts.repositories.MavenArtifactRepository
import javax.inject.Inject

@HfxDsl
open class RepoExtension @Inject constructor(private val project: Project) {

    fun usePresetRepositories() {
        val resolvedRepos = RepositoryResolver.resolvedRepositories(project)
        RepositoryInjector.injectResolvedRepositories(project.repositories, resolvedRepos, project)
    }

    fun mavenCentral() {
        project.repositories.mavenCentral()
    }

    fun mavenLocal() {
        project.repositories.mavenLocal()
    }

    fun gradlePluginPortal() {
        project.repositories.gradlePluginPortal()
    }

    fun google() {
        project.repositories.google()
    }

    fun jcenter() {
        @Suppress("DEPRECATION")
        project.repositories.jcenter()
    }

    fun maven(url: String) {
        project.repositories.maven { it.setUrl(url) }
    }

    fun ivy(url: String) {
        project.repositories.ivy { it.setUrl(url) }
    }

    fun maven(configure: Action<MavenArtifactRepository>) {
        project.repositories.maven(configure)
    }
}