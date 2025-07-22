package io.hfx.pluginx.platform.internal

import io.hfx.pluginx.platform.model.ResolvedRepository
import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.api.artifacts.repositories.MavenArtifactRepository
import java.net.URI

object RepositoryInjector {

    fun injectResolvedRepositories(
        handler: RepositoryHandler,
        repositories: List<ResolvedRepository>,
        project: Project
    ): List<MavenArtifactRepository> {
        return repositories.map { resolved ->
            handler.maven { repo ->
                repo.name = resolved.name
                repo.url = URI(resolved.url)
                repo.isAllowInsecureProtocol = resolved.isAllowInsecureProtocol

                if (!resolved.user.isNullOrBlank() && !resolved.password.isNullOrBlank()) {
                    repo.credentials {
                        it.username = resolved.user
                        it.password = resolved.password
                    }
                }

                // 可选：标记是平台自动注入的，调试用
                repo.metadataSources {
                    it.mavenPom()
                    it.artifact()
                    it.ignoreGradleMetadataRedirection()
                }

                // 加入 project logger 打印
                project.logger.debug("Injected repository: ${resolved.name} -> ${resolved.url}")
            }
        }
    }
}