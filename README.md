# hfxPlatform Gradle Plugin

> 🎯 Unify and simplify Gradle repository configuration, publishing repositories, and dependency injection with a consistent DSL.

---

## 📌 Introduction 简介

`hfxPlatform` 是一个用于统一管理 Gradle 构建中仓库（`repositories`）和发布（`publishing.repositories`）的插件。  
The plugin provides a central DSL block to define and reuse preset repository and publishing configurations across builds.

It supports:
- ✅ Gradle Groovy DSL (`build.gradle`)
- ✅ Gradle Kotlin DSL (`build.gradle.kts`)
- ✅ Both `repositories` & `publishing.repositories` auto-injection
- ✅ Extendable `hfxPlatform {}` DSL
- ✅ Built-in help tasks

---

## ⚙️ Installation 安装方式

### Plugin DSL
```kotlin
plugins {
    id("io.github.neallon.platform-dsl") version "1.0.0"
}
```

---

## 🧩 Usage 示例

```kotlin
hfxPlatform {
    repositories {
        usePresetRepositories() // Injects repositories from gradle.properties
    }
    publishing {
        repositories {
            usePresetPublishingRepositories() // Injects publishing repositories
        }
    }
}
```

You can also define publications normally:

```kotlin
publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }
}
```

---

## 🛠️ Configuration 配置说明

支持通过 `gradle.properties` 配置多个仓库，支持用户名密码：

```properties
# ~/.gradle/gradle.properties 或 项目根目录 gradle.properties

# 示例：配置 Maven Central 仓库
hfx.mavenCentral.repo.url=https://repo.maven.apache.org/maven2
hfx.mavenCentral.repo.user=yourUsername
hfx.mavenCentral.repo.password=yourPassword

# 示例：自定义私服仓库
hfx.myCompany.repo.url=https://repo.mycorp.com/releases
hfx.myCompany.repo.user=admin
hfx.myCompany.repo.password=123456
```

自动优先读取顺序：
- 全局配置文件：`~/.gradle/gradle.properties`
- 项目配置文件：`./gradle.properties`（优先生效）

---

## 🧪 Helper Tasks 辅助任务

```bash
./gradlew platformReposHelp
```

输出带有 ANSI 彩色提示的配置指南：

```ansi
===========================
 hfxPlatform Repository Help 
===========================

Configuration Example (add to gradle.properties):
  hfx.mavenCentral.repo.url=https://repo.maven.apache.org/maven2
  hfx.mavenCentral.repo.user=yourUsername
  hfx.mavenCentral.repo.password=yourPassword

Multiple repositories supported:
  hfx.{repoName}.repo.url
  hfx.{repoName}.repo.user
  hfx.{repoName}.repo.password

Where to configure:
 - ~/.gradle/gradle.properties (global)
 - ./gradle.properties (project)

To apply repositories:
  ./gradlew build

To list tasks:
  ./gradlew tasks --group hfxPlatform
```

---

## 📦 Plugin Info 插件信息

| Name | Value |
|------|-------|
| Plugin ID | `io.github.neallon.platform-dsl` |
| Version | `1.0.0` |
| Compatible | Gradle 7.0+ |
| DSL Support | Kotlin / Groovy |
| Author | [HFX Open Platform](https://github.com/neallon/gradle-platform-dsl) |

---

## 📝 License

[MIT License](LICENSE)
