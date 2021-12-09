plugins {
  id("plugin.application-js")
  id("org.jetbrains.compose") version "1.0.0"
}

description = "Local consumer sandbox"

kotlin {
  sourceSets {
    commonTest {
      dependencies {
        implementation("dev.petuska:test")
      }
    }
    jsMain {
      dependencies {
        implementation("dev.petuska:kmdc")
      }
    }
  }
}

// Workaround for https://kotlinlang.slack.com/archives/C0B8L3U69/p1633590092096600
rootProject.plugins.withType<org.jetbrains.kotlin.gradle.targets.js.yarn.YarnPlugin> {
  rootProject.the<org.jetbrains.kotlin.gradle.targets.js.yarn.YarnRootExtension>().apply {
    resolution("@webpack-cli/serve", "1.5.2")
  }
}
