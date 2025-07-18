package io.hfx.pluginx.platform.internal

import io.hfx.pluginx.platform.model.ResolvedRepository
import org.gradle.api.Project
import java.io.File
import java.util.*

object RepositoryResolver {
    fun resolvedRepositories(project: Project): List<ResolvedRepository> {
        val properties = loadMergedRepoProperties(project)
        return parseRepositories(properties)
    }

    private fun loadMergedRepoProperties(project: Project): Properties {
        val result = Properties()

        // 1. 先加载 ~/.gradle/gradle.properties
        val userHomeProps = File(gradleUserHome(project), "gradle.properties")
        if (userHomeProps.exists()) {
            userHomeProps.inputStream().use { result.load(it) }
        }

        // 2. 再加载项目根目录的 gradle.properties（会覆盖上面的同名键）
        val projectProps = File(project.rootDir, "gradle.properties")
        if (projectProps.exists()) {
            projectProps.inputStream().use { result.load(it) }
        }

        // 3. 遍历 project.properties，仅在值不存在时才 set
        //    保留 gradle.properties 中的优先级，避免被不明确来源的值覆盖
        project.properties.forEach { (k, v) ->
            // 注意：project.properties 总是覆盖前面两者，但不一定是我们想要的
            // 只有 key 不存在时才 set，避免低优先级覆盖高优先级
            if (!result.containsKey(k) && v != null) {
                result.setProperty(k.toString(), v.toString())
            }
        }

        return result
    }

    private fun gradleUserHome(project: Project): File {
        return project.gradle.gradleUserHomeDir ?: File(System.getProperty("user.home"), ".gradle")
    }

    private fun parseRepositories(properties: Properties): List<ResolvedRepository> {
        val repoIds = properties.stringPropertyNames()
            .mapNotNull { key ->
                Regex("""hfx\.(.+?)\.repo\.url""").matchEntire(key)?.groupValues?.get(1)
            }
            .distinct()

        return repoIds.mapNotNull { id ->
            val prefix = "hfx.$id.repo"
            val url = properties.getProperty("$prefix.url") ?: return@mapNotNull null
            val rawName = properties.getProperty("$prefix.name") ?: id
            val name = rawName
                .split(Regex("\\s+"))
                .joinToString("") { it.replaceFirstChar(Char::uppercaseChar) }

            val user = properties.getProperty("$prefix.user")
            val password = properties.getProperty("$prefix.password")

            var isAllowInsecureProtocol = url.startsWith("http:")
            val allowInsecure = properties.getProperty("$prefix.allowInsecure")
            if (!allowInsecure.isNullOrBlank()) {
                isAllowInsecureProtocol = allowInsecure.toBooleanStrictOrNull() ?: false
            }

            ResolvedRepository(name, url, user, password, isAllowInsecureProtocol)
        }
    }
}