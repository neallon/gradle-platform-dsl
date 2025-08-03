# hfxPlatform Gradle 插件

[![Gradle Plugin Portal](https://img.shields.io/maven-metadata/v?label=Gradle%20Plugin&metadataUrl=https://plugins.gradle.org/m2/io/github/neallon/platform-dsl/io.github.neallon.platform-dsl.gradle.plugin/maven-metadata.xml)](https://plugins.gradle.org/plugin/io.github.neallon.platform-dsl)

> 🎯 统一简化 Gradle 仓库配置、发布配置与依赖注入，提供一致性的 DSL。

---

## 📌 插件简介

`hfxPlatform` 是一个 Gradle 插件，用于统一管理 `repositories` 和 `publishing.repositories` 的配置。

支持功能：
- ✅ Gradle Groovy DSL (`build.gradle`)
- ✅ Gradle Kotlin DSL (`build.gradle.kts`)
- ✅ 自动注入构建与发布仓库
- ✅ 扩展型 `hfxPlatform {}` 顶级 DSL
- ✅ 内置辅助任务帮助调试与提示

---

## ⚙️ 安装方式

```kotlin
plugins {
    id("io.github.neallon.platform-dsl") version "1.1.0"
}
```

---

## 🧩 使用示例

```kotlin
hfxPlatform {
    repositories {
        usePresetRepositories() // 自动注入构建仓库
        mavenCentral()
        mavenLocal()
        // custom Maven repo
        maven {
            name = "custom"
            url = uri("http://repo.example.com/maven2")
            allowInsecureProtocol = true
        }
    }
    publishing {
        usePresetPublishingRepositories() // 自动注入发布仓库
    }
}
```

---

## 🛠️ 配置说明

在 `~/.gradle/gradle.properties` 或项目根目录的 `gradle.properties` 中配置：

```properties
hfx.mavenCentral.repo.url=https://repo.maven.apache.org/maven2
hfx.mavenCentral.repo.user=yourUsername
hfx.mavenCentral.repo.password=yourPassword
```

支持多个仓库前缀，如：

```properties
hfx.repo1.repo.url=...
hfx.repo2.repo.url=...
```

优先读取顺序：
1. 全局配置 `~/.gradle/gradle.properties`
2. 项目配置 `./gradle.properties`

---

## 🧪 辅助任务

```bash
./gradlew platformReposHelp
```

提供详细的配置说明与 ANSI 彩色提示。

---

## 📝 许可证

[MIT License](LICENSE)
